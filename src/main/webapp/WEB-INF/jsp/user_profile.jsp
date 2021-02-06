<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>

<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div style="margin-left: 30px; margin-right: 30px;">
    <div class="card" style="margin-bottom: 20px; margin-top: 20px">
        <a class="card-header">${user.fullName}</a>
        <div class="card-body">
            <div class="list-view" style="width: 30rem">
                <p class="card-text" style="margin-right: 10px">
                    <fmt:message key="user.status"/>: <fmt:message key="user.status.${user.status}"/>
                </p>
                <p class="card-text" style="margin-right: 10px"><fmt:message key="user.email"/>: ${user.email}</p>
                <p class="card-text" style="margin-right: 10px"><fmt:message key="user.city"/>: ${user.city}</p>
                <p class="card-text" style="margin-right: 10px"><fmt:message key="user.region"/>: ${user.region}</p>
                <p class="card-text" style="margin-right: 10px"><fmt:message key="user.education"/>: ${user.education}</p>
                <c:if test="${auth.admin}">
                    <c:if test="${user.active}">
                        <a class="btn btn-danger" style="margin-right: 5px; margin-left: 20px;"
                           href="${contextPath}/admin/user/block?userId=${user.id}">
                            <fmt:message key="user.block"/>
                        </a>
                    </c:if>
                    <c:if test="${user.blocked}">
                        <a class="btn btn-warning" style="margin-right: 5px; margin-left: 20px;"
                           href="${contextPath}/admin/user/unblock?userId=${user.id}">
                            <fmt:message key="user.unblock"/>
                        </a>
                    </c:if>
                </c:if>
            </div>
        </div>
    </div>

    <c:set var="enrollments" value="${user.enrollments}" scope="request"/>
    <jsp:include page="fragments/user_enrollments.jsp"/>
</div>
</body>
</html>