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

		<form:form modelAttribute="user" method="post" class="form-horizontal">
			<div class="${cssGroup}">
				<label class="control-label">Imię</label>
				<div class="controls">
					<form:hidden path="id" />
					<form:input path="firstname" />
					<span class="help-inline"><form:errors path="firstname" cssClass="control-group error" /></span>
				</div>
				<label class="control-label">Nazwisko</label>
				<div class="controls">
					<form:input path="lastname" />
					<span class="help-inline"><form:errors path="lastname" cssClass="control-group error" /></span>
				</div>
				<label class="control-label">E-mail</label>
				<div class="controls">
					<form:input path="email" />
					<span class="help-inline"><form:errors path="email" cssClass="control-group error" /></span>
				</div>
			</div>
			<div class="form-actions">
				<button type="submit">Zatwierdź</button>
			</div>
		</form:form>
		<jsp:include page="fragments/footer.jsp" />
	</div>
</body>

</html>
