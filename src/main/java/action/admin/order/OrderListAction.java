package action.admin.order;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import action.admin.AdministratorAction;
import domain.Order;
import exception.PersistentException;
import service.OrderService;


public class OrderListAction extends AdministratorAction{
	private static Logger logger = LogManager.getLogger(OrderListAction.class);

	@Override
	public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		OrderService service = factory.getService(OrderService.class);
		List<Order> orders = service.findAll();
		logger.debug("In Order List Action "+orders);
		request.setAttribute("orders", orders);
		return null;
	}

}
