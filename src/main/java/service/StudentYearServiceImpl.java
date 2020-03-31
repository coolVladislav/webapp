package service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.StudentDao;
import dao.StudentYearDao;
import domain.Student;
import domain.StudentYear;

public class StudentYearServiceImpl extends ServiceImpl implements StudentYearService {
	static final Logger logger = LogManager.getLogger(StudentYearServiceImpl.class);	
	@Override
	public List<StudentYear> findAll() {
		StudentYearDao dao = transaction.createDao(StudentYearDao.class);
		List<StudentYear> list = dao.findAll();
		
		for(StudentYear tmp:list) {
			StudentDao stDao = transaction.createDao(StudentDao.class);
			Student student  = stDao.read(tmp.getStudent().getId());
			tmp.setStudent(student);
 		}
		
		return list;
	}

	@Override
	public StudentYear findById(Integer identity) {
		StudentYearDao dao = transaction.createDao(StudentYearDao.class);
		return dao.read(identity);
	}

	@Override
	public void save(StudentYear studentYear) {
		StudentYearDao dao = transaction.createDao(StudentYearDao.class);
		logger.debug("In StudentYear Save Service "+studentYear.getId());
		if(studentYear.getId()!=null) {
			dao.update(studentYear);
		} else {
			studentYear.setId(dao.save(studentYear));
		}
	}

	@Override
	public void delete(Integer identity) {
		StudentYearDao dao = transaction.createDao(StudentYearDao.class);
		dao.delete(identity);		
	}
}
