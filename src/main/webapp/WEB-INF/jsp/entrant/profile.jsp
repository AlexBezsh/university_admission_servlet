<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="i18n.messages"/>

<html>
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="user.profile"/></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>

<jsp:include page="../fragments/bodyHeader.jsp"/>

<div style="margin-left: 30px; margin-right: 30px;">
    <div class="card" style="margin-bottom: 10px; margin-top: 10px">
        <a class="card-header">${user.fullName}</a>
        <div class="card-body">
            <div class="list-view" style="width: 30rem">
                <p class="card-text" style="margin-right: 10px"
                   th:text="#{user.status} + ${': ' + user.status}"></p>
                <p class="card-text" style="margin-right: 10px"
                   th:text="#{user.email} + ${': ' + user.email}"></p>
                <p class="card-text" style="margin-right: 10px"
                   th:text="#{user.city} + ${': ' + user.city}"></p>
                <p class="card-text" style="margin-right: 10px"
                   th:text="#{user.region} + ${': ' + user.region}"></p>
                <p class="card-text" style="margin-right: 10px"
                   th:text="#{user.education} + ${': ' + user.education}"></p>
            </div>
        </div>
    </div>

    <h3 th:if="${!user.enrollments.empty}" class="page-header" style="text-align: center; margin-top: 30px; margin-bottom: 30px"><fmt:message key="user.enrollments"/></h3>

    <table class="table" th:if="${!user.enrollments.empty}">
        <thead>
        <tr>
            <th scope="col"><fmt:message key="enrollment.id"/></th>
            <th scope="col"><fmt:message key="enrollment.faculty"/></th>
            <th scope="col"><fmt:message key="enrollment.marksSum"/></th>
            <th scope="col"><fmt:message key="enrollment.status"/></th>
        </tr>
        </thead>
        <tbody>
        <tr th:class="${enrollment.status == enrollment.status.FINALIZED} ? 'table-success' : ''"
            th:each="enrollment : ${user.enrollments}">
            <th scope="row" th:text="${enrollment.id}"></th>
            <td>
                <a th:href="@{'/faculty/' + ${enrollment.faculty.id}}" th:text="${enrollment.faculty.nameEn}">
                </a>
            </td>
            <td th:text="${enrollment.marksSum}"></td>
            <td th:text="${enrollment.status}"></td>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>