package validator;

import javax.servlet.http.HttpServletRequest;

import domain.Order;
import domain.Reason;
import domain.StudentYear;
import exception.IncorrectFormDataException;

public class OrderValidator implements Validator<Order>{

	@Override
	public Order validate(HttpServletRequest request) throws IncorrectFormDataException {
		Order order = new Order();
		
		String parameter = request.getParameter("id");
		if(parameter != null) {
			try {
				order.setId(Integer.parseInt(parameter));
			} catch(NumberFormatException e) {
				throw new IncorrectFormDataException("id", parameter);
			}
		}
		
		parameter = request.getParameter("studentYear");
		if(parameter != null && !parameter.isEmpty()) {
			StudentYear studentYear = new StudentYear();
			try {
				studentYear.setId(Integer.parseInt(parameter));
				order.setStudentYear(studentYear);
			} catch(NumberFormatException e) {
				throw new IncorrectFormDataException("studentYear", parameter);
			}
		}
		
		parameter = request.getParameter("reason");
		if(parameter != null && !parameter.isEmpty()) {
			Reason reason = new Reason();
			try {
				reason.setId(Integer.parseInt(parameter));
				order.setReason(reason);
			} catch(NumberFormatException e) {
				throw new IncorrectFormDataException("reason", parameter);
			}
		}
		
		parameter = request.getParameter("dateOfOrder");
		if(parameter != null && !parameter.isEmpty()) {
			try {
			order.setDateOfOrder(Integer.parseInt(parameter));
			} catch(NumberFormatException e) {
				throw new IncorrectFormDataException("dateOfOrder", parameter);
			}
		}
		
		
		
		return order;
	}

}
