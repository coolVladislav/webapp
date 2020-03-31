package action.admin.studentAdditional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import action.admin.AdministratorAction;
import exception.PersistentException;
import service.StudentAdditionalService;

public class StudentAdditionalDeleteAction extends AdministratorAction{

	private static Logger logger = LogManager.getLogger(StudentAdditionalDeleteAction.class);

	@Override
	public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		Forward forward = new Forward("/studentAdditional/list.html");
		try {
			StudentAdditionalService service = factory.getService(StudentAdditionalService.class);
			Integer id = Integer.parseInt(request.getParameter("id"));
			service.delete(id);
			forward.getAttributes().put("message", "StudentAdditional успешно удалёна");
			logger.info(String.format("StudentAdditional was deleted  with id "+ id));
		} catch(NumberFormatException e) {
			logger.warn(String.format("Incorrect data was found when  tried to delete StudentAdditional"), e);
		}
		return forward;
	}
}
