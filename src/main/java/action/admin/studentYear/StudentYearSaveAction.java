package action.admin.studentYear;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import action.admin.AdministratorAction;
import domain.StudentYear;
import exception.IncorrectFormDataException;
import exception.PersistentException;
import service.StudentYearService;
import validator.Validator;
import validator.ValidatorFactory;

public class StudentYearSaveAction extends AdministratorAction{
	private static Logger logger = LogManager.getLogger(StudentYearSaveAction.class);

	@Override
	public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		Forward forward = new Forward("/studentYear/list.html");
		try {
			Validator<StudentYear> validator = ValidatorFactory.createValidator(StudentYear.class);
			StudentYear studentYear = validator.validate(request);
			StudentYearService service = factory.getService(StudentYearService.class);
			service.save(studentYear);
			forward.getAttributes().put("id", studentYear.getId());
			forward.getAttributes().put("message", "Данные  успешно сохранены");
			logger.info(String.format("StudentYear successfully saved with id %d", studentYear.getId()));
		} catch(IncorrectFormDataException e) {
			forward.getAttributes().put("message", "Были обнаружены некорректные данные");
			logger.warn(String.format("Incorrect data was found when user  tried to save " , e));
		}
		return forward;
	}
}
