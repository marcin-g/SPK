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
		<c:choose>
			<c:when test="${empty book.id}">
				<c:set var="method" value="post" />
			</c:when>
			<c:otherwise>
				<c:set var="method" value="put" />
			</c:otherwise>
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
		</div>
		<div class="form-actions">
			<security:authorize access="hasRole('ROLE-SUPERUSER')">
				<button type="submit">Edytuj książkę</button>
			</security:authorize>
		</div>

		<jsp:include page="fragments/footer.jsp" />
	</div>
</body>

</html>
