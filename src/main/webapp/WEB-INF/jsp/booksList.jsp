<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ page contentType="text/html;charset=utf-8"%>


<html lang="pl">


<jsp:include page="fragments/headTag.jsp" />

<body>
	<div class="container">
		<jsp:include page="fragments/bodyHeader.jsp" />

		<h2>Książki</h2>

		<c:forEach var="book" items="${books}">

			<c:choose>
				<c:when test="${book.state == 'AVAILABLE'}">
					<c:set var="stat" value="na półce" />
				</c:when>
				<c:when test="${book.state == 'REPORTED'}">
					<c:set var="stat" value="realizacja zamówienia" />
				</c:when>
				<c:when test="${book.state == 'BORROWED'}">
					<c:set var="stat" value="wypożyczona" />
				</c:when>
				<c:when test="${book.state == 'UNAVAILABLE'}">
					<c:set var="stat" value="niedostępna" />
				</c:when>
				<c:when test="${book.state == 'AWAITING_RETURN'}">
					<c:set var="stat" value="oczekiwanie na zwrot" />
				</c:when>
				<c:when test="${book.state == 'AWAITING_RECEPTION'}">
					<c:set var="stat" value="oczekiwanie na odbiór" />
				</c:when>
				<c:when test="${book.state == 'REVIEWED'}">
					<c:set var="stat" value="u recenzenta" />
				</c:when>
				<c:otherwise>
					<c:set var="stat" value="----" />
				</c:otherwise>
			</c:choose>

			<spring:url value="/books/{bookId}" var="bookURL">
				<spring:param name="bookId" value="${book.id}" />
			</spring:url>
			<spring:url value="/books/edit/{bookId}" var="bookEditURL">
				<spring:param name="bookId" value="${book.id}" />
			</spring:url>
			<c:out value="${book.id}" />. <a href="${bookURL}"><b><c:out value="${book.title}" /></b></a> - <c:out
				value="${book.author}" />
			<security:authorize access="hasRole('ROLE_ADMIN')">
				<form:form style="display:inline;" method="get" action="${bookEditURL}">
					<button type="submit">Edytuj</button>
				</form:form>
				<form:form style="display:inline;" method="delete" action="${bookEditURL}">
					<button type="submit">Usuń</button>
				</form:form>
			</security:authorize>
			<br />
		</c:forEach>
		<jsp:include page="fragments/footer.jsp" />
	</div>
</body>

</html>
