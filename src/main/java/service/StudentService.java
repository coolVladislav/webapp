package service;

import java.util.List;

import domain.Student;

public interface StudentService extends Service{
	List<Student> findAll();

	Student findById(Integer identity);

	void save(Student student);

	void delete(Integer identity);
}
