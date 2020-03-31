package service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.ReasonDao;
import dao.StudentAdditionalDao;
import dao.StudentYearDao;
import domain.Reason;
import domain.StudentAdditional;
import domain.StudentYear;

public class StudentAdditionalServiceImpl extends ServiceImpl implements StudentAdditionalService {
	private static Logger logger = LogManager.getLogger(StudentAdditionalServiceImpl.class);

	@Override
	public List<StudentAdditional> findAll() {
		StudentAdditionalDao dao = transaction.createDao(StudentAdditionalDao.class);
		List<StudentAdditional> list = dao.findAll();
		for(StudentAdditional tmp:list) {
			StudentYearDao stYeDao = transaction.createDao(StudentYearDao.class);
			StudentYear studentYear = stYeDao.read(tmp.getStudentYear().getId());
			tmp.setStudentYear(studentYear);
			
			ReasonDao reDao = transaction.createDao(ReasonDao.class);
			Reason reason = reDao.read(tmp.getReason().getId());
			tmp.setReason(reason);
		}
		return list;
	}

	@Override
	public StudentAdditional findById(Integer identity) {
		StudentAdditionalDao dao = transaction.createDao(StudentAdditionalDao.class);
		return dao.read(identity);
	}

	@Override
	public void save(StudentAdditional studentAdditional) {
		logger.debug("In StudentAdditional Service save "+studentAdditional);
		StudentAdditionalDao dao = transaction.createDao(StudentAdditionalDao.class);
		if(studentAdditional.getId()!=null) {
			dao.update(studentAdditional);
		} else {
			studentAdditional.setId(dao.save(studentAdditional));
		}
	}

	@Override
	public void delete(Integer identity) {
		StudentAdditionalDao dao = transaction.createDao(StudentAdditionalDao.class);
		dao.delete(identity);
	}	

}
