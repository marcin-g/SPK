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
			<c:when test="${empty user.id}">
				<c:set var="method" value="post" />
			</c:when>
			<c:otherwise>
				<c:set var="method" value="post" />
			</c:otherwise>
		</c:choose>

		<form:form modelAttribute="user" method="${method}" class="form-horizontal">
			<div class="${cssGroup}">
				<label class="control-label">Login</label>
				<div class="controls">
					<form:hidden path="id" />
					<form:input path="username" />
					<span class="help-inline"><form:errors path="username" cssClass="control-group error" /></span>
				</div>
				<label class="control-label">Imię</label>
				<div class="controls">
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

				<label class="control-label">Typ użytkownika</label>
				<div class="controls">
					<form:select path="roles">
						<option value="ROLE_USER">Zwykły użytkownik</option>
						<option value="ROLE_SUPERUSER">Superuser</option>
					</form:select>
					<span class="help-inline"><form:errors path="roles" cssClass="control-group error" /></span>
				</div>

				<c:if test="${empty user.username}">
					<label class="control-label">Hasło</label>
					<div class="controls">
						<form:input type="password" path="password" />
						<span class="help-inline"><form:errors path="password" cssClass="control-group error" /></span>
					</div>
				</c:if>

			</div>
			<div class="form-actions">
				<button type="submit">Edytuj użytkownika</button>
			</div>
		</form:form>
		<jsp:include page="fragments/footer.jsp" />
	</div>
</body>

</html>
