package service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.StudentDao;
import domain.Student;

public class StudentServiceImpl extends ServiceImpl implements StudentService {
	static final Logger logger = LogManager.getLogger(StudentServiceImpl.class);

	@Override
	public List<Student> findAll() {
		StudentDao dao = transaction.createDao(StudentDao.class);
		return dao.findAll();
	}

	@Override
	public Student findById(Integer identity) {
		StudentDao dao = transaction.createDao(StudentDao.class);
		return dao.read(identity);
	}

	@Override
	public void save(Student student) {
		StudentDao dao = transaction.createDao(StudentDao.class);
		logger.debug("In Student Service Save "+student.getId());
		if(student.getId()!=null) {
			dao.update(student);
		} else {
			student.setId(dao.save(student));
		}

	}

	@Override
	public void delete(Integer identity) {
		StudentDao dao = transaction.createDao(StudentDao.class);
		dao.delete(identity);

	}

}
