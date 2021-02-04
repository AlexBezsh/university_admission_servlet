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

<h3 class="page-header" style="text-align: center; margin-top: 30px; margin-bottom: 30px">${faculty.nameEn} (<fmt:message key="faculty.finalList"/>)</h3>

<div style="margin-left: 30px; margin-right: 30px;">
    <c:set value="${faculty.enrollments}" var="enrollments"/>
    <c:choose>
        <c:when test="${empty enrollments}">
            <p style="text-align: center"><fmt:message key="enrollments.empty.message"/></p>
        </c:when>
        <c:otherwise>
            <h3 class="page-header" style="text-align: center; margin-top: 30px; margin-bottom: 30px"><fmt:message
                    key="enrollments.header"/></h3>
            <table class="table">
                <thead>
                <tr>
                    <th scope="col"><fmt:message key="enrollment.id"/></th>
                    <th scope="col"><fmt:message key="enrollment.user"/></th>
                    <th scope="col"><fmt:message key="enrollment.user.status"/></th>
                    <th scope="col"><fmt:message key="enrollment.marksSum"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${enrollments}" var="enrollment">
                    <tr>
                        <th scope="row">${enrollment.id}</th>
                        <td>
                            <a href="${pageContext.request.contextPath}/admin/user?userId=${enrollment.user.id}">${enrollment.user.fullName}</a>
                        </td>
                        <td>${enrollment.user.status}</td>
                        <td>${enrollment.marksSum}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>