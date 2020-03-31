package action.admin.reasonDoc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import action.admin.AdministratorAction;
import exception.PersistentException;
import service.ReasonDocService;

public class ReasonDocDeleteAction extends AdministratorAction{
	private static Logger logger = LogManager.getLogger(ReasonDocDeleteAction.class);

	@Override
	public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		Forward forward = new Forward("/reasonDoc/list.html");
		try {
			ReasonDocService service = factory.getService(ReasonDocService.class);
			Integer id = Integer.parseInt(request.getParameter("id"));
			service.delete(id);
			forward.getAttributes().put("message", "ReasonDoc успешно удалёна");
			logger.info(String.format("ReasonDoc was deleted reader with id"+ id));
		} catch(NumberFormatException e) {
			logger.warn(String.format("Incorrect data was found when tried to delete ReasonDoc"), e);
		}
		return forward;
	}

}
