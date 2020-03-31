package action.admin.orderDoc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import action.admin.AdministratorAction;
import domain.OrderDoc;
import exception.PersistentException;
import service.OrderDocService;

public class OrderDocListAction extends AdministratorAction{
	private static Logger logger = LogManager.getLogger(OrderDocListAction.class);

	@Override
	public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		OrderDocService service = factory.getService(OrderDocService.class);
		List<OrderDoc> orderDocs = service.findAll();
		logger.debug("In OrderDoc List Action "+orderDocs);
		request.setAttribute("orderDocs", orderDocs);
		return null;
	}

}
