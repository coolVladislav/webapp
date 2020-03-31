package validator;

import javax.servlet.http.HttpServletRequest;

import domain.Reason;
import domain.StudentAdditional;
import domain.StudentYear;
import exception.IncorrectFormDataException;

public class StudentAdditionalValidator implements Validator<StudentAdditional>{

	@Override
	public StudentAdditional validate(HttpServletRequest request) throws IncorrectFormDataException {		
		StudentAdditional studentAdditional = new StudentAdditional();
		
		String parameter = request.getParameter("id");
		if(parameter != null) {
			try {
				studentAdditional.setId(Integer.parseInt(parameter));
			} catch(NumberFormatException e) {
				throw new IncorrectFormDataException("id", parameter);
			}
		}
		
		parameter = request.getParameter("studentYear");
		if(parameter != null && !parameter.isEmpty()) {
			StudentYear studentYear = new StudentYear();
			try {
				studentYear.setId(Integer.parseInt(parameter));
				studentAdditional.setStudentYear(studentYear);
			} catch(NumberFormatException e) {
				throw new IncorrectFormDataException("studentYear", parameter);
			}
		}
		
		parameter = request.getParameter("sum");
		if(parameter != null && !parameter.isEmpty()) {
			try {
				studentAdditional.setSum(Double.parseDouble(parameter));
			} catch(NumberFormatException e) {
				throw new IncorrectFormDataException("sum", parameter);
			}
		}
		
		parameter = request.getParameter("reason");
		if(parameter != null && !parameter.isEmpty()) {
			Reason reason = new Reason();
			try {
				reason.setId(Integer.parseInt(parameter));
				studentAdditional.setReason(reason);
			} catch(NumberFormatException e) {
				throw new IncorrectFormDataException("reason", parameter);
			}
		}
		
		parameter = request.getParameter("month");
		if(parameter != null && !parameter.isEmpty()) {
			try {
				studentAdditional.setMonth(Integer.parseInt(parameter));;
			} catch(NumberFormatException e) {
				throw new IncorrectFormDataException("month", parameter);
			}
		}
		
		parameter = request.getParameter("isProved");
		if(parameter != null && !parameter.isEmpty()) {
			try {
				studentAdditional.setIsProved(Boolean.parseBoolean(parameter));;
			} catch(NumberFormatException e) {
				throw new IncorrectFormDataException("isProved", parameter);
			}
		}
		
		parameter = request.getParameter("dateOfProve");
		if(parameter != null && !parameter.isEmpty()) {
			try {
				studentAdditional.setDateOfProve(Integer.parseInt(parameter));;
			} catch(NumberFormatException e) {
				throw new IncorrectFormDataException("dateOfProve", parameter);
			}
		}
		
		return studentAdditional;
	}

}
