<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="i18n.messages"/>

<html>
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="enrollment.congratulation.title"/></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>

<jsp:include page="../fragments/bodyHeader.jsp"/>

<div style="margin-top: 15px; margin-bottom: 15px; margin-left: 40px; margin-right: 40px;" class="alert alert-success">
    <c:if test="${sessionScope.user.enrolledContract}">
        <span><fmt:message key="enrollment.congratulation.contract"/></span>
    </c:if>
    <c:if test="${sessionScope.user.enrolledStateFunded}">
    <span><fmt:message key="enrollment.congratulation.stateFunded"/></span>
    </c:if>

</div>

</body>
</html>