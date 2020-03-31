<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<c:choose>
	<c:when test="${not empty studentAdditional}">
		<c:set var="studentYear" value="${studentAdditional.studentYear}"/>
		<c:if test="${not empty studentAdditional.studentYear}">
			<c:set var="selectedStudentYearId" value="${studentAdditional.studentYear.id}"/>
		</c:if>
		<c:set var="sum" value="${studentAdditional.sum}"/>
		<c:set var="reason" value="${studentAdditional.reason}"/>
		<c:if test="${not empty studentAdditional.reason}">
			<c:set var="selectedReasonId" value="${studentAdditional.reason.id}"/>
		</c:if>
		<c:set var="month" value="${studentAdditional.month}"/>
		<c:set var="isProved" value="${studentAdditional.isProved}"/>
		<c:set var="dateOfProve" value="${studentAdditional.dateOfProve}"/>
		<c:set var="title" value="StudentAdditional ${studentAdditional.studentYear} ${studentAdditional.sum}"/>
	</c:when>
	<c:otherwise>
		<c:set var="title" value="New StudentAdditional"/>
	</c:otherwise>
</c:choose>
<u:html title="${title}" validator="validator-of-edit-studentAdditional-form.js">
	<H2>${title}</H2>
	<c:url value="/studentAdditional/save.html" var="studentAdditionalSaveUrl"/>
	<FORM action="${studentAdditionalSaveUrl}" method="post" onsubmit="return validateEditStudentAdditional(this)">
		<c:if test="${not empty studentAdditional}">
			<INPUT type="text" name="identity" value="${studentAdditional.id}">
		</c:if>
		<LABEL for="studentYear">StudentYear:</LABEL>
		<SELECT id="studentYear" name="studentYear">
			<c:forEach items="${studentYears}" var="studentYear">
				<c:choose>
					<c:when test="${studentYear.id == selectedStudentYearId}">
						<c:set var="selected" value="selected"/>
					</c:when>
					<c:otherwise>
						<c:remove var="selected"/>
					</c:otherwise>
				</c:choose>
				<OPTION value="${studentYear.id}" ${selected}>${studentYear.id}</OPTION>
			</c:forEach>
		</SELECT>
		<LABEL for="sum">Сумма:</LABEL>
		<INPUT type="text" id="sum" name="sum" value="${sum}">
		<LABEL for="reason">Причина:</LABEL>
		<SELECT id="reason" name="reason">
			<c:forEach items="${reasons}" var="reason">
				<c:choose>
					<c:when test="${reason.id == selectedReasonId}">
						<c:set var="selected" value="selected"/>
					</c:when>
					<c:otherwise>
						<c:remove var="selected"/>
					</c:otherwise>
				</c:choose>
				<OPTION value="${reason.id}" ${selected}>${reason.id}</OPTION>
			</c:forEach>
		</SELECT>
		<LABEL for="month">Месяц:</LABEL>
		<INPUT type="text" id="month" name="month" value="${month}">
		
		<LABEL for="isProved">Одобренна:</LABEL>
		<INPUT type="text" id="isProved" name="isProved" value="${isProved}">
		
		<LABEL for="dateOfProve">Дата Одобрения:</LABEL>
		<INPUT type="text" id="dateOfProve" name="dateOfProve" value="${dateOfProve}">
	
		
		<BUTTON type="submit">Сохранить</BUTTON>
		<BUTTON type="reset">Сбросить</BUTTON>
	</FORM>
	<c:if test="${not empty studentAdditional}">
		<c:url value="/studentAdditional/delete.html" var="studentAdditionalDeleteUrl"/>
		<FORM action="${studentAdditionalDeleteUrl}" method="post" id="form-delete" onsubmit="return confirmation(this, 'Вы уверены, что хотите удалить StudentAdditional?')">
			<INPUT type="hidden" name="id" value="${studentAdditional.id}">
			<BUTTON type="submit" onclick="submitFormById('form-delete')">Удалить</BUTTON>
		</FORM>
	</c:if>
</u:html>