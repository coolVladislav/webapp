package action.admin.student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import action.admin.AdministratorAction;
import exception.PersistentException;
import service.StudentService;

public class StudentDeleteAction extends AdministratorAction{

	private static Logger logger = LogManager.getLogger(StudentDeleteAction.class);

	@Override
	public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		Forward forward = new Forward("/student/list.html");
		try {
			Integer id = (Integer)request.getAttribute("id");
			if(id == null) {
				id = Integer.parseInt(request.getParameter("id"));
			}
			logger.debug("In Student Delete Action "+id);
			StudentService service = factory.getService(StudentService.class);
			service.delete(id);
			forward.getAttributes().put("message", "Student успешно удалён");
			logger.info(String.format("Srudent deleted  with id %d", id));
		} catch(NumberFormatException e) {
			logger.warn(String.format("Incorrect data was found when tried to delete Student"), e);
		}
		return forward;
	}
}
