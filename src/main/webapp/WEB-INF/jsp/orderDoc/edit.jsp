<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<c:choose>
	<c:when test="${not empty orderDoc}">
		<c:set var="order" value="${orderDoc.order}"/>
		<c:if test="${not empty orderDoc.order}">
			<c:set var="selectedOrderId" value="${orderDoc.order.id}"/>
		</c:if>
		<c:set var="reasonDoc" value="${orderDoc.reasonDoc}"/>
		<c:if test="${not empty orderDoc.reasonDoc}">
			<c:set var="selectedReasonDocId" value="${orderDoc.reasonDoc.id}"/>
		</c:if>
		<c:set var="isAccepted" value="${orderDoc.isAccepted}"/>
		<c:set var="title" value="OrderDoc ${orderDoc.order.id} ${orderDoc.reasonDoc.number} ${orderDoc.isAccepted}"/>
	</c:when>
	<c:otherwise>
		<c:set var="title" value="New OrderDoc"/>
	</c:otherwise>
</c:choose>
<u:html title="${title}" validator="validator-of-edit-orderDoc-form.js">
	<H2>${title}</H2>
	<c:url value="/orderDoc/save.html" var="orderDocSaveUrl"/>
	<FORM action="${orderDocSaveUrl}" method="post" onsubmit="return validateEditOrderDoc(this)">
		<c:if test="${not empty orderDoc}">
			<INPUT type="text" name="id" value="${orderDoc.id}">
		</c:if>
		
		<LABEL for="order">Заявка:</LABEL>
		<SELECT id="order" name="order">
			<c:forEach items="${orders}" var="order">
				<c:choose>
					<c:when test="${order.id == selectedOrderId}">
						<c:set var="selected" value="selected"/>
					</c:when>
					<c:otherwise>
						<c:remove var="selected"/>
					</c:otherwise>
				</c:choose>
				<OPTION value="${order.id}" ${selected}>${order.id}</OPTION>
			</c:forEach>
		</SELECT>
		
		<LABEL for="reasonDoc">Документы Причины:</LABEL>
		<SELECT id="reasonDoc" name="reasonDoc">
			<c:forEach items="${reasonDocs}" var="reasonDoc">
				<c:choose>
					<c:when test="${reasonDoc.id == selectedReasonDocId}">
						<c:set var="selected" value="selected"/>
					</c:when>
					<c:otherwise>
						<c:remove var="selected"/>
					</c:otherwise>
				</c:choose>
				<OPTION value="${reasonDoc.id}" ${selected}>${reasonDoc.number}</OPTION>
			</c:forEach>
		</SELECT>
		
		<LABEL for="isAccepted">Принято:</LABEL>
		<INPUT type="text" id="isAccepted" name="isAccepted" value="${isAccepted}">
		
		<BUTTON type="submit">Сохранить</BUTTON>
		<BUTTON type="reset">Сбросить</BUTTON>
	</FORM>
	<c:if test="${not empty orderDoc}">
		<c:url value="/orderDoc/delete.html" var="orderDocDeleteUrl"/>
		<FORM action="${orderDocDeleteUrl}" method="post" id="form-delete" onsubmit="return confirmation(this, 'Вы уверены, что хотите удалить OrderDoc?')">
			<INPUT type="hidden" name="id" value="${orderDoc.id}">
			<BUTTON type="submit" onclick="submitFormById('form-delete')">Удалить</BUTTON>
		</FORM>
	</c:if>
</u:html>