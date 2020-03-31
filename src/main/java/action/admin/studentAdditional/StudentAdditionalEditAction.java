package action.admin.studentAdditional;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import action.admin.AdministratorAction;
import domain.Reason;
import domain.StudentAdditional;
import domain.StudentYear;
import exception.PersistentException;
import service.ReasonService;
import service.StudentAdditionalService;
import service.StudentYearService;

public class StudentAdditionalEditAction extends AdministratorAction{
	private static Logger logger = LogManager.getLogger(StudentAdditionalEditAction.class);
	
	@Override
	public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		
		StudentYearService stService = factory.getService(StudentYearService.class);
		List<StudentYear> studentYears = stService.findAll();
		request.setAttribute("studentYears", studentYears);
		
		ReasonService rService = factory.getService(ReasonService.class);
		List<Reason> reasons = rService.findAll();
		request.setAttribute("reasons", reasons);
		
		try {
			Integer id = (Integer)request.getAttribute("id");
			if(id == null) {
				id = Integer.parseInt(request.getParameter("id"));
			}
			StudentAdditionalService service = factory.getService(StudentAdditionalService.class);
			StudentAdditional studentAdditional = service.findById(id);
			logger.debug("In StudentAdditional Edit Action "+studentAdditional);
			if(studentAdditional != null) {
				request.setAttribute("studentAdditional", studentAdditional);
			}
		} catch(NumberFormatException e) {}
		return null;
	}
}
