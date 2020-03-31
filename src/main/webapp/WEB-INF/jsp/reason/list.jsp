<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<u:html title="Список причин" message="${message}">
	<H2>Список причин</H2>
	<TABLE>
		<TR>
			<TH>Номер</TH>
			<TH>Описание</TH>
			<TH>Максимальный коефициент в б.в.</TH>
		</TR>
		<c:url value="/reason/edit.html" var="reasonEditUrl"/>
		<c:forEach items="${reasons}" var="reason">
			<TR onclick="submitFormById('form-${reason.id}')">
				<TD>
					${reason.number}
					<FORM id="form-${reason.id}" action="${reasonEditUrl}"  method="post">
						<INPUT type="hidden" name="id" value="${reason.id}">
					</FORM>
				</TD>
				<TD>${reason.note}</TD>
				<TD>${reason.maxCoef}</TD>
			</TR>
		</c:forEach>
	</TABLE>
	<c:url value="/reason/edit.html" var="reasonEditUrl"/>
	<FORM action="${reasonEditUrl}" method="post">
		<BUTTON type="submit">Добавить</BUTTON>
	</FORM>
</u:html>