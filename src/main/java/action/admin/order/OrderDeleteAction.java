package action.admin.order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import action.admin.AdministratorAction;
import exception.PersistentException;
import service.OrderService;

public class OrderDeleteAction extends AdministratorAction{
	
	private static Logger logger = LogManager.getLogger(OrderDeleteAction.class);

	@Override
	public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		Forward forward = new Forward("/order/list.html");
		
		try {
			OrderService service = factory.getService(OrderService.class);
			Integer id = Integer.parseInt(request.getParameter("id"));
			service.delete(id);
			forward.getAttributes().put("message", "Заявка успешно удалёна");
			logger.info(String.format("Order was deleted with identity %d" ,id));
		} catch(NumberFormatException e) {
			logger.warn(String.format("Incorrect data was found when tried to delete order"), e);
		}
		return forward;
	}

}
