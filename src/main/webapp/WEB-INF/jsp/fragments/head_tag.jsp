<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}" scope="request"/>
<fmt:setBundle basename="i18n.messages" scope="request"/>

<c:set var="auth" value="${sessionScope.user}" scope="request"/>
<c:set var="lang" value="${sessionScope.lang}" scope="request"/>
<c:set var="contextPath" value="${pageContext.request.contextPath}/app" scope="request"/>

<head>
    <meta charset="UTF-8">
    <title><fmt:message key="app.name"/></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>