<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<u:html title="Список студентов" message="${message}">
	<H2>Список Надбавок</H2>
	<TABLE>
		<TR>
			<TH>StudentYear</TH>
			<TH>Сумма</TH>
			<TH>Причина</TH>
			<TH>Месяц</TH>
			<TH>Одобренна</TH>
			<TH>Дата Одобрения</TH>
		</TR>
		<c:url value="/studentAdditional/edit.html" var="studentAdditionalEditUrl"/>
		<c:forEach items="${studentAdditionals}" var="studentAdditional">
			<TR onclick="submitFormById('form-${studentAdditional.id}')">
				<TD>
					${studentAdditional.studentYear}
					<FORM id="form-${studentAdditional.id}" action="${studentAdditionalEditUrl}"  method="post">
						<INPUT type="hidden" name="id" value="${studentAdditional.id}">
					</FORM>
				</TD>
				<TD>${studentAdditional.sum}</TD>
				<TD>${studentAdditional.reason}</TD>
				<TD>${studentAdditional.month}</TD>
				<TD>${studentAdditional.isProved}</TD>
				<TD>${studentAdditional.dateOfProve}</TD>
			</TR>
		</c:forEach>
	</TABLE>
	<c:url value="/studentAdditional/edit.html" var="studentAdditionalEditUrl"/>
	<FORM action="${studentAdditionalEditUrl}" method="post">
		<BUTTON type="submit">Добавить</BUTTON>
	</FORM>
</u:html>