package service;

import java.util.List;
import domain.StudentAdditional;

public interface StudentAdditionalService extends Service{
	List<StudentAdditional> findAll();

	StudentAdditional findById(Integer identity);

	void save(StudentAdditional studentAdditional);

	void delete(Integer identity);
}
