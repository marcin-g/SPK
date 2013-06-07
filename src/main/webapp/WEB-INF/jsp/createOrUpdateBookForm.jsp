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
				<c:set var="method" value="post" />
			</c:otherwise>
		</c:choose>

		<form:form modelAttribute="book" method="${method}" class="form-horizontal">
			<div class="${cssGroup}">
				<label class="control-label">Tytuł</label>
				<div class="controls">
					<form:hidden path="id" />
					<form:hidden path="state" />
					<form:input path="title" />
					<span class="help-inline"><form:errors path="title" cssClass="control-group error" /></span>
				</div>
				<label class="control-label">Autor</label>
				<div class="controls">
					<form:input path="author" />
					<span class="help-inline"><form:errors path="author" cssClass="control-group error" /></span>
				</div>
				<label class="control-label">ISBN</label>
				<div class="controls">
					<form:input path="ISBN" />
					<span class="help-inline"><form:errors path="ISBN" cssClass="control-group error" /></span>
				</div>
				<label class="control-label">Wydawnictwo</label>
				<div class="controls">
					<form:input path="publisher" />
					<span class="help-inline"><form:errors path="ISBN" cssClass="control-group error" /></span>
				</div>
				<label class="control-label">Rok wydania</label>
				<div class="controls">
					<form:input path="year" />
					<span class="help-inline"><form:errors path="year" cssClass="control-group error" /></span>
				</div>

				<label class="control-label">Status</label>
				<div class="controls">
					<form:select path="state">
						<option value="AVAILABLE">dostępna</option>
						<option value="REPORTED">realizacja zamówienia</option>
						<option value="REVIEWED">u recenzenta</option>
					</form:select>
					<span class="help-inline"><form:errors path="state" cssClass="control-group error" /></span>
				</div>

			</div>
			<div class="form-actions">
				<button type="submit">Edytuj książkę</button>
			</div>
		</form:form>
		<jsp:include page="fragments/footer.jsp" />
	</div>
</body>

</html>
