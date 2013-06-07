<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="java.io.*,java.util.*"%>
<%@ page import="javax.servlet.*,java.text.*"%>

<jsp:useBean id="dateValue" class="java.util.Date" />


<html lang="pl">


<jsp:include page="fragments/headTag.jsp" />

<body>
	<div class="container">
		<jsp:include page="fragments/bodyHeader.jsp" />

		<h2>
			Historia wypożyczeń (
			<c:out value="${book.title}" />
			-
			<c:out value="${book.author}" />
			)
		</h2>

		<spring:url value="/books/{bookId}" var="bookURL">
			<spring:param name="bookId" value="${borrow.key.bookId}" />
		</spring:url>

		<c:forEach var="borrow" items="${borrows}">

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

			<c:out value="${borrow.value.username}" /> (<c:out value="${borrow.value.firstname}" />
			<c:out value="${borrow.value.lastname}" />)
			<br />
		</c:forEach>

		<jsp:include page="fragments/footer.jsp" />
	</div>
</body>

</html>
