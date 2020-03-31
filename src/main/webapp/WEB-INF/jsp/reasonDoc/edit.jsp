<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<c:choose>
	<c:when test="${not empty reasonDoc}">
		<c:set var="number" value="${reasonDoc.number}"/>
		<c:set var="note" value="${reasonDoc.docName}"/>
		<c:set var="title" value="ReasonDoc ${reasonDoc.number} ${reasonDoc.docName} "/>
	</c:when>
	<c:otherwise>
		<c:set var="title" value="New ReasonDoc"/>
	</c:otherwise>
</c:choose>
<u:html title="${title}" validator="validator-of-edit-reasonDoc-form.js">
	<H2>${title}</H2>
	<c:url value="/reasonDoc/save.html" var="reasonDocSaveUrl"/>
	<FORM action="${reasonDocSaveUrl}" method="post" onsubmit="return validateEditReasonDoc(this)">
		<c:if test="${not empty reasonDoc}">
			<INPUT type="text" name="id" value="${reasonDoc.id}">
		</c:if>
		<LABEL for="number">Номер:</LABEL>
		<INPUT type="text" id="number" name="number" value="${number}">
		<LABEL for="docName">Описание:</LABEL>
		<INPUT type="text" id="docName" name="docName" value="${docName}">
		<BUTTON type="submit">Сохранить</BUTTON>
		<BUTTON type="reset">Сбросить</BUTTON>
	</FORM>
	<c:if test="${not empty reasonDoc}">
		<c:url value="/reasonDoc/delete.html" var="reasonDocDeleteUrl"/>
		<FORM action="${reasonDocDeleteUrl}" method="post" id="form-delete" onsubmit="return confirmation(this, 'Вы уверены, что хотите удалить ReasonDoc?')">
			<INPUT type="hidden" name="id" value="${reasonDoc.id}">
			<BUTTON type="submit" onclick="submitFormById('form-delete')">Удалить</BUTTON>
		</FORM>
	</c:if>
</u:html>