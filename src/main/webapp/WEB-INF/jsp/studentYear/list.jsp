<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<u:html title="Список студентов+год" message="${message}">
	<H2>Список студентов+год</H2>
	<TABLE>
		<TR>
			<TH>Курс</TH>
			<TH>Группа</TH>
			<TH>Студент</TH>
			<TH>Год</TH>
		</TR>
		<c:url value="/studentYear/edit.html" var="studentYearEditUrl"/>
		<c:forEach items="${studentYears}" var="studentYear">
		
			<TR onclick="submitFormById('form-${studentYear.id}')" >
				<TD>
					${studentYear.course}
					<FORM id="form-${studentYear.id}" action="${studentYearEditUrl}"  method="post">
						<INPUT type="hidden" name="id" value="${studentYear.id}">
					</FORM>
				</TD>
				<TD>${studentYear.group}</TD>
				<TD>${studentYear.student.lastName}</TD>
				<TD>${studentYear.year}</TD>
			</TR>
		</c:forEach>
	</TABLE>
	<c:url value="/studentYear/edit.html" var="studentYearEditUrl"/>
	<FORM action="${studentYearEditUrl}" method="post">
		<BUTTON type="submit">Добавить</BUTTON>
	</FORM>
</u:html>