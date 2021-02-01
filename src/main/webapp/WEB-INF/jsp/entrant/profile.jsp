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
        <a class="card-header">${sessionScope.user.fullName}</a>
        <div class="card-body">
            <div class="list-view" style="width: 30rem">
                <p class="card-text" style="margin-right: 10px"><fmt:message key="user.status"/>: ${sessionScope.user.status}</p>
                <p class="card-text" style="margin-right: 10px"><fmt:message key="user.email"/>: ${sessionScope.user.email}</p>
                <p class="card-text" style="margin-right: 10px"><fmt:message key="user.city"/>: ${sessionScope.user.city}</p>
                <p class="card-text" style="margin-right: 10px"><fmt:message key="user.region"/>: ${sessionScope.user.region}</p>
                <p class="card-text" style="margin-right: 10px"><fmt:message
                        key="user.education"/>: ${sessionScope.user.education}</p>
            </div>
        </div>
    </div>

    <c:if test="${empty enrollments}">
        <h3 class="page-header"
            style="text-align: center; margin-top: 30px; margin-bottom: 30px"><fmt:message
                key="enrollments.empty.message"/></h3>
    </c:if>

    <c:if test="${not empty enrollments}">
        <h3 class="page-header" style="text-align: center; margin-top: 30px; margin-bottom: 30px"><fmt:message key="enrollments.header"/></h3>
        <table class="table">
            <thead>
            <tr>
                <th scope="col"><fmt:message key="enrollment.id"/></th>
                <th scope="col"><fmt:message key="enrollment.faculty"/></th>
                <th scope="col"><fmt:message key="enrollment.faculty.status"/></th>
                <th scope="col"><fmt:message key="enrollment.marksSum"/></th>
                <th scope="col"><fmt:message key="enrollment.status"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${enrollments}" var="enrollment">
                <tr class="${enrollment.finalized ? 'table-success' : ''}">
                    <th scope="row">${enrollment.id}</th>
                    <td>
                        <c:if test="${sessionScope.lang.equals('en')}">
                            <a href="/entrant/faculty?facultyId=${faculty.id}">${faculty.nameEn}</a>
                        </c:if>
                        <c:if test="${sessionScope.lang.equals('ua')}">
                            <a href="/entrant/faculty?facultyId=${faculty.id}">${faculty.nameUa}</a>
                        </c:if>
                    </td>
                    <td>${enrollment.faculty.status}</td>
                    <td>${enrollment.marksSum}</td>
                    <td>${enrollment.status}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>
</body>
</html>