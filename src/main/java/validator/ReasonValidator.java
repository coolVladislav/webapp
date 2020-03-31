package validator;

import javax.servlet.http.HttpServletRequest;

import domain.Reason;
import exception.IncorrectFormDataException;

public class ReasonValidator implements Validator<Reason> {

	@Override
	public Reason validate(HttpServletRequest request) throws IncorrectFormDataException {
		Reason reason = new Reason();
		
		String parameter = request.getParameter("id");
		if(parameter != null) {
			try {
				reason.setId(Integer.parseInt(parameter));
			} catch(NumberFormatException e) {
				throw new IncorrectFormDataException("id", parameter);
			}
		}
		
		parameter = request.getParameter("number");
		if(parameter != null && !parameter.isEmpty()) {
			try {
				reason.setNumber(Double.parseDouble(parameter));
			} catch(NumberFormatException e) {
				throw new IncorrectFormDataException("number", parameter);
			}
		}
		
		parameter = request.getParameter("note");
		if(parameter != null && !parameter.isEmpty()) {
			try {
				reason.setNote(parameter);
			} catch(NumberFormatException e) {
				throw new IncorrectFormDataException("note", parameter);
			}
		}
		
		parameter = request.getParameter("max_coef");
		if(parameter != null && !parameter.isEmpty()) {
			try {
				reason.setMaxCoef(Integer.parseInt(parameter));
			} catch(NumberFormatException e) {
				throw new IncorrectFormDataException("max_coef", parameter);
			}
		}
		
		return reason;
	}

}
