<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<c:choose>
	<c:when test="${not empty reason}">
		<c:set var="number" value="${reason.number}"/>
		<c:set var="note" value="${reason.note}"/>
		<c:set var="max_coef" value="${reason.maxCoef}"/>
		<c:set var="title" value="Reason ${reason.number} ${reason.note}"/>
	</c:when>
	<c:otherwise>
		<c:set var="title" value="New Reason"/>
	</c:otherwise>
</c:choose>
<u:html title="${title}" validator="validator-of-edit-reason-form.js">
	<H2>${title}</H2>
	<c:url value="/reason/save.html" var="reasonSaveUrl"/>
	<FORM action="${reasonSaveUrl}" method="post" onsubmit="return validateEditReason(this)">
		<c:if test="${not empty reason}">
			<INPUT type="text" name="id" value="${reason.id}">
		</c:if>
		<LABEL for="number">Номер:</LABEL>
		<INPUT type="text" id="number" name="number" value="${number}">
		<LABEL for="note">Описание:</LABEL>
		<INPUT type="text" id="note" name="note" value="${note}">
		<LABEL for="max_coef">Максимальный коефициент в б.в.:</LABEL>
		<INPUT type="text" id="max_coef" name="max_coef" value="${max_coef}">
		<BUTTON type="submit">Сохранить</BUTTON>
		<BUTTON type="reset">Сбросить</BUTTON>
	</FORM>
	<c:if test="${not empty reason}">
		<c:url value="/reason/delete.html" var="reasonDeleteUrl"/>
		<FORM action="${reasonDeleteUrl}" method="post" id="form-delete" onsubmit="return confirmation(this, 'Вы уверены, что хотите удалить Reason?')">
			<INPUT type="hidden" name="id" value="${reason.id}">
			<BUTTON type="submit" onclick="submitFormById('form-delete')">Удалить</BUTTON>
		</FORM>
	</c:if>
</u:html>