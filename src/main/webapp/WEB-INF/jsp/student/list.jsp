<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<u:html title="Список студентов" message="${message}">
	<H2>Список студентов</H2>
	<TABLE>
		<TR>
			<TH>Фамилия</TH>
			<TH>Имя</TH>
			<TH>Отчество</TH>
		</TR>
		<c:url value="/student/edit.html" var="studentEditUrl"/>
		<c:forEach items="${students}" var="student">
			
			<TR onclick="submitFormById('form-${student.id}')">
				<TD>
					${student.lastName}
					<FORM id="form-${student.id}" action="${studentEditUrl}"  method="post">
						<INPUT type="hidden" name="id" value="${student.id}">
					</FORM>
				</TD>
				<TD>${student.firstName}</TD>
				<TD>${student.patronymic}</TD>
			</TR>
		</c:forEach>
	</TABLE>
	<c:url value="/student/edit.html" var="studentEditUrl"/>
	<FORM action="${studentEditUrl}" method="post">
		<BUTTON type="submit">Добавить</BUTTON>
	</FORM>
</u:html>