<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html lang="pl">


<jsp:include page="fragments/headTag.jsp" />

<body>
	<div class="container">
		<jsp:include page="fragments/bodyHeader.jsp" />

		<h2>Zamówienia</h2>

		<c:forEach var="order" items="${orders}">
			<c:out value="${order.id}" />
			<c:out value="${order.title}" />
			<spring:url value="/orders/{orderId}" var="orderURL">
				<spring:param name="orderId" value="${order.id}" />
			</spring:url>
			<a href="${orderURL}">edytuj</a>
			<form:form method="delete" action="${orderURL}">
				<button type="submit">Delete</button>
			</form:form>
			<br />

		</c:forEach>
		<jsp:include page="fragments/footer.jsp" />
	</div>
</body>

</html>
