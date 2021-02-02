<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="i18n.messages"/>

<h3>Pagination</h3>

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
