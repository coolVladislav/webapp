package validator;

import javax.servlet.http.HttpServletRequest;

import domain.Order;
import domain.OrderDoc;
import domain.ReasonDoc;
import exception.IncorrectFormDataException;

public class OrderDocValidator implements Validator<OrderDoc> {

	@Override
	public OrderDoc validate(HttpServletRequest request) throws IncorrectFormDataException {
		
		OrderDoc orderDoc = new OrderDoc();
		
		String parameter = request.getParameter("id");
		if(parameter != null) {
			try {
				orderDoc.setId(Integer.parseInt(parameter));
			} catch(NumberFormatException e) {
				throw new IncorrectFormDataException("id", parameter);
			}
		}
		
		parameter = request.getParameter("order");
		if(parameter != null && !parameter.isEmpty()) {
			Order order = new Order();
			try {
				order.setId(Integer.parseInt(parameter));
				orderDoc.setOrder(order);
			} catch(NumberFormatException e) {
				throw new IncorrectFormDataException("order", parameter);
			}
		}
		
		parameter = request.getParameter("reasonDoc");
		if(parameter != null && !parameter.isEmpty()) {
			ReasonDoc reasonDoc = new ReasonDoc();
			try {
				reasonDoc.setId(Integer.parseInt(parameter));
				orderDoc.setReasonDoc(reasonDoc);
			} catch(NumberFormatException e) {
				throw new IncorrectFormDataException("reasonDoc", parameter);
			}
		}
		
		parameter = request.getParameter("isAccepted");
		if(parameter != null && !parameter.isEmpty()) {
			try {
				orderDoc.setIsAccepted(Boolean.parseBoolean(parameter));
			} catch(NumberFormatException e) {
				throw new IncorrectFormDataException("isAccepted", parameter);
			}
		}
		return orderDoc;
	}

}
