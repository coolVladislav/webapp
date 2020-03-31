package service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.OrderDao;
import dao.OrderDocDao;
import dao.ReasonDocDao;
import domain.Order;
import domain.OrderDoc;
import domain.ReasonDoc;

public class OrderDocServiceImpl extends ServiceImpl implements OrderDocService {
	static final Logger logger = LogManager.getLogger(OrderDocServiceImpl.class);

	@Override
	public List<OrderDoc> findAll() {
		OrderDocDao dao = transaction.createDao(OrderDocDao.class);
		List<OrderDoc> list = dao.findAll();
		for(OrderDoc tmp:list) {
			OrderDao orderDao = transaction.createDao(OrderDao.class);
			Order order = orderDao.read(tmp.getOrder().getId());
			tmp.setOrder(order);
			
			ReasonDocDao reDao = transaction.createDao(ReasonDocDao.class);
			ReasonDoc reasonDoc = reDao.read(tmp.getReasonDoc().getId());
			tmp.setReasonDoc(reasonDoc);
		}
		return list;
	}

	@Override
	public OrderDoc findById(Integer identity) {
		OrderDocDao dao = transaction.createDao(OrderDocDao.class);
		return dao.read(identity);
	}

	@Override
	public void save(OrderDoc orderDoc) {
		OrderDocDao dao = transaction.createDao(OrderDocDao.class);
		logger.debug("In OrderDoc Service Save "+orderDoc.getIsAccepted());
		if(orderDoc.getId() != null) {
			dao.update(orderDoc);
		} else {
			orderDoc.setId(dao.save(orderDoc));
		}
	}

	@Override
	public void delete(Integer identity) {
		OrderDocDao dao = transaction.createDao(OrderDocDao.class);
		dao.delete(identity);
	}
}
