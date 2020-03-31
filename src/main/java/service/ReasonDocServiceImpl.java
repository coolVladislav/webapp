package service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.ReasonDocDao;
import domain.ReasonDoc;

public class ReasonDocServiceImpl extends ServiceImpl implements ReasonDocService {
	private static Logger logger = LogManager.getLogger(ReasonDocServiceImpl.class);

	@Override
	public List<ReasonDoc> findAll() {
		ReasonDocDao dao = transaction.createDao(ReasonDocDao.class);
		return dao.findAll();
	}

	@Override
	public ReasonDoc findById(Integer identity) {
		ReasonDocDao dao = transaction.createDao(ReasonDocDao.class);
		return dao.read(identity);
	}

	@Override
	public void save(ReasonDoc reasonDoc) {
		ReasonDocDao dao = transaction.createDao(ReasonDocDao.class);
		logger.debug("In ReasonDoc Service Save "+reasonDoc.getId());
		if(reasonDoc.getId()!=null) {
			dao.update(reasonDoc);
		} else {
			reasonDoc.setId(dao.save(reasonDoc));
		}
	}

	@Override
	public void delete(Integer identity) {
		ReasonDocDao dao = transaction.createDao(ReasonDocDao.class);
		dao.delete(identity);
	}

}
