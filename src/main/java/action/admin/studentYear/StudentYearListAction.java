package action.admin.studentYear;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import action.Action.Forward;
import action.admin.AdministratorAction;
import domain.Student;
import domain.StudentYear;
import exception.PersistentException;
import service.StudentService;
import service.StudentYearService;

public class StudentYearListAction extends AdministratorAction{
	
	private static Logger logger = LogManager.getLogger(StudentYearListAction.class);
	@Override
	public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		
		StudentYearService service = factory.getService(StudentYearService.class);
		List<StudentYear> studentYears = service.findAll();
		logger.debug("In StudentYear List Action "+studentYears);
		request.setAttribute("studentYears", studentYears);
		return null;
	}
}
