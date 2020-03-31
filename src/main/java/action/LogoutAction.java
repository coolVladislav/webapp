package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import domain.User;
import exception.PersistentException;

public class LogoutAction extends AuthorizedUserAction {
	private static Logger logger = LogManager.getLogger(LogoutAction.class);

	@Override
	public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		User user = getAuthorizedUser();
		logger.info(String.format("user \"%s\" is logged out", user.getLogin()));
		request.getSession(false).invalidate();
		return new Forward("/login.html");
	}
}
