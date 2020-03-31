package service;

import java.util.List;
import domain.ReasonDoc;



public interface ReasonDocService extends Service{
	List<ReasonDoc> findAll();

	ReasonDoc findById(Integer identity);

	void save(ReasonDoc reasonDoc);

	void delete(Integer identity);
}
