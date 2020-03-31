package service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.TransactionDao;
import dao.TransactionFactoryDao;
import dao.sql.OrderDaoImpl;
import exception.PersistentException;

public class ServiceFactoryImpl implements ServiceFactory{
	
	private static Logger logger = LogManager.getLogger(ServiceFactoryImpl.class);

	private static Map<Class<? extends Service>, Class<? extends ServiceImpl>> SERVICES = new ConcurrentHashMap<>();
	
	static {
		SERVICES.put(OrderService.class, OrderServiceImpl.class);
		SERVICES.put(OrderDocService.class, OrderDocServiceImpl.class);
		SERVICES.put(ReasonService.class, ReasonServiceImpl.class);
		SERVICES.put(ReasonDocService.class, ReasonDocServiceImpl.class);
		SERVICES.put(StudentAdditionalService.class, StudentAdditionalServiceImpl.class);
		SERVICES.put(StudentService.class, StudentServiceImpl.class);
		SERVICES.put(StudentYearService.class, StudentYearServiceImpl.class);
		SERVICES.put(UserService.class, UserServiceImpl.class);
	}

	private TransactionFactoryDao factory;

	public ServiceFactoryImpl(TransactionFactoryDao factory){
		this.factory = factory;
	}
	@SuppressWarnings("unchecked")
	@Override
	public <Type extends Service> Type getService(Class<Type> key) {
		Class<? extends ServiceImpl> value = SERVICES.get(key);
		if(value != null) {
			try {
				ClassLoader classLoader = value.getClassLoader();
				Class<?>[] interfaces = {key};
				TransactionDao transaction = factory.createTransaction();
				ServiceImpl service = value.newInstance();
				service.setTransaction(transaction);
				InvocationHandler handler = new ServiceInvocationHandlerImpl(service);
				return (Type)Proxy.newProxyInstance(classLoader, interfaces, handler);
			} catch(InstantiationException | IllegalAccessException e) {
				logger.error("It is impossible to instance service class", e);
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public void close() {
		factory.close();
	}
}
