package action.admin.reason;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import action.admin.AdministratorAction;
import domain.Reason;
import exception.PersistentException;
import service.ReasonService;

public class ReasonEditAction extends AdministratorAction{
	private static Logger logger = LogManager.getLogger(ReasonEditAction.class);

	@Override
	public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		try {
			Integer id = (Integer)request.getAttribute("id");
			if(id == null) {
				id = Integer.parseInt(request.getParameter("id"));
			}
			ReasonService service = factory.getService(ReasonService.class);
			Reason reason = service.findById(id);
			logger.debug("In Reason Edit Action "+reason);
			if(reason != null) {
				request.setAttribute("reason", reason);
			}
		} catch(NumberFormatException e) {}
		return null;
	}

}
