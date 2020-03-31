package dao;

public interface TransactionDao {
	<Type extends Dao<?>> Type createDao(Class<Type> key);

	void commit();

	void rollback();
}