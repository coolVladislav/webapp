package action.admin.reasonDoc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import action.admin.AdministratorAction;
import domain.ReasonDoc;
import exception.PersistentException;
import service.ReasonDocService;

public class ReasonDocListAction  extends AdministratorAction{
	private static Logger logger = LogManager.getLogger(ReasonDocListAction.class);

	@Override
	public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		ReasonDocService service = factory.getService(ReasonDocService.class);
		List<ReasonDoc> reasonDocs = service.findAll();
		logger.debug("In ReasonDoc List Action "+reasonDocs);
		request.setAttribute("reasonDocs", reasonDocs);
		return null;
	}

}
