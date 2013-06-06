<!DOCTYPE html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags"%>
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

		<form:form modelAttribute="book" method="${method}" class="form-horizontal">
			<div class="${cssGroup}">
				<label class="control-label">Recenzja</label>
				<div class="controls">
					<form:input path="year" />
					<span class="help-inline"><form:errors path="year" cssClass="control-group error" /></span>
				</div>

			</div>
			<div class="form-actions">
				<button type="submit">Edytuj recenzję</button>
			</div>
		</form:form>
		<jsp:include page="fragments/footer.jsp" />
	</div>
</body>

</html>
