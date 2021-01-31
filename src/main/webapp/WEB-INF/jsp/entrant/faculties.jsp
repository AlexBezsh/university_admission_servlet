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
    <div style="margin-left: 45px;">
        <b style="font-size: 18px;"><fmt:message key="faculties.sort.label"/></b>
        <a class="btn btn-default" style="margin-bottom: 5px;"
           href="?sort=name_${sessionScope.lang}">
            <fmt:message key="faculties.sort.a-z"/>
        </a>
        <a class="btn btn-default" style="margin-bottom: 5px;"
           href="?sort=name_${sessionScope.lang},desc'}">
            <fmt:message key="faculties.sort.z-a"/>
        </a>
        <a class="btn btn-default" style="margin-bottom: 5px;"
           href="?sort=stateFundedPlaces,desc">
            <fmt:message key="faculties.sort.stateFundedPlaces"/>
        </a>
        <a class="btn btn-default" style="margin-bottom: 5px;"
           href="?sort=contractPlaces,desc">
            <fmt:message key="faculties.sort.contractPlaces"/>
        </a>
    </div>
</div>

<div style="margin-left: 30px; margin-right: 30px;">

    <c:if test="${empty faculties}">
        <p style="text-align: center">
            <fmt:message key="faculties.empty.message"/>
        </p>
    </c:if>

    <c:forEach items="${faculties}" var="faculty">
        <c:set var="faculty" value="${faculty}" scope="request"/>
        <jsp:include page="../entrant/fragments/faculty_card.jsp"/>
    </c:forEach>

</div>

<%--<div th:if="${faculties.totalElements gt 0}">
    <nav aria-label="Pagination">
        <ul class="pagination justify-content-center font-weight-medium">

            <li class="page-item" th:classappend="${faculties.number eq 0} ? 'disabled'">
                <a class="page-link"
                   th:href="@{'?page=' + ${faculties.number - 1} + '&size=' + ${faculties.size} + '&sort=' + ${faculties.sort.toString().replace(': ', ',')}}">
                    <fmt:message key="pageable.previous"/>
                </a>
            </li>

            <li class="page-item disabled">
                <a class="page-link"
                   th:href="@{'?page=' + ${faculties.number} + '&size=' + ${faculties.size} + '&sort=' + ${faculties.sort.toString().replace(': ', ',')}}"
                   th:text="${faculties.number + 1} + ' / ' + ${faculties.totalPages}"></a>
            </li>

            <li class="page-item" th:classappend="${(faculties.number + 1) eq faculties.totalPages} ? 'disabled'">
                <a class="page-link"
                   th:href="@{'?page=' + ${faculties.number + 1} + '&size=' + ${faculties.size} + '&sort=' + ${faculties.sort.toString().replace(': ', ',')}}">
                    <fmt:message key="pageable.next"/>
                </a>
            </li>
        </ul>
    </nav>
</div>--%>

</body>
</html>