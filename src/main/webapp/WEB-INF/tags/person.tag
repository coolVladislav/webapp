<%@tag language="java" pageEncoding="UTF-8"%>
<%@attribute name="value" required="true" rtexprvalue="true" type="domain.Student"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

${value.last_name}&nbsp;${fn:substring(value.first_name, 0, 1)}.&nbsp;${fn:substring(value.patronymic, 0, 1)}.