<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="en">


<jsp:include page="fragments/headTag.jsp" />

<body>
	<div class="container">
		<jsp:include page="fragments/bodyHeader.jsp" />

		<h2>Movies</h2>

		<c:forEach var="movie" items="${movies}">
			<c:out value="${movie.id}" />
			<c:out value="${movie.title}" />
			<spring:url value="/movies/{movieId}" var="movieURL">
				<spring:param name="movieId" value="${movie.id}" />
			</spring:url>
			<a href="${movieURL}">edytuj</a>
			<form:form method="delete" action="${movieURL}">
				<button type="submit">Delete</button>
			</form:form>
			<br />

		</c:forEach>
		<jsp:include page="fragments/footer.jsp" />
	</div>
</body>

</html>
