package action.admin.orderDoc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import action.admin.AdministratorAction;
import domain.OrderDoc;
import exception.IncorrectFormDataException;
import exception.PersistentException;
import service.OrderDocService;
import validator.Validator;
import validator.ValidatorFactory;

public class OrderDocSaveAction extends AdministratorAction{
	private static Logger logger = LogManager.getLogger(OrderDocSaveAction.class);

	@Override
	public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		Forward forward = new Forward("/orderDoc/list.html");
		try {
			Validator<OrderDoc> validator = ValidatorFactory.createValidator(OrderDoc.class);
			OrderDoc orderDoc = validator.validate(request);
			OrderDocService service = factory.getService(OrderDocService.class);
			service.save(orderDoc);
			forward.getAttributes().put("id", orderDoc.getId());
			forward.getAttributes().put("message", "Данные  успешно сохранены");
			logger.info(String.format("OrderDoc saved  with id "+ orderDoc.getId()));
		} catch(IncorrectFormDataException e) {
			forward.getAttributes().put("message", "Были обнаружены некорректные данные");
			logger.warn(String.format("Incorrect data was found when tried to save "), e);
		}
		return forward;
	}

}
