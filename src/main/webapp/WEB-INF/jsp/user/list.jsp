<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<u:html title="Список юзеров" message="${message}">
	<H2>Список юзеров</H2>
	<TABLE>
		<TR>
			<TH>Логин</TH>
			<TH>Пароль</TH>
			<TH>Роль</TH>
		</TR>
		
		<c:forEach items="${users}" var="user">
			<TR>
				<TD>${user.login}</TD>
				<TD>${user.password}</TD>
				<TD>${user.role}</TD>
			</TR>
		</c:forEach>
	</TABLE>
	<c:url value="/user/edit.html" var="userEditUrl"/>
	<FORM action="${userEditUrl}" method="post">
		<BUTTON type="submit">Добавить</BUTTON>
	</FORM>
</u:html>