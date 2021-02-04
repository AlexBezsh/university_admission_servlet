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

    <div class="card" style="margin-bottom: 20px; margin-top: 20px">
        <a class="card-header">${user.fullName}</a>
        <div class="card-body">
            <div class="list-view" style="width: 30rem">
                <p class="card-text" style="margin-right: 10px"><fmt:message key="user.status"/>: ${user.status}</p>
                <p class="card-text" style="margin-right: 10px"><fmt:message key="user.email"/>: ${user.email}</p>
                <p class="card-text" style="margin-right: 10px"><fmt:message key="user.city"/>: ${user.city}</p>
                <p class="card-text" style="margin-right: 10px"><fmt:message key="user.region"/>: ${user.region}</p>
                <p class="card-text" style="margin-right: 10px"><fmt:message key="user.education"/>: ${user.education}</p>
                <c:if test="${user.active}">
                    <a class="btn btn-danger" style="margin-right: 5px; margin-left: 20px;"
                       href="/admin/user/block?userId=${user.id}">
                    <fmt:message key="user.block"/>
                    </a>
                </c:if>
                <c:if test="${user.blocked}">
                    <a class="btn btn-warning" style="margin-right: 5px; margin-left: 20px;"
                       href="/admin/user/unblock?userId=${user.id}">
                        <fmt:message key="user.unblock"/>
                    </a>
                </c:if>
            </div>
        </div>
    </div>

    <c:choose>
        <c:when test="${empty user.enrollments}">
            <p style="text-align: center">
                <fmt:message key="enrollments.empty.message"/>
            </p>
        </c:when>
        <c:otherwise>
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
                <c:forEach items="${user.enrollments}" var="enrollment">
                    <tr class="${enrollment.finalized ? 'table-success' : ''}">
                        <th scope="row">${enrollment.id}</th>
                        <td>
                            <c:choose>
                                <c:when test="${sessionScope.lang.equals('ua')}">
                                    <a href="${pageContext.request.contextPath}/admin/faculty?facultyId=${enrollment.faculty.id}">${enrollment.faculty.nameUa}</a>
                                </c:when>
                                <c:otherwise>
                                    <a href="${pageContext.request.contextPath}/admin/faculty?facultyId=${enrollment.faculty.id}">${enrollment.faculty.nameEn}</a>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>${enrollment.faculty.status}</td>
                        <td>${enrollment.marksSum}</td>
                        <td>${enrollment.status}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>