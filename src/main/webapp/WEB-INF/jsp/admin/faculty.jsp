<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="i18n.messages"/>

<html>
<head>
    <meta charset="UTF-8">
    <title th:text="${faculty.name}"></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>

<jsp:include page="../fragments/bodyHeader.jsp"/>

<h3 class="page-header" style="text-align: center; margin-top: 30px; margin-bottom: 30px"
    th:text="${faculty.name}"></h3>

<div class="row" style="justify-content: space-between">
    <div>
    </div>
    <div sec:authorize="hasAuthority('ADMIN')" th:if="${faculty.status == faculty.status.ACTIVE}">
        <a class="btn btn-secondary" style="margin-right: 45px; margin-left: 5px; margin-top: 5px; margin-bottom: 5px;"
           th:href="@{'/faculty/' + ${faculty.id} + '/finalize'}">
            <fmt:message key="faculty.finalize"/>
        </a>
    </div>
</div>

<div style="margin-left: 30px; margin-right: 30px;">

    <div th:if="${faculty.enrollments.empty}">
        <p style="text-align: center">
            <fmt:message key="faculty.empty.message"/>
        </p>
    </div>

    <table class="table" th:if="${!faculty.enrollments.empty}">
        <thead>
        <tr>
            <th scope="col"><fmt:message key="enrollment.id"/></th>
            <th scope="col"><fmt:message key="enrollment.id"/></th>
            <th scope="col"><fmt:message key="enrollment.user.status"/></th>
            <th scope="col"><fmt:message key="enrollment.marksSum"/></th>
            <th scope="col"><fmt:message key="enrollment.status"/></th>
            <th sec:authorize="hasAuthority('ADMIN')" scope="col"><fmt:message key="enrollment.approve"/></th>
        </tr>
        </thead>
        <tbody>
        <tr th:each="enrollment : ${faculty.enrollments}">
            <th scope="row" th:text="${enrollment.id}"></th>
            <td>
                <p sec:authorize="!hasAuthority('ADMIN')" th:text="${enrollment.user.fullName}"></p>
                <a sec:authorize="hasAuthority('ADMIN')" th:href="@{'/user/' + ${enrollment.user.id}}"
                   th:text="${enrollment.user.fullName}">
                </a>
            </td>
            <td th:text="${enrollment.user.status}"></td>
            <td th:text="${enrollment.marksSum}"></td>
            <td th:text="${enrollment.status}"></td>
            <td sec:authorize="hasAuthority('ADMIN')">
                <a class="btn btn-success" style="margin-right: 5px; margin-left: 15px;"
                   th:if="${(enrollment.status == enrollment.status.NEW) && (enrollment.user.status == enrollment.user.status.ACTIVE)}"
                   th:href="@{'/faculty/' + ${enrollment.faculty.id} + '/enrollment/' + ${enrollment.id} + '/approve'}">
                    <fmt:message key="enrollment.approve"/>
                </a>
                <b th:if="${(enrollment.status != enrollment.status.NEW) || (enrollment.user.status != enrollment.user.status.ACTIVE)}">-</b>
            </td>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>