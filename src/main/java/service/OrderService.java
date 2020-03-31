package service;

import java.util.List;
import domain.Order;



public interface OrderService extends Service{
	List<Order> findAll();

	Order findById(Integer identity);

	void save(Order order);

	void delete(Integer identity);
}
