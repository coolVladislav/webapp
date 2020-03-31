package action.admin.studentYear;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import action.admin.AdministratorAction;
import action.admin.student.StudentDeleteAction;
import exception.PersistentException;
import service.StudentYearService;

public class StudentYearDeleteAction extends AdministratorAction{

	private static Logger logger = LogManager.getLogger(StudentDeleteAction.class);

	@Override
	public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		Forward forward = new Forward("/studentYear/list.html");
		try {
			StudentYearService service = factory.getService(StudentYearService.class);
			Integer id = Integer.parseInt(request.getParameter("id"));
			service.delete(id);
			forward.getAttributes().put("message", "Студент+год успешно удалён");
			logger.info(String.format("Student+Year deleted with id: "+id));
		} catch(NumberFormatException e) {
			logger.warn(String.format("Incorrect data was found when  tried to delete StudentYear", e));
		}
		return forward;
	}
}
