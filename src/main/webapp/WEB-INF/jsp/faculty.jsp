<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<html>
<jsp:include page="fragments/head_tag.jsp"/>

<body>
<jsp:include page="fragments/body_header.jsp"/>

<div style="margin-left: 30px; margin-right: 30px;">
    <jsp:include page="fragments/faculty_card.jsp"/>

    <c:set var="enrollments" value="${faculty.enrollments}" scope="request"/>
    <c:if test="${auth.admin}">
        <div class="row" style="justify-content: space-between">
            <div>
            </div>
            <c:if test="${not empty enrollments && faculty.active}">
                <a class="btn btn-secondary"
                   style="margin-right: 45px; margin-left: 5px; margin-top: 5px; margin-bottom: 5px;"
                   href="${contextPath}/admin/faculty/finalize?facultyId=${faculty.id}">
                    <fmt:message key="faculty.finalize"/>
                </a>
            </c:if>
        </div>
    </c:if>

    <c:choose>
        <c:when test="${faculty.active}">
            <jsp:include page="${auth.admin ? 'admin' : 'entrant'}/fragments/faculty_enrollments.jsp"/>
        </c:when>
        <c:otherwise>
            <jsp:include page="fragments/final_list.jsp"/>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>