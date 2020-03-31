package controller;

import java.io.IOException;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import domain.Role;
import domain.User;
import action.Action;
import action.MainAction;

public class SecurityFilter implements Filter {
	private static Logger logger = LogManager.getLogger(SecurityFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if(request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
			HttpServletRequest httpRequest = (HttpServletRequest)request;
			HttpServletResponse httpResponse = (HttpServletResponse)response;
			Action action = (Action)httpRequest.getAttribute("action");
			Set<Role> allowRoles = action.getAllowRoles();
			String userName = "unauthorized user";
			HttpSession session = httpRequest.getSession(false);
			User user = null;
			if(session != null) {
				user = (User)session.getAttribute("authorizedUser");
				action.setAuthorizedUser(user);
				String errorMessage = (String)session.getAttribute("SecurityFilterMessage");
				if(errorMessage != null) {
					httpRequest.setAttribute("message", errorMessage);
					session.removeAttribute("SecurityFilterMessage");
				}
			}
			boolean canExecute = allowRoles == null;
			if(user != null) {
				userName = "\"" + user.getLogin() + "\" user";
				canExecute = canExecute || allowRoles.contains(user.getRole());
			}
			if(canExecute) {
				chain.doFilter(request, response);
			} else {
				logger.info(String.format("Trying of %s access to forbidden resource \"%s\"", userName, action.getName()));
				if(session != null && action.getClass() != MainAction.class) {
					session.setAttribute("SecurityFilterMessage", "Доступ запрещён");
				}
				httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.html");
			}
		} else {
			logger.error("It is impossible to use HTTP filter");
			request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
		}
	}

	@Override
	public void destroy() {}
}
