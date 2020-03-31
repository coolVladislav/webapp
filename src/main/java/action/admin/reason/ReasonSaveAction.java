package action.admin.reason;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import action.admin.AdministratorAction;
import domain.Reason;
import exception.IncorrectFormDataException;
import exception.PersistentException;
import service.ReasonService;
import validator.Validator;
import validator.ValidatorFactory;

public class ReasonSaveAction extends AdministratorAction{
	
	private static Logger logger = LogManager.getLogger(ReasonSaveAction.class);

	@Override
	public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		Forward forward = new Forward("/reason/list.html");
		try {
			Validator<Reason> validator = ValidatorFactory.createValidator(Reason.class);
			Reason reason = validator.validate(request);
			ReasonService service = factory.getService(ReasonService.class);
			service.save(reason);
			forward.getAttributes().put("id", reason.getId());
			forward.getAttributes().put("message", "Данные  успешно сохранены");
			logger.info(String.format("Reason saved  with id  "+reason.getId()));
		} catch(IncorrectFormDataException e) {
			forward.getAttributes().put("message", "Были обнаружены некорректные данные");
			logger.warn(String.format("Incorrect data was found when Reason tried to save "), e);
		}
		return forward;
	}

}
