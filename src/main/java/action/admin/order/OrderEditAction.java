package action.admin.order;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import action.admin.AdministratorAction;
import domain.Order;
import domain.Reason;
import domain.StudentYear;
import exception.PersistentException;
import service.OrderService;
import service.ReasonService;
import service.StudentYearService;
public class OrderEditAction extends AdministratorAction{
	private static Logger logger = LogManager.getLogger(OrderEditAction.class);

	@Override
	public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		
		ReasonService reasonService = factory.getService(ReasonService.class);
		List<Reason> reasons = reasonService.findAll();
		request.setAttribute("reasons", reasons);
		
		StudentYearService studentYearService = factory.getService(StudentYearService.class);
		List<StudentYear> studentYears = studentYearService.findAll();
		request.setAttribute("studentYears", studentYears);
		
		try {
			Integer id = (Integer)request.getAttribute("id");
			if(id == null) {
				id = Integer.parseInt(request.getParameter("id"));
			}
			OrderService service = factory.getService(OrderService.class);
			Order order = service.findById(id);
			logger.debug("In Order Edit Action"+order);
			if(order != null) {
				request.setAttribute("order", order);
			}
		} catch(NumberFormatException e) {}
		return null;
	}

}
