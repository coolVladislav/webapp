package action.admin.reason;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import action.admin.AdministratorAction;
import exception.PersistentException;
import service.ReasonService;

public class ReasonDeleteAction extends AdministratorAction{

	private static Logger logger = LogManager.getLogger(ReasonDeleteAction.class);

	@Override
	public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		Forward forward = new Forward("/reason/list.html");
		try {
			ReasonService service = factory.getService(ReasonService.class);
			Integer id = Integer.parseInt(request.getParameter("id"));
			service.delete(id);
			forward.getAttributes().put("message", "Reason успешно удалена");
			logger.info(String.format("Reason was deleted with id"+ id));
		} catch(NumberFormatException e) {
			logger.warn(String.format("Incorrect data was found when  tried to delete Reason"), e);
		}
		return forward;
	}

}
