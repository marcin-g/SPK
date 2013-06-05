<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page
	import="org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter"%>
<%@ page
	import="org.springframework.security.core.AuthenticationException"%>

	<form name="f" method="post" action="<c:url value="/loginProcess" />" id="login-form"
		class="login medium">
		<c:if test="${not empty param.login_error}">
			<div id="log-in-error" class="message error">
				Your login attempt was not successful, try again.<br />
				<br /> Reason:
				<c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
			</div>
		</c:if>
		<fieldset>
			<legend>Mój formularz logowania</legend>
			<ul>
				<li><label for="j_usernam">login</label> <input type="text"
					name="j_username" id="j_username"
					value='<c:if test="${not empty param.login_error}"><c:out value="${SPRING_SECURITY_LAST_EXCEPTION.authentication.principal}"/></c:if>' />
					</li>
				<li><label for="login-password">hasło</label> <input
					type="password" name="j_password" id="j_password" /> </li>
				<li class="submit">
					<button id="login" type="submit">Zaloguj się</button>
				</li>
			</ul>
		</fieldset>
	</form>


