package service;

import java.util.List;

import domain.StudentYear;

public interface StudentYearService extends Service{
	List<StudentYear> findAll();
	
	StudentYear findById(Integer identity);
	
	void save(StudentYear studentYear);
	
	void delete(Integer identity);
}
