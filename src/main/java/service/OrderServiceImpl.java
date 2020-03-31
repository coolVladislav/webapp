package service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.OrderDao;
import dao.ReasonDao;
import dao.StudentYearDao;
import domain.Order;
import domain.Reason;
import domain.StudentYear;

public class OrderServiceImpl extends ServiceImpl implements OrderService {
	static final Logger logger = LogManager.getLogger(OrderServiceImpl.class);

	@Override
	public List<Order> findAll() {
		OrderDao dao = transaction.createDao(OrderDao.class);
		List<Order> list = dao.findAll();
		for(Order tmp:list) {
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
	public Order findById(Integer identity) {
		OrderDao dao = transaction.createDao(OrderDao.class);
		return dao.read(identity);
	}

	@Override
	public void save(Order order) {
		OrderDao dao = transaction.createDao(OrderDao.class);
		logger.debug("In Order Service save: "+order.getId());
		if(order.getId() != null) {
			dao.update(order);
		} else {
			order.setId(dao.save(order));
		}

	}

	@Override
	public void delete(Integer identity) {
		OrderDao dao = transaction.createDao(OrderDao.class);
		dao.delete(identity);
	}

}
