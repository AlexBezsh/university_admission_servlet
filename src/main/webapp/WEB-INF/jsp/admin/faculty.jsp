<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="i18n.messages"/>

<html>
<head>
    <meta charset="UTF-8">
    <c:choose>
        <c:when test="${sessionScope.lang.equals('ua')}">
            <title>${faculty.nameUa}</title>
        </c:when>
        <c:otherwise>
            <title>${faculty.nameEn}</title>
        </c:otherwise>
    </c:choose>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>

<jsp:include page="../fragments/bodyHeader.jsp"/>

<div style="margin-left: 30px; margin-right: 30px;">

    <jsp:include page="../admin/fragments/faculty_card.jsp"/>

    <div class="row" style="justify-content: space-between">
        <div>
        </div>
        <c:if test="${faculty.active}">
            <a class="btn btn-secondary"
               style="margin-right: 45px; margin-left: 5px; margin-top: 5px; margin-bottom: 5px;"
               href="/admin/faculty/finalize?facultyId=${faculty.id}">
                <fmt:message key="faculty.finalize"/>
            </a>
        </c:if>
    </div>

    <c:set var="enrollments" value="${faculty.enrollments}" scope="request"/>

    <c:choose>
        <c:when test="${faculty.active}">
            <jsp:include page="../admin/fragments/enrollments_list.jsp"/>
        </c:when>
        <c:otherwise>
            <jsp:include page="../fragments/final_list.jsp"/>
        </c:otherwise>
    </c:choose>


</div>
</body>
</html>