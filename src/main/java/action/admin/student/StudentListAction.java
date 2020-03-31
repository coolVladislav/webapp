package action.admin.student;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import action.admin.AdministratorAction;
import domain.Student;
import exception.PersistentException;
import service.StudentService;

public class StudentListAction extends AdministratorAction{
	private static Logger logger = LogManager.getLogger(StudentListAction.class);
	
	@Override
	public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		StudentService service = factory.getService(StudentService.class);
		List<Student> students = service.findAll();
		logger.debug("In Student List Action "+students);
		request.setAttribute("students", students);
		return null;
	}

}
