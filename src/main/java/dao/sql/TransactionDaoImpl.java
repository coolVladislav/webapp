package dao.sql;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.Dao;
import dao.OrderDao;
import dao.OrderDocDao;
import dao.ReasonDao;
import dao.ReasonDocDao;
import dao.StudentAdditionalDao;
import dao.StudentDao;
import dao.StudentYearDao;
import dao.TransactionDao;
import dao.UserDao;




public class TransactionDaoImpl implements TransactionDao{
	
	private static Logger logger = LogManager.getLogger(TransactionDaoImpl.class);
	
	private static Map<Class<? extends Dao<?>>, Class<? extends DaoImpl>> classes = new ConcurrentHashMap<>();
	static {
		classes.put(OrderDao.class, OrderDaoImpl.class);
		classes.put(OrderDocDao.class, OrderDocDaoImpl.class);
		classes.put(ReasonDao.class, ReasonDaoImpl.class);
		classes.put(ReasonDocDao.class, ReasonDocDaoImpl.class);
		classes.put(StudentAdditionalDao.class, StudentAdditionalDaoImpl.class);
		classes.put(StudentDao.class, StudentDaoImpl.class);
		classes.put(StudentYearDao.class, StudentYearDaoImpl.class);
		classes.put(UserDao.class, UserDaoImpl.class);
	}
	
	private Connection connection;

	public TransactionDaoImpl(Connection connection) {
		this.connection = connection;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <Type extends Dao<?>> Type createDao(Class<Type> key) {
		Class<? extends DaoImpl> value = classes.get(key);
		if(value != null) {
			try {
				DaoImpl dao = value.newInstance();
				dao.setConnection(connection);
				return (Type)dao;
			} catch(InstantiationException | IllegalAccessException e) {
				logger.error("It is impossible to create data access object", e);
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public void commit() {
		try {
			connection.commit();
		} catch(SQLException e) {
			logger.error("It is impossible to commit transaction", e);
			e.printStackTrace();
		}
	}

	@Override
	public void rollback() {
		try {
			connection.rollback();
		} catch(SQLException e) {
			logger.error("It is impossible to rollback transaction", e);
			e.printStackTrace();
		}
		
	}

}
