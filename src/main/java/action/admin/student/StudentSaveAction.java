package action.admin.student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import action.admin.AdministratorAction;
import domain.Student;
import exception.IncorrectFormDataException;
import exception.PersistentException;
import service.StudentService;
import validator.Validator;
import validator.ValidatorFactory;

public class StudentSaveAction extends AdministratorAction{
	private static Logger logger = LogManager.getLogger(StudentSaveAction.class);

	@Override
	public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		Forward forward = new Forward("/student/edit.html");
		try {
			Validator<Student> validator = ValidatorFactory.createValidator(Student.class);
			Student student = validator.validate(request);
			StudentService service = factory.getService(StudentService.class);
			service.save(student);
			forward.getAttributes().put("id", student.getId());
			logger.debug(String.format("In student save "+student));
			logger.debug(String.format("In student save forward "+forward.getAttributes()));
			forward.getAttributes().put("message", "Данные  успешно сохранены"+student);
		} catch(IncorrectFormDataException e) {
			forward.getAttributes().put("message", "Были обнаружены некорректные данные");
			logger.warn(String.format("Incorrect data was found when user \"%s\" tried to save ", e));
		}
		return forward;
	}
}
