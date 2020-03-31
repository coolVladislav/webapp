package service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.ReasonDao;
import domain.Reason;

public class ReasonServiceImpl extends ServiceImpl implements ReasonService {
	private static Logger logger = LogManager.getLogger(ReasonServiceImpl.class);

	@Override
	public List<Reason> findAll() {
		ReasonDao dao = transaction.createDao(ReasonDao.class);
		return dao.findAll();
	}

	@Override
	public Reason findById(Integer identity) {
		ReasonDao dao = transaction.createDao(ReasonDao.class);
		return dao.read(identity);
	}

	@Override
	public void save(Reason reason) {
		ReasonDao dao = transaction.createDao(ReasonDao.class);
		logger.debug("In Reason Service Save "+reason.getId());
		if(reason.getId()!=null) {
			dao.update(reason);
		} else {
			reason.setId(dao.save(reason));
		}

	}

	@Override
	public void delete(Integer identity) {
		ReasonDao dao = transaction.createDao(ReasonDao.class);
		dao.delete(identity);
	}

}
