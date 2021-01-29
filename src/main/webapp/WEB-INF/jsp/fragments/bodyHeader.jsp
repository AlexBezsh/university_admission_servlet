<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${param.lang}"/>
<fmt:setBundle basename="i18n.messages"/>

<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<nav class="navbar navbar-expand-lg navbar-light bg-light justify-content-between">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/faculties"><fmt:message key="navbar.title"/> </a>
    <div class="row" style="margin-right: 10px">
        <div style="margin-right: 30px">
            <span th:with="urlBuilder=${T(org.springframework.web.servlet.support.ServletUriComponentsBuilder).fromCurrentRequest()}">
            <a class="btn btn-default" th:href="${urlBuilder.replaceQueryParam('lang', 'ua').toUriString()}">
                <fmt:message key="lang.ua.label"/> </a>
            <a class="btn btn-default" th:href="${urlBuilder.replaceQueryParam('lang', 'en').toUriString()}">
                <fmt:message key="lang.en.label"/></a>
            </span>
        </div>
        <div sec:authorize="hasAuthority('ENTRANT')">
            <a class="btn btn-success" style="margin-right: 5px; margin-left: 20px;"
               href="${pageContext.request.contextPath}/profile">
                <fmt:message key="user.profile"/></a>
        </div>
        <div sec:authorize="isAuthenticated()">
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/logout"><fmt:message key="navbar.logout"/></a>
        </div>
        <div sec:authorize="isAnonymous()">
            <a class="btn btn-success" href="${pageContext.request.contextPath}/login"><fmt:message key="login.button"/></a>
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/register"><fmt:message key="registration.button"/></a>
        </div>
    </div>
</nav>
</html>