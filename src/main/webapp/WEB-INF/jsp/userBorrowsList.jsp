<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=utf-8"%>


<html lang="pl">

<jsp:useBean id="dateValue" class="java.util.Date" />

<jsp:include page="fragments/headTag.jsp" />

<body>
	<div class="container">
		<jsp:include page="fragments/bodyHeader.jsp" />

		<h2>
			Historia wypożyczeń -
			<c:out value="${user.username }" />
			(
			<c:out value="${user.firstname }" />
			<c:out value="${user.lastname }" />
			)
		</h2>

		<c:forEach var="borrow" items="${borrows}">
			<spring:url value="/books/{bookId}" var="bookURL">
				<spring:param name="bookId" value="${borrow.key.bookId}" />
			</spring:url>	
					[<jsp:setProperty name="dateValue" property="time" value="${borrow.key.begin*1000}" />
			<fmt:formatDate value="${dateValue}" pattern="MM/dd/yyyy" /> -
			
			<c:choose>
				<c:when test="${borrow.key.end==0}">
					<c:out value="--/--/----" />
				</c:when>
				<c:otherwise>
					<jsp:setProperty name="dateValue" property="time" value="${borrow.key.end*1000}" />
					<fmt:formatDate value="${dateValue}" pattern="MM/dd/yyyy" />
				</c:otherwise>
			</c:choose>
			]
			
			<a href="${bookURL}"> <c:out value="${borrow.value.title}" /> - <c:out
					value="${borrow.value.author}" />
			</a>
			<br />
		</c:forEach>
		<jsp:include page="fragments/footer.jsp" />
	</div>
</body>

</html>
