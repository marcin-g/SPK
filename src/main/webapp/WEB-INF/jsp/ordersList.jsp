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

		<h2>Zamówienia</h2>

		<c:forEach var="order" items="${orders}">
			<spring:url value="/order/confirm/{orderId}" var="orderURL">
				<spring:param name="orderId" value="${order.key.id}" />
			</spring:url>
			<form:form method="post" action="${orderURL}">
			Zamówienie nr <c:out value="${order.key.id}" />.<br />
			<b><c:out value="${order.value.title}" /></b> - <c:out value="${order.value.author}" /><br />
				<button type="submit">Zatwierdź</button>
			</form:form>

		</c:forEach>
		<jsp:include page="fragments/footer.jsp" />
	</div>
</body>

</html>
