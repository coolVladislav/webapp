package validator;

import javax.servlet.http.HttpServletRequest;

import domain.Student;
import exception.IncorrectFormDataException;

public class StudentValidator implements Validator<Student>{

	@Override
	public Student validate(HttpServletRequest request) throws IncorrectFormDataException {
		
		Student student = new Student();
		
		String parameter = request.getParameter("id");
		if(parameter != null) {
			try {
				student.setId(Integer.parseInt(parameter));
			} catch(NumberFormatException e) {
				throw new IncorrectFormDataException("id", parameter);
			}
		}
		
		parameter = request.getParameter("last_name");
		if(parameter != null && !parameter.isEmpty()) {
			try {
				student.setLastName(parameter);
			} catch(NumberFormatException e) {
				throw new IncorrectFormDataException("last_name", parameter);
			}
		}
		
		parameter = request.getParameter("first_name");
		if(parameter != null && !parameter.isEmpty()) {
			try {
				student.setFirstName(parameter);
			} catch(NumberFormatException e) {
				throw new IncorrectFormDataException("first_name", parameter);
			}
		}
		
		parameter = request.getParameter("patronymic");
		if(parameter != null && !parameter.isEmpty()) {
			try {
				student.setPatronymic(parameter);
			} catch(NumberFormatException e) {
				throw new IncorrectFormDataException("patronymic", parameter);
			}
		}
		return student;
	}

}
