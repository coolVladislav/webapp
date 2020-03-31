package dao.sql;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.TransactionDao;
import dao.TransactionFactoryDao;
import dao.sql.TransactionDaoImpl;
import exception.PersistentException;
import dao.pool.ConnectionPool;


public class TransactionFactoryDaoImpl implements TransactionFactoryDao{
	
	private static Logger logger = LogManager.getLogger(TransactionFactoryDaoImpl.class);
	private Connection connection;
	
	public TransactionFactoryDaoImpl() throws PersistentException {
		connection = ConnectionPool.getInstance().getConnection();
		try {
			connection.setAutoCommit(false);
		} catch(SQLException e) {
			logger.error("It is impossible to turn off autocommiting for database connection", e);
			throw new PersistentException(e);
		}
	}

	@Override
	public TransactionDao createTransaction() {
		return new TransactionDaoImpl(connection);
	}

	@Override
	public void close() {
		try {
			connection.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

}
