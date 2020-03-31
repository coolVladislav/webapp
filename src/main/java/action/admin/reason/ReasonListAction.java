package action.admin.reason;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import action.admin.AdministratorAction;
import domain.Reason;
import exception.PersistentException;
import service.ReasonService;

public class ReasonListAction extends AdministratorAction{
	private static Logger logger = LogManager.getLogger(ReasonListAction.class);

	@Override
	public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		ReasonService service = factory.getService(ReasonService.class);
		List<Reason> reasons = service.findAll();
		logger.debug("In Reason List Action "+reasons);
		request.setAttribute("reasons", reasons);
		return null;
	}

}
