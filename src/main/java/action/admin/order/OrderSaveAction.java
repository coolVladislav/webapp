package action.admin.order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import action.admin.AdministratorAction;
import domain.Order;
import exception.IncorrectFormDataException;
import exception.PersistentException;
import service.OrderService;

import validator.Validator;
import validator.ValidatorFactory;

public class OrderSaveAction extends AdministratorAction{
	private static Logger logger = LogManager.getLogger(OrderSaveAction.class);
	
	@Override
	public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		Forward forward = new Forward("/order/list.html");
		try {
			Validator<Order> validator = ValidatorFactory.createValidator(Order.class);
			Order order = validator.validate(request);
			OrderService service = factory.getService(OrderService.class);
			service.save(order);
			forward.getAttributes().put("id", order.getId());
			forward.getAttributes().put("message", "Данные  успешно сохранены");
			logger.info(String.format("Order saved",order));
		} catch(IncorrectFormDataException e) {
			forward.getAttributes().put("message", "Были обнаружены некорректные данные");
			logger.warn(String.format("Incorrect data was found when tried to save Order"), e);
		}
		return forward;
	}

}
