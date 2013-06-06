<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=utf-8"%>


<html lang="pl">


<jsp:include page="fragments/headTag.jsp" />

<body>
	<div class="container">
		<jsp:include page="fragments/bodyHeader.jsp" />

		<h2>Historia wypożyczeń</h2>

		<c:forEach var="borrow" items="${borrows}">
			<c:out value="${borrow.key.id}" />. <b><c:out value="${borrow.key.bookId}" /></b> - <c:out
				value="${borrow.value.title}" />
			<spring:url value="/books/{bookId}" var="bookURL">
				<spring:param name="bookId" value="${borrow.key.bookId}" />
			</spring:url>
			<br />
		</c:forEach>
		<jsp:include page="fragments/footer.jsp" />
	</div>
</body>

</html>
