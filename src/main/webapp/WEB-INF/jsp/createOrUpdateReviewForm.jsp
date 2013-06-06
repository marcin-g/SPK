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

		<form:form modelAttribute="book" method="post" class="form-horizontal">
			<div class="${cssGroup}">
				<label class="control-label">Recenzja</label>
				<div class="controls">
					<form:hidden path="id" />
					<form:input path="reviewURL" />
					<span class="help-inline"><form:errors path="reviewURL" cssClass="control-group error" /></span>
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
