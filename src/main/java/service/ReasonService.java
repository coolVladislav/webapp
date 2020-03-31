package service;

import java.util.List;

import domain.Reason;

public interface ReasonService extends Service{
	List<Reason> findAll();

	Reason findById(Integer identity);

	void save(Reason reason);

	void delete(Integer identity);
}
