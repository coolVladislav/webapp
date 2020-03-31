package action.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.UserService;
import domain.User;
import exception.PersistentException;

public class UserListAction extends AdministratorAction {
	@Override
	public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		UserService service = factory.getService(UserService.class);
		List<User> users = service.findAll();
		request.setAttribute("users", users);
		return null;
	}
}
