<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<html>
<jsp:include page="fragments/head_tag.jsp"/>

<body>
<jsp:include page="fragments/body_header.jsp"/>

<c:set var="pageInfo" value="${sessionScope.facultiesPageInfo}" scope="request"/>
<div style="margin-left: 30px; margin-right: 30px;">
    <div class="row" style="justify-content: space-between; margin-top: 10px; margin-right: 0; margin-left: 0;">
        <div>
        </div>
        <c:if test="${auth.admin}">
            <a class="btn btn-secondary"
               style="margin-right: 10px; margin-left: 10px; margin-top: 5px; margin-bottom: 5px;"
               href="${contextPath}/admin/faculty/new">
                <fmt:message key="faculty.new"/>
            </a>
        </c:if>
    </div>

    <h3 class="page-header" style="text-align: center; margin-top: 5px; margin-bottom: 30px">
        <fmt:message key="faculties.header"/>
    </h3>

    <div class="row" style="justify-content: space-between; margin-left: 0; margin-right: 0;">
        <div>
            <jsp:include page="fragments/sorting_options.jsp"/>
        </div>
        <div>
            <jsp:include page="fragments/page_size_options.jsp"/>
        </div>
    </div>

    <c:choose>
        <c:when test="${empty faculties}">
            <p style="text-align: center; margin-top: 20px; margin-bottom: 20px;">
                <fmt:message key="faculties.empty.message"/>
            </p>
        </c:when>
        <c:otherwise>
            <c:forEach items="${faculties}" var="faculty">
                <c:set var="faculty" value="${faculty}" scope="request"/>
                <jsp:include page="fragments/faculty_card.jsp"/>
            </c:forEach>
        </c:otherwise>
    </c:choose>
    <jsp:include page="fragments/pagination.jsp"/>
</div>
</body>
</html>