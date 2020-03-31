<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<u:html title="Список заявок" message="${message}">
	<H2>Список заявок</H2>
	<TABLE>
		<TR>
			<TH>Год</TH>
			<TH>Причина</TH>
			<TH>Дата</TH>
		</TR>
		<c:url value="/order/edit.html" var="orderEditUrl"/>
		<c:forEach items="${orders}" var="order">
			<TR onclick="submitFormById('form-${order.id}')">
				<TD>
					${order.studentYear.id}
					<FORM id="form-${order.id}" action="${orderEditUrl}"  method="post">
						<INPUT type="hidden" name="id" value="${order.id}">
					</FORM>
				</TD>
				<TD>${order.reason.id}</TD>
				<TD>${order.dateOfOrder}</TD>
			</TR>
		</c:forEach>
	</TABLE>
	<c:url value="/order/edit.html" var="orderEditUrl"/>
	<FORM action="${orderEditUrl}" method="post">
		<BUTTON type="submit">Добавить</BUTTON>
	</FORM>
</u:html>