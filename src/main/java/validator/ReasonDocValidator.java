package validator;

import javax.servlet.http.HttpServletRequest;


import domain.ReasonDoc;
import exception.IncorrectFormDataException;

public class ReasonDocValidator implements Validator<ReasonDoc>{

	@Override
	public ReasonDoc validate(HttpServletRequest request) throws IncorrectFormDataException {
		
		ReasonDoc reasonDoc = new ReasonDoc();
		
		String parameter = request.getParameter("id");
		if(parameter != null) {
			try {
				reasonDoc.setId(Integer.parseInt(parameter));
			} catch(NumberFormatException e) {
				throw new IncorrectFormDataException("id", parameter);
			}
		}
		
		parameter = request.getParameter("number");
		if(parameter != null && !parameter.isEmpty()) {
			try {
				reasonDoc.setNumber(Double.parseDouble(parameter));
			} catch(NumberFormatException e) {
				throw new IncorrectFormDataException("number", parameter);
			}
		}
		
		parameter = request.getParameter("docName");
		if(parameter != null && !parameter.isEmpty()) {
			try {
				reasonDoc.setDocName(parameter);
			} catch(NumberFormatException e) {
				throw new IncorrectFormDataException("docName", parameter);
			}
		
		}
		return reasonDoc;
	}
}


