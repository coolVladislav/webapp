<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<u:html title="Список студентов" message="${message}">
	<H2>Список ReasonDoc</H2>
	<TABLE>
		<TR>
			<TH>Номер</TH>
			<TH>Описание</TH>
		</TR>
		<c:url value="/reasonDoc/edit.html" var="reasonDocEditUrl"/>
		<c:forEach items="${reasonDocs}" var="reasonDoc">
			<TR onclick="submitFormById('form-${reasonDoc.id}')">
				<TD>
					${reasonDoc.number}
					<FORM id="form-${reasonDoc.id}" action="${reasonDocEditUrl}"  method="post">
						<INPUT type="hidden" name="id" value="${reasonDoc.id}">
					</FORM>
				</TD>
				<TD>${reasonDoc.docName}</TD>
			</TR>
		</c:forEach>
	</TABLE>
	<c:url value="/reasonDoc/edit.html" var="reasonDocEditUrl"/>
	<FORM action="${reasonDocEditUrl}" method="post">
		<BUTTON type="submit">Добавить</BUTTON>
	</FORM>
</u:html>