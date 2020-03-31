package action.admin.reasonDoc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import action.admin.AdministratorAction;
import domain.ReasonDoc;
import exception.IncorrectFormDataException;
import exception.PersistentException;
import service.ReasonDocService;
import validator.Validator;
import validator.ValidatorFactory;

public class ReasonDocSaveAction extends AdministratorAction{

	private static Logger logger = LogManager.getLogger(ReasonDocSaveAction.class);

	@Override
	public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		Forward forward = new Forward("/reasonDoc/list.html");
		try {
			Validator<ReasonDoc> validator = ValidatorFactory.createValidator(ReasonDoc.class);
			ReasonDoc reasonDoc = validator.validate(request);
			ReasonDocService service = factory.getService(ReasonDocService.class);
			service.save(reasonDoc);
			forward.getAttributes().put("id", reasonDoc.getId());
			forward.getAttributes().put("message", "Данные  успешно сохранены");
			logger.info(String.format("ReasonDoc was saved  with id"+reasonDoc.getId()));
		} catch(IncorrectFormDataException e) {
			forward.getAttributes().put("message", "Были обнаружены некорректные данные");
			logger.warn(String.format("Incorrect data was found when tried to save ReasonDoc"), e);
		}
		return forward;
	}
}
