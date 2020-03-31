<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<u:html title="Список заявок" message="${message}">
	<H2>Список OrderDoc</H2>
	<TABLE>
		<TR>
			<TH>Order</TH>
			<TH>ReasonDoc</TH>
			<TH>Принято</TH>
		</TR>
		<c:url value="/orderDoc/edit.html" var="orderDocEditUrl"/>
		<c:forEach items="${orderDocs}" var="orderDoc">
			<TR onclick="submitFormById('form-${orderDoc.id}')">
				<TD>
					${orderDoc.order.id}
					<FORM id="form-${orderDoc.id}" action="${orderDocEditUrl}"  method="post">
						<INPUT type="hidden" name="id" value="${orderDoc.id}">
					</FORM>
				</TD>
				<TD>${orderDoc.reasonDoc.id}</TD>
				<TD>${orderDoc.isAccepted}</TD>
			</TR>
		</c:forEach>
	</TABLE>
	<c:url value="/orderDoc/edit.html" var="orderDocEditUrl"/>
	<FORM action="${orderDocEditUrl}" method="post">
		<BUTTON type="submit">Добавить</BUTTON>
	</FORM>
</u:html>