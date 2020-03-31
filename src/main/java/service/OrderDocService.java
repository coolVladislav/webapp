package service;

import java.util.List;
import domain.OrderDoc;

public interface OrderDocService extends Service{
	List<OrderDoc> findAll();

	OrderDoc findById(Integer identity);

	void save(OrderDoc orderDoc);

	void delete(Integer identity);
}
