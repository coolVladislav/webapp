package action.admin.orderDoc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import action.admin.AdministratorAction;
import domain.Order;
import domain.OrderDoc;
import domain.ReasonDoc;
import exception.PersistentException;
import service.OrderDocService;
import service.OrderService;
import service.ReasonDocService;

public class OrderDocEditAction extends AdministratorAction{
	private static Logger logger = LogManager.getLogger(OrderDocEditAction.class);

	@Override
	public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		
		OrderService orderService = factory.getService(OrderService.class);
		List<Order> orders = orderService.findAll();
		request.setAttribute("orders", orders);
		
		ReasonDocService reasonService = factory.getService(ReasonDocService.class);
		List<ReasonDoc> reasonDocs = reasonService.findAll();
		request.setAttribute("reasonDocs", reasonDocs);
		
		try {
			Integer id = (Integer)request.getAttribute("id");
			if(id == null) {
				id = Integer.parseInt(request.getParameter("id"));
			}
			OrderDocService service = factory.getService(OrderDocService.class);
			OrderDoc orderDoc = service.findById(id);
			logger.debug("In OrderDoc Edit Action "+orderDoc);
			if(orderDoc != null) {
				request.setAttribute("orderDoc", orderDoc);
			}
		} catch(NumberFormatException e) {}
		return null;
	}

}
