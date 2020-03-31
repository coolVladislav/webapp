package controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import service.ServiceFactory;
import service.ServiceFactoryImpl;
import action.Action;
import action.ActionManager;
import action.ActionManagerFactory;
import dao.pool.ConnectionPool;
import dao.sql.TransactionFactoryDaoImpl;
import exception.PersistentException;

public class DispatcherServlet extends HttpServlet {
	static final Logger logger = LogManager.getLogger(DispatcherServlet.class);	

	public static final String DB_DRIVER_CLASS = "org.postgresql.Driver";
	public static final String DB_URL = "jdbc:postgresql://localhost:5432/diplom?useUnicode=true&characterEncoding=UTF-8";
	public static final String DB_USER = "postgres";
	public static final String DB_PASSWORD = "postgres";
	public static final int DB_POOL_START_SIZE = 10;
	public static final int DB_POOL_MAX_SIZE = 1000;
	public static final int DB_POOL_CHECK_CONNECTION_TIMEOUT = 0;

	public void init() {
		try {			
			ConnectionPool.getInstance().init(DB_DRIVER_CLASS, DB_URL, DB_USER, DB_PASSWORD, DB_POOL_START_SIZE, DB_POOL_MAX_SIZE, DB_POOL_CHECK_CONNECTION_TIMEOUT);
		} catch(PersistentException e) {
			logger.error("It is impossible to initialize application", e);
			destroy();
		}
	}

	public ServiceFactory getFactory() throws PersistentException {
		return new ServiceFactoryImpl(new TransactionFactoryDaoImpl());
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		process(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Action action = (Action)request.getAttribute("action");
		try {
			HttpSession session = request.getSession(false);
			if(session != null) {
				@SuppressWarnings("unchecked")
				Map<String, Object> attributes = (Map<String, Object>)session.getAttribute("redirectedData");
				if(attributes != null) {
					for(String key : attributes.keySet()) {
						request.setAttribute(key, attributes.get(key));
					}
					session.removeAttribute("redirectedData");
				}
			}
			ActionManager actionManager = ActionManagerFactory.getManager(getFactory());
			Action.Forward forward = actionManager.execute(action, request, response);
			actionManager.close();
			if(session != null && forward != null && !forward.getAttributes().isEmpty()) {
				session.setAttribute("redirectedData", forward.getAttributes());
			}
			String requestedUri = request.getRequestURI();
			if(forward != null && forward.isRedirect()) {
				String redirectedUri = request.getContextPath() + forward.getForward();
				logger.debug(String.format("Request for URI \"%s\" id redirected to URI \"%s\", action \"%s\"", requestedUri, redirectedUri, action));
				response.sendRedirect(redirectedUri);
			} else {
				String jspPage;
				if(forward != null) {
					jspPage = forward.getForward();
				} else {
					jspPage = action.getName() + ".jsp";
				}
				jspPage = "/WEB-INF/jsp" + jspPage;
				logger.debug(String.format("Request for URI \"%s\" is forwarded to JSP \"%s\"", requestedUri, jspPage));
				getServletContext().getRequestDispatcher(jspPage).forward(request, response);
			}
		} catch(PersistentException e) {
			logger.error("It is impossible to process request", e);
			request.setAttribute("error", "Ошибка обработки данных");
			getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
		}
	}
}
