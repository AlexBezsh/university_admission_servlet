<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="i18n.messages"/>

<html>
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="faculties.title"/></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>

<jsp:include page="../fragments/bodyHeader.jsp"/>

<h3 class="page-header" style="text-align: center; margin-top: 30px; margin-bottom: 30px">
    <fmt:message key="faculties.header"/>
</h3>

<div class="row" style="justify-content: space-between">
    <jsp:include page="../fragments/faculties_sorting_options.jsp"/>
        <a class="btn btn-secondary" style="margin-right: 45px; margin-left: 45px; margin-top: 5px; margin-bottom: 5px;" href="/admin/faculty/new">
            <fmt:message key="faculty.new"/>
        </a>
</div>

<div style="margin-left: 30px; margin-right: 30px;">

    <c:if test="${empty faculties}">
        <p style="text-align: center">
            <fmt:message key="faculties.empty.message"/>
        </p>
    </c:if>

    <c:forEach items="${faculties}" var="faculty">
        <c:set var="faculty" value="${faculty}" scope="request"/>
        <jsp:include page="../admin/fragments/faculty_card.jsp"/>
    </c:forEach>

</div>

<jsp:include page="../fragments/pagination.jsp"/>

</body>
</html>