package action.admin.studentAdditional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import action.admin.AdministratorAction;
import domain.StudentAdditional;
import exception.IncorrectFormDataException;
import exception.PersistentException;
import service.StudentAdditionalService;
import validator.Validator;
import validator.ValidatorFactory;

public class StudentAdditionalSaveAction extends AdministratorAction{
	private static Logger logger = LogManager.getLogger(StudentAdditionalSaveAction.class);

	@Override
	public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		Forward forward = new Forward("/studentAdditional/list.html");
		try {
			Validator<StudentAdditional> validator = ValidatorFactory.createValidator(StudentAdditional.class);
			StudentAdditional studentAdditional = validator.validate(request);
			StudentAdditionalService service = factory.getService(StudentAdditionalService.class);
			service.save(studentAdditional);
			forward.getAttributes().put("id", studentAdditional.getId());
			forward.getAttributes().put("message", "Данные  успешно сохранены");
			logger.info(String.format("StudentAdditional was saved  with id"+studentAdditional.getId()));
		} catch(IncorrectFormDataException e) {
			forward.getAttributes().put("message", "Были обнаружены некорректные данные");
			logger.warn(String.format("Incorrect data was found when  tried to save StudentAdditional"), e);
		}
		return forward;
	}
}
