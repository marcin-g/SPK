<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=utf-8" %>


<html lang="pl">


<jsp:include page="fragments/headTag.jsp" />

<body>
	<div class="container">
		<jsp:include page="fragments/bodyHeader.jsp" />

		<h2>Książki</h2>

		<c:forEach var="book" items="${books}">
			<c:out value="${book.id}" />
			<c:out value="${book.title}" />
			<spring:url value="/books/{bookId}" var="bookURL">
				<spring:param name="bookId" value="${book.id}" />
			</spring:url>
			<a href="${bookURL}">edytuj</a>
			<form:form method="delete" action="${bookURL}">
				<button type="submit">Usuń</button>
			</form:form>
			<br />

		</c:forEach>
		<jsp:include page="fragments/footer.jsp" />
	</div>
</body>

</html>
