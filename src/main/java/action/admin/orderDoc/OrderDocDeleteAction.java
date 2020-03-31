package action.admin.orderDoc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import action.admin.AdministratorAction;
import exception.PersistentException;
import service.OrderDocService;

public class OrderDocDeleteAction extends AdministratorAction{
	
	private static Logger logger = LogManager.getLogger(OrderDocDeleteAction.class);

	@Override
	public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		Forward forward = new Forward("/orderDoc/list.html");
		try {
			OrderDocService service = factory.getService(OrderDocService.class);
			Integer id = Integer.parseInt(request.getParameter("id"));
			service.delete(id);
			forward.getAttributes().put("message", "OrderDoc успешно удалёна");
			logger.info(String.format("OrderDoc was deleted with id"+ id));
		} catch(NumberFormatException e) {
			logger.warn(String.format("Incorrect data was found when tried to delete orderDoc"), e);
		}
		return null;
	}

}
