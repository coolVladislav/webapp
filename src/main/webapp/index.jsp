<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<u:html title="Список студентов" message="${message}">
	<H2>Список</H2>
	<TABLE>
		<TR>
			<TH>Переход</TH>
		</TR>
		
		<c:url value="/student/list.html" var="studentListUrl"/>
		<TR>
			<TD><a href="${studentListUrl}">Student</a></TD>
		</TR>
		
		<c:url value="/studentYear/list.html" var="studentYearListUrl"/>
		<TR>
			<TD><a href="${studentYearListUrl}">StudentYear</a></TD>
		</TR>
		
		<c:url value="/studentAdditional/list.html" var="studentAdditionalListUrl"/>
		<TR>
			<TD><a href="${studentAdditionalListUrl}">StudentAdditional</a></TD>
		</TR>
		
		<c:url value="/reason/list.html" var="reasonListUrl"/>
		<TR>
			<TD><a href="${reasonListUrl}">Reason</a></TD>
		</TR>
		
		<c:url value="/reasonDoc/list.html" var="reasonDocListUrl"/>
		<TR>
			<TD><a href="${reasonDocListUrl}">ReasonDoc</a></TD>
		</TR>
		
		<c:url value="/order/list.html" var="orderListUrl"/>
		<TR>
			<TD><a href="${orderListUrl}">Order</a></TD>
		</TR>
		
		<c:url value="/orderDoc/list.html" var="orderDocListUrl"/>
		<TR>
			<TD><a href="${orderDocListUrl}">OrderDoc</a></TD>
		</TR>
	</TABLE>
	
</u:html>