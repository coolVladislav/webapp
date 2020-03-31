package dao;

import java.util.List;
import domain.Entity;


public interface Dao<Type extends Entity> {
	Integer save(Type entity);

	Type read(Integer identity);

	void update(Type entity);

	void delete(Integer identity);
	
	public abstract List<Type> findAll();
}
