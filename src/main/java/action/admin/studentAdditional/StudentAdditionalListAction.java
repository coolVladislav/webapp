package action.admin.studentAdditional;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import action.admin.AdministratorAction;
import domain.StudentAdditional;
import exception.PersistentException;
import service.StudentAdditionalService;

public class StudentAdditionalListAction extends AdministratorAction{
	private static Logger logger = LogManager.getLogger(StudentAdditionalListAction.class);

	@Override
	public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		StudentAdditionalService service = factory.getService(StudentAdditionalService.class);
		List<StudentAdditional> studentAdditionals = service.findAll();
		logger.debug("In StudentAdditional List Action "+studentAdditionals);
		request.setAttribute("studentAdditionals", studentAdditionals);
		return null;
	}

}
