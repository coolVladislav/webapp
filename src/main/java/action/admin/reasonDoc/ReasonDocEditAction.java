package action.admin.reasonDoc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import action.admin.AdministratorAction;
import domain.ReasonDoc;
import exception.PersistentException;
import service.ReasonDocService;

public class ReasonDocEditAction extends AdministratorAction{
	private static Logger logger = LogManager.getLogger(ReasonDocEditAction.class);

	@Override
	public Forward exec(HttpServletRequest request, HttpServletResponse response) throws PersistentException {
		try {
			Integer id = (Integer)request.getAttribute("id");
			if(id == null) {
				id = Integer.parseInt(request.getParameter("id"));
			}
			ReasonDocService service = factory.getService(ReasonDocService.class);
			ReasonDoc reasonDoc = service.findById(id);
			logger.debug("In ReasonDoc Edit Action "+reasonDoc);
			if(reasonDoc != null) {
				request.setAttribute("reasonDoc", reasonDoc);
			}
		} catch(NumberFormatException e) {}
		return null;
	}

}
