<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ page contentType="text/html;charset=utf-8"%>


<html lang="pl">

<jsp:include page="fragments/headTag.jsp" />
<body>
	<div class="container">
		<jsp:include page="fragments/bodyHeader.jsp" />


		<spring:url value="/books/edit/{bookId}" var="bookEditURL">
			<spring:param name="bookId" value="${book.id}" />
		</spring:url>


		<div class="${cssGroup}">
			Tytuł:
			<c:out value="${book.title}" />
			<br /> Autor:
			<c:out value="${book.author}" />
			<br /> ISBN:
			<c:out value="${book.ISBN}" />
			<br /> Wydawnictwo:
			<c:out value="${book.publisher}" />
			<br /> Rok wydania:
			<c:out value="${book.year}" />
		</div>
		<div class="form-actions">
			<security:authorize access="hasRole('ROLE_ADMIN')">
				<form:form style="display:inline;" method="get" action="${bookEditURL}">
					<button type="submit">Edytuj</button>
				</form:form>
				<form:form style="display:inline;" method="delete" action="${bookEditURL}">
					<button type="submit">Usuń</button>
				</form:form>
			</security:authorize>
		</div>

		<jsp:include page="fragments/footer.jsp" />
	</div>
</body>

</html>
