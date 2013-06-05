<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page
	import="org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter"%>
<%@ page
	import="org.springframework.security.core.AuthenticationException"%>

<html lang="en">

<jsp:include page="fragments/headTag.jsp" />

<body>
	<div class="container">
		<jsp:include page="fragments/bodyHeader.jsp" />


	<form name="f" method="post" action="<c:url value="/loginProcess" />" id="login-form"
		class="login medium">
		<div class="${cssGroup}">
		<c:if test="${not empty param.login_error}">
			<div id="log-in-error" class="message error">
				Your login attempt was not successful, try again.<br />
				<br /> Reason:
				<c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
			</div>
		</c:if>
		
				<label for="j_username">Login</label>
				<input type="text"name="j_username" id="j_username"
					value='<c:if test="${not empty param.login_error}">
					<c:out value="${SPRING_SECURITY_LAST_EXCEPTION.authentication.principal}"/></c:if>' />
					
					
					<label for="j_password">Hasło</label>
					<input type="password" name="j_password" id="j_password" /><br />
					<button id="login" type="submit">Zaloguj się</button>
	</div>
	</form>
	<jsp:include page="fragments/footer.jsp" />
	</div>
</body>

</html>

