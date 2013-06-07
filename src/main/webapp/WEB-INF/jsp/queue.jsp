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

		<h2>
			Kolejka:
			<c:out value="${book.title } - ${book.author }" />
		</h2>

		<c:forEach var="user" items="${users}">



			<spring:url value="/queue/rm/{bookId}/{userId}" var="queueEditURL">
				<spring:param name="userId" value="${user.id}" />
				<spring:param name="bookId" value="${book.id}" />
			</spring:url>


			<c:out value="${user.id}" />. <b><c:out value="${user.username}" /> (<c:out
					value="${user.firstname}" /> <c:out value="${user.lastname}" />)</b>
			<form:form style="display:inline;" method="delete" action="${queueEditURL}">
				<button type="submit">Usuń</button>
			</form:form>
			<br />
		</c:forEach>
		<jsp:include page="fragments/footer.jsp" />
	</div>
</body>

</html>
