<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<c:if test="${pageInfo.totalPages != 0 && pageInfo.page <= pageInfo.totalPages && pageInfo.page > 0}">
    <nav aria-label="Pagination">
        <ul class="pagination justify-content-center font-weight-medium">
            <li class="page-item ${pageInfo.page <= 1 ? 'disabled' : ''}">
                <a class="page-link"
                   href="?page=${pageInfo.page - 1}">
                    <fmt:message key="pageable.previous"/>
                </a>
            </li>

            <li class="page-item disabled">
                <a class="page-link"
                   href="?page=${pageInfo.page}">${pageInfo.page}
                    / ${pageInfo.totalPages}</a>
            </li>

            <li class="page-item ${pageInfo.page >= pageInfo.totalPages ? 'disabled' : ''}">
                <a class="page-link"
                   href="?page=${pageInfo.page + 1}">
                    <fmt:message key="pageable.next"/>
                </a>
            </li>
        </ul>
    </nav>
</c:if>
