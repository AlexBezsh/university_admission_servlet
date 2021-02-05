<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="i18n.messages"/>

 <c:set var="pageInfo" value="${sessionScope.facultiesPageInfo}" scope="page"/>

<c:if test="${pageInfo.totalPages != 0 && pageInfo.page <= pageInfo.totalPages}">
    <nav aria-label="Pagination">
        <ul class="pagination justify-content-center font-weight-medium">

            <li class="page-item ${pageInfo.page == 1 ? 'disabled' : ''}">
                <a class="page-link"
                   href="?page=${pageInfo.page - 1}">
                    <fmt:message key="pageable.previous"/>
                </a>
            </li>

            <li class="page-item disabled">
                <a class="page-link"
                   href="?page=${sessionScope.facultiesPageInfo.page}">${sessionScope.facultiesPageInfo.page}
                    / ${sessionScope.facultiesPageInfo.totalPages}</a>
            </li>

            <li class="${pageInfo.page == pageInfo.totalPages ? 'page-item disabled' : 'page-item'}">
                <a class="page-link"
                   href="?page=${pageInfo.page + 1}">
                    <fmt:message key="pageable.next"/>
                </a>
            </li>
        </ul>
    </nav>
</c:if>
