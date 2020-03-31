<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<c:choose>
	<c:when test="${not empty order}">
		<c:set var="studentYear" value="${order.studentYear.id}"/>
		<c:if test="${not empty order.studentYear}">
			<c:set var="selectedStudentYearId" value="${order.studentYear.id}"/>
		</c:if>
		<c:set var="reason" value="${order.reason.id}"/>
		<c:if test="${not empty order.reason}">
			<c:set var="selectedReasonId" value="${order.reason.id}"/>
		</c:if>
		<c:set var="dateOfOrder" value="${order.dateOfOrder}"/>
		<c:set var="title" value="Order ${order.studentYear.id} ${order.reason.id} ${order.dateOfOrder}"/>
	</c:when>
	<c:otherwise>
		<c:set var="title" value="New Order"/>
	</c:otherwise>
</c:choose>
<u:html title="${title}" validator="validator-of-edit-order-form.js">
	<H2>${title}</H2>
	<c:url value="/order/save.html" var="orderSaveUrl"/>
	<FORM action="${orderSaveUrl}" method="post" onsubmit="return validateEditOrder(this)">
		<c:if test="${not empty order}">
			<INPUT type="text" name="id" value="${order.id}">
		</c:if>
		
		<LABEL for="studentYear">Год:</LABEL>
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
		
		<LABEL for="dateOfOrder">Дата:</LABEL>
		<INPUT type="text" id="dateOfOrder" name="dateOfOrder" value="${dateOfOrder}">
		<BUTTON type="submit">Сохранить</BUTTON>
		<BUTTON type="reset">Сбросить</BUTTON>
	</FORM>
	<c:if test="${not empty order}">
		<c:url value="/order/delete.html" var="orderDeleteUrl"/>
		<FORM action="${orderDeleteUrl}" method="post" id="form-delete" onsubmit="return confirmation(this, 'Вы уверены, что хотите удалить заявку?')">
			<INPUT type="hidden" name="id" value="${order.id}">
			<BUTTON type="submit">Удалить</BUTTON>
		</FORM>
	</c:if>
</u:html>