package validator;

import javax.servlet.http.HttpServletRequest;

import exception.IncorrectFormDataException;
import domain.Reason;
import domain.Student;
import domain.StudentYear;

public class StudentYearValidator implements Validator<StudentYear>{

	@Override
	public StudentYear validate(HttpServletRequest request) throws IncorrectFormDataException {
		StudentYear studentYear = new StudentYear();
		
		String parameter = request.getParameter("id");
		if(parameter != null) {
			try {
				studentYear.setId(Integer.parseInt(parameter));
			} catch(NumberFormatException e) {
				throw new IncorrectFormDataException("id", parameter);
			}
		}
		
		
		parameter = request.getParameter("course");
		if(parameter != null && !parameter.isEmpty()) {
			try {
				studentYear.setCourse(Integer.parseInt(parameter));
			} catch(NumberFormatException e) {
				throw new IncorrectFormDataException("course", parameter);
			}
		}
		
		parameter = request.getParameter("group");
		if(parameter != null && !parameter.isEmpty()) {
			try {
				studentYear.setGroup(Integer.parseInt(parameter));
			} catch(NumberFormatException e) {
				throw new IncorrectFormDataException("group", parameter);
			}
		}
		
		parameter = request.getParameter("isFreeibe");
		if(parameter != null && !parameter.isEmpty()) {
			try {
				studentYear.setIsFreeibe(Boolean.parseBoolean(parameter));
			} catch(NumberFormatException e) {
				throw new IncorrectFormDataException("isFreeibe", parameter);
			}
		}
		
		parameter = request.getParameter("student");
		if(parameter != null && !parameter.isEmpty()) {
			Student student = new Student();
			try {
				student.setId(Integer.parseInt(parameter));
				studentYear.setStudent(student);
			} catch(NumberFormatException e) {
				throw new IncorrectFormDataException("student", parameter);
			}
		}
		
		parameter = request.getParameter("year");
		if(parameter != null && !parameter.isEmpty()) {
			try {
				studentYear.setYear(Integer.parseInt(parameter));
			} catch(NumberFormatException e) {
				throw new IncorrectFormDataException("year", parameter);
			}
		}
		
		return studentYear;
	}

}
