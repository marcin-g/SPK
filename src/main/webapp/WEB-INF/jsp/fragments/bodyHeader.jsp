<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ page contentType="text/html;charset=utf-8"%>

<div class="navbar" style="width: 1001px;">
	<div class="navbar-inner">
		<ul class="nav">
			<li style="width: 100px;"><a href="<spring:url value="/books.html" htmlEscape="true" />"><i
					class="icon-home"></i> Książki</a></li>
			<security:authorize access="!isAuthenticated()">
				<li style="width: 100px;"><a href="<spring:url value="/login" htmlEscape="true" />"><i
						class="icon-home"></i> Zaloguj</a></li>
			</security:authorize>
			<security:authorize access="hasRole('ROLE_SUPERUSER')">
				<li style="width: 170px;"><a
					href="<spring:url value="/order/new.html" htmlEscape="true" />"><i class="icon-plus-sign"></i>
						Zamów książkę</a></li>
			</security:authorize>

			<security:authorize access="hasRole('ROLE_SUPERUSER') || hasRole('ROLE_ADMIN')">
				<li style="width: 130px;"><a href="<spring:url value="/order.html" htmlEscape="true" />"><i
						class="icon-th-list"></i> Zamówienia</a></li>
			</security:authorize>
			
			<security:authorize access="hasRole('ROLE_ADMIN')">
				<li style="width: 150px;"><a href="<spring:url value="/books/new" htmlEscape="true" />"><i
						class="icon-th-list"></i> Dodaj książkę</a></li>
			</security:authorize>

			<security:authorize access="isAuthenticated() && !hasRole('ROLE_ADMIN')">
				<li style="width: 100px;"><a href="<spring:url value="/borrow" htmlEscape="true" />"><i
						class="icon-home"></i> Historia</a></li>
			</security:authorize>

			<security:authorize access="isAuthenticated()">
				<li style="width: 100px;"><a href="<spring:url value="/profile" htmlEscape="true" />"><i
						class="icon-home"></i> Profil</a></li>
				<li style="width: 200px;"><a
					href="<spring:url value="/j_spring_security_logout" htmlEscape="true" />"><i
						class="icon-home"></i> Wyloguj [<security:authentication property="principal.username" />]</a></li>
			</security:authorize>
		</ul>
	</div>
</div>

