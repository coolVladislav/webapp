package action.admin.studentYear;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import action.admin.AdministratorAction;
import domain.Student;
import domain.StudentYear;
import exception.PersistentException;
import service.StudentService;
import service.StudentYearService;

public class StudentYearEditAction extends AdministratorAction{
	private static Logger logger = LogManager.getLogger(StudentYearEditAction.class);

	@Override
	public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		StudentService studentService = factory.getService(StudentService.class);
		List<Student> students = studentService.findAll();
		request.setAttribute("students", students);
		
		try {
			Integer id = (Integer)request.getAttribute("id");
			if(id == null) {
				id = Integer.parseInt(request.getParameter("id"));
			}
			StudentYearService service = factory.getService(StudentYearService.class);
			StudentYear studentYear = service.findById(id);
			logger.debug("In StudentYear Save Action "+studentYear);
			if(studentYear != null) {
				request.setAttribute("studentYear", studentYear);
			}
		} catch(NumberFormatException e) {}
		return null;
	}
}
