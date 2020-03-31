package controller;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import action.Action;
import action.LoginAction;
import action.LogoutAction;
import action.MainAction;
import action.ProfileEditAction;
import action.ProfileSaveAction;
import action.admin.UserDeleteAction;
import action.admin.UserEditAction;
import action.admin.UserListAction;
import action.admin.UserSaveAction;
import action.admin.order.OrderDeleteAction;
import action.admin.order.OrderEditAction;
import action.admin.order.OrderListAction;
import action.admin.order.OrderSaveAction;
import action.admin.orderDoc.OrderDocDeleteAction;
import action.admin.orderDoc.OrderDocEditAction;
import action.admin.orderDoc.OrderDocListAction;
import action.admin.orderDoc.OrderDocSaveAction;
import action.admin.reason.ReasonDeleteAction;
import action.admin.reason.ReasonEditAction;
import action.admin.reason.ReasonListAction;
import action.admin.reason.ReasonSaveAction;
import action.admin.reasonDoc.ReasonDocDeleteAction;
import action.admin.reasonDoc.ReasonDocEditAction;
import action.admin.reasonDoc.ReasonDocListAction;
import action.admin.reasonDoc.ReasonDocSaveAction;
import action.admin.student.StudentDeleteAction;
import action.admin.student.StudentEditAction;
import action.admin.student.StudentListAction;
import action.admin.student.StudentSaveAction;
import action.admin.studentAdditional.StudentAdditionalDeleteAction;
import action.admin.studentAdditional.StudentAdditionalEditAction;
import action.admin.studentAdditional.StudentAdditionalListAction;
import action.admin.studentAdditional.StudentAdditionalSaveAction;
import action.admin.studentYear.StudentYearDeleteAction;
import action.admin.studentYear.StudentYearEditAction;
import action.admin.studentYear.StudentYearListAction;
import action.admin.studentYear.StudentYearSaveAction;

public class ActionFromUriFilter implements Filter {
	private static Logger logger = LogManager.getLogger(ActionFromUriFilter.class);

	private static Map<String, Class<? extends Action>> actions = new ConcurrentHashMap<>();

	static {
		actions.put("/", MainAction.class);
		actions.put("/index", MainAction.class);
		
		actions.put("/order/list", OrderListAction.class);
		actions.put("/order/edit", OrderEditAction.class);
		actions.put("/order/save", OrderSaveAction.class);
		actions.put("/order/delete", OrderDeleteAction.class);
		
		actions.put("/orderDoc/list", OrderDocListAction.class);
		actions.put("/orderDoc/edit", OrderDocEditAction.class);
		actions.put("/orderDoc/save", OrderDocSaveAction.class);
		actions.put("/orderDoc/delete", OrderDocDeleteAction.class);
		
		actions.put("/reason/list", ReasonListAction.class);
		actions.put("/reason/edit", ReasonEditAction.class);
		actions.put("/reason/save", ReasonSaveAction.class);
		actions.put("/reason/delete", ReasonDeleteAction.class);
		
		actions.put("/reasonDoc/list", ReasonDocListAction.class);
		actions.put("/reasonDoc/edit", ReasonDocEditAction.class);
		actions.put("/reasonDoc/save", ReasonDocSaveAction.class);
		actions.put("/reasonDoc/delete", ReasonDocDeleteAction.class);
		
		actions.put("/student/list", StudentListAction.class);
		actions.put("/student/edit", StudentEditAction.class);
		actions.put("/student/save", StudentSaveAction.class);
		actions.put("/student/delete", StudentDeleteAction.class);
		
		actions.put("/studentAdditional/list", StudentAdditionalListAction.class);
		actions.put("/studentAdditional/edit", StudentAdditionalEditAction.class);
		actions.put("/studentAdditional/save", StudentAdditionalSaveAction.class);
		actions.put("/studentAdditional/delete", StudentAdditionalDeleteAction.class);
		
		actions.put("/studentYear/list", StudentYearListAction.class);
		actions.put("/studentYear/edit", StudentYearEditAction.class);
		actions.put("/studentYear/save", StudentYearSaveAction.class);
		actions.put("/studentYear/delete", StudentYearDeleteAction.class);

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if(request instanceof HttpServletRequest) {
			HttpServletRequest httpRequest = (HttpServletRequest)request;
			String contextPath = httpRequest.getContextPath();
			String uri = httpRequest.getRequestURI();
			logger.debug(String.format("Starting of processing of request for URI \"%s\", \"%s\" contexPath", uri, contextPath));
			int beginAction = contextPath.length();
			int endAction = uri.lastIndexOf('.');
			String actionName;
			if(endAction >= 0) {
				actionName = uri.substring(beginAction, endAction);
			} else {
				actionName = uri.substring(beginAction);
			}
			Class<? extends Action> actionClass = actions.get(actionName);
			try {
				Action action = actionClass.newInstance();
				action.setName(actionName);
				httpRequest.setAttribute("action", action);
				chain.doFilter(request, response);
			} catch (InstantiationException | IllegalAccessException | NullPointerException e) {
				logger.error("It is impossible to create action handler object", e);
				httpRequest.setAttribute("error", String.format("Запрошенный адрес %s не может быть обработан сервером", uri));
				httpRequest.getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
			}
		} else {
			logger.error("It is impossible to use HTTP filter");
			request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
		}
	}

	@Override
	public void destroy() {}
}
