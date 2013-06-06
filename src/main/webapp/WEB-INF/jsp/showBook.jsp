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
		<spring:url value="/books/review/{bookId}" var="bookReviewURL">
			<spring:param name="bookId" value="${book.id}" />
		</spring:url>
		<spring:url value="/books/reviewing/{bookId}" var="bookReviewingURL">
			<spring:param name="bookId" value="${book.id}" />
		</spring:url>
		<spring:url value="/books/borrow/{bookId}" var="bookBorrowURL">
			<spring:param name="bookId" value="${book.id}" />
		</spring:url>
		<spring:url value="/books/queue/{bookId}" var="bookQueueURL">
			<spring:param name="bookId" value="${book.id}" />
		</spring:url>

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

		<c:choose>
			<c:when test="${status == '0'}">
				<c:set var="message" value="Wypożycz" />
			</c:when>
			<c:when test="${status == '1'}">
				<c:set var="message" value="Oddaj książkę" />
			</c:when>
			<c:when test="${status == '2'}">
				<c:set var="message" value="Wypisz się z kolejki" />
			</c:when>
			<c:when test="${status == '3'}">
				<c:set var="message" value="Dopisz się do kolejki" />
			</c:when>
		</c:choose>



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
			<br /> Recenzja:
			<c:out value="${book.reviewURL}" />

			<security:authorize access="hasRole('ROLE_SUPERUSER')">
				<c:if test="${empty book.reviewURL}">
					<form:form style="display:inline;" method="get" action="${bookReviewURL}">
						<button type="submit">Wyślij recenzję</button>
					</form:form>
				</c:if>
			</security:authorize>

			<br /> Status:
			<c:out value="${stat}" />

			<c:choose>
				<c:when test="${status == '0' || status == '1'}">
					<form:form style="display:inline;" method="post" action="${bookBorrowURL}">
						<button type="submit">
							<c:out value="${message }" />
						</button>
					</form:form>
				</c:when>
				<c:when test="${status == '2' || status == '3'}">
					<form:form style="display:inline;" method="post" action="${bookQueueURL}">
						<button type="submit">
							<c:out value="${message }" />
						</button>
					</form:form>
				</c:when>
			</c:choose>



		</div>


		<div class="form-actions">
			<security:authorize access="hasRole('ROLE_ADMIN')">
				<c:if test="${book.state == 'AWAITING_RECEPTION' && empty book.reviewURL}">
					<form:form style="display:inline;" method="post" action="${bookReviewingURL}">
						<button type="submit">Odebrana przez Recenzenta</button>
					</form:form>
				</c:if>
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
