<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<c:choose>
	<c:when test="${not empty student}">
		<c:set var="id" value="${student.id}"/>
		<c:set var="last_name" value="${student.lastName}"/>
		<c:set var="first_name" value="${student.firstName}"/>
		<c:set var="patronymic" value="${student.patronymic}"/>
		<c:set var="title" value="Student ${student.lastName} ${student.firstName} ${student.patronymic}"/>
	</c:when>
	<c:otherwise>
		<c:set var="title" value="New Student"/>
	</c:otherwise>
</c:choose>
<u:html title="${title}" validator="validator-of-edit-student-form.js">
	<H2>${title}</H2>
	<c:url value="/student/save.html" var="studentSaveUrl"/>
	<FORM action="${studentSaveUrl}" method="post" onsubmit="return validateEditStudent(this)">
		<c:if test="${not empty student}">
			<INPUT type="hidden" name="id" value="${student.id}">
		</c:if>
		<LABEL for="last_name">Фамилия:</LABEL>
		<INPUT type="text" id="last_name" name="last_name" value="${student.lastName}">
		<LABEL for="first_name">Имя:</LABEL>
		<INPUT type="text" id="first_name" name="first_name" value="${student.firstName}">
		<LABEL for="patronymic">Отчество:</LABEL>
		<INPUT type="text" id="patronymic" name="patronymic" value="${student.patronymic}">
		<BUTTON type="submit">Сохранить</BUTTON>
		<BUTTON type="reset">Сбросить</BUTTON>
	</FORM>
	<c:if test="${not empty student}">
		<c:url value="/student/delete.html" var="studentDeleteUrl"/>
		<FORM action="${studentDeleteUrl}" method="post" id="form-delete" onsubmit="return confirmation(this, 'Вы уверены, что хотите удалить студента?')">
			<INPUT type="hidden" name="id" value="${student.id}">
			<BUTTON type="submit">Удалить</BUTTON>
		</FORM>
	</c:if>
</u:html>