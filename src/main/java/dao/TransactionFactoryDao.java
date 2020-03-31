package dao;

public interface TransactionFactoryDao {
	TransactionDao createTransaction();

	void close();
}
