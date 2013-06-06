<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="java.io.*,java.util.*"%>
<%@ page import="javax.servlet.*,java.text.*"%>


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

		<%
			//long myLong = (long)borrow.key.begin;
			//Date date = new Date(borrow.key.begin);
			//SimpleDateFormat ft = new SimpleDateFormat("yyyy.MM.dd");
			//out.print("<h2 align=\"center\">" + ft.format(date) + "</h2>");
		%>

		<c:forEach var="borrow" items="${borrows}">
			<c:out value="${borrow.key.begin}" />

			<a href="${bookURL}"> <c:out value="${date}" /> (<c:out value="${borrow.value.firstname}" />
				<c:out value="${borrow.value.lastname}" />)
			</a>
			<br />
		</c:forEach>

		<jsp:include page="fragments/footer.jsp" />
	</div>
</body>

</html>
