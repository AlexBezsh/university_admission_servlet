<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="i18n.messages"/>

<html>
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="faculty.finalList"/></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>

<jsp:include page="../fragments/bodyHeader.jsp"/>

<h3 class="page-header" style="text-align: center; margin-top: 30px; margin-bottom: 30px">
    <fmt:message key="faculty.finalList"/>
</h3>

<div style="margin-left: 30px; margin-right: 30px;">

    <div th:if="${enrollments.empty}">
        <p style="text-align: center">
            <fmt:message key="faculties.empty.message"/>
        </p>
    </div>

    <table class="table" th:if="${!enrollments.empty}">
        <thead>
        <tr>
            <th scope="col"><fmt:message key="enrollment.id"/></th>
            <th scope="col"><fmt:message key="enrollment.user"/></th>
            <th scope="col"><fmt:message key="enrollment.marksSum"/></th>
        </tr>
        </thead>
        <tbody>
        <tr th:each="enrollment : ${enrollments}">
            <th scope="row" th:text="${enrollment.id}"></th>
            <td>
                <a th:href="@{'/user/' + ${enrollment.user.id}}" th:text="${enrollment.user.fullName}">
                </a>
            </td>
            <td th:text="${enrollment.marksSum}"></td>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>