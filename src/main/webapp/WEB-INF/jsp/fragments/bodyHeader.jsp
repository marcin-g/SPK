<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=utf-8" %>

<div class="navbar" style="width: 601px;">
    <div class="navbar-inner">
        <ul class="nav">
            <li style="width: 100px;"><a href="<spring:url value="/" htmlEscape="true" />"><i class="icon-home"></i>
                Home</a></li>
            <li style="width: 130px;"><a href="<spring:url value="/movies.html" htmlEscape="true" />"><i
                    class="icon-th-list"></i> Zamówienia</a></li>
            <li style="width: 130px;"><a href="<spring:url value="/movies/new.html" htmlEscape="true" />"><i
                    class="icon-plus-sign"></i> Add movie</a></li>                        
        </ul>
    </div>
</div>
	
