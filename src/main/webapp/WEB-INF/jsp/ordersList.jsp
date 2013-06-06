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

		<h2>Zamówienia</h2>

		<c:forEach var="order" items="${orders}">
			<spring:url value="/order/confirm/{orderId}" var="orderURL">
				<spring:param name="orderId" value="${order.key.id}" />
			</spring:url>
			
			
			<c:out value="${order.key.id}" />. <b><c:out value="${order.value.title}" /> - <c:out
				value="${order.value.author}" /></b>

			<security:authorize access="hasRole('ROLE_ADMIN')">
				<form:form style="display:inline;" method="post" action="${orderURL}">
				<button type="submit">Zatwierdź</button>
				</form:form>
			</security:authorize>
			
			<br/>

		</c:forEach>
		<jsp:include page="fragments/footer.jsp" />
	</div>
</body>

</html>
