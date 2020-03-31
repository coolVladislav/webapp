package action.admin.student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import action.admin.AdministratorAction;
import domain.Student;
import exception.PersistentException;
import service.StudentService;

public class StudentEditAction extends AdministratorAction{
	private static Logger logger = LogManager.getLogger(StudentEditAction.class);
	
	@Override
	public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		try {
			Integer id = (Integer)request.getAttribute("id");
			if(id == null) {
				id = Integer.parseInt(request.getParameter("id"));
			}
			StudentService service = factory.getService(StudentService.class);
			Student student = service.findById(id);
			logger.debug("In Student Edit"+student);
			if(student != null) {
				request.setAttribute("student", student);
			}
		} catch(NumberFormatException e) {}
		return null;
	}
}
