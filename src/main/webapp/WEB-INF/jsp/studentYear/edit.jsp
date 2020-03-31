<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<c:choose>
	<c:when test="${not empty studentYear}">
		<c:set var="course" value="${studentYear.course}"/>
		<c:set var="group" value="${studentYear.group}"/>
		<c:set var="isFreeibe" value="${studentYear.isFreeibe}"/>
		<c:if test="${not empty studentYear.student}">
			<c:set var="selectedStudentId" value="${studentYear.student.id}"/>
		</c:if>
		<c:set var="year" value="${studentYear.year}"/>
		<c:set var="title" value="StudentYear ${studentYear.course} ${studentYear.group}"/>
	</c:when>
	<c:otherwise>
		<c:set var="title" value="New StudentYear"/>
	</c:otherwise>
</c:choose>
<u:html title="${title}" validator="validator-of-edit-studentYear-form.js">
	<H2>${title}</H2>
	<c:url value="/studentYear/save.html" var="studentYearSaveUrl"/>
	<FORM action="${studentYearSaveUrl}" method="post" onsubmit="return validateEditStudentYear(this)">
		<c:if test="${not empty studentYear}">
			<INPUT type="text" name="id" value="${studentYear.id}">
		</c:if>
		<LABEL for="course">Курс:</LABEL>
		<INPUT type="text" id="course" name="course" value="${course}">
		<LABEL for="group">Группа:</LABEL>
		<INPUT type="text" id="group" name="group" value="${group}">		
		<LABEL for="student">Студент:</LABEL>
		<SELECT id="student" name="student">
			
			<c:forEach items="${students}" var="student">
				
				<c:choose>
					<c:when test="${student.id == selectedStudentId}">
						<c:set var="selected" value="selected"/>
					</c:when>
					<c:otherwise>
						<c:remove var="selected"/>
					</c:otherwise>
				</c:choose>
				<OPTION value="${student.id}" ${selected}>${student.lastName}</OPTION>
			</c:forEach>
		</SELECT>
		<LABEL for="year">Год:</LABEL>
		<INPUT type="text" id="year" name="year" value="${year}">
		<BUTTON type="submit">Сохранить</BUTTON>
		<BUTTON type="reset">Сбросить</BUTTON>
	</FORM>
	<c:if test="${not empty studentYear}">
		<c:url value="/studentYear/delete.html" var="studentYearDeleteUrl"/>
		<FORM action="${studentYearDeleteUrl}" method="post" id="form-delete" onsubmit="return confirmation(this, 'Вы уверены, что хотите удалить сдудент+год?')">
			<INPUT type="hidden" name="id" value="${studentYear.id}">
			<BUTTON type="submit">Удалить</BUTTON>
		</FORM>
	</c:if>
</u:html>