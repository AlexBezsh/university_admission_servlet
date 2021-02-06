<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<div style="margin-left: 30px; margin-right: 30px;">
    <c:set value="${faculty.enrollments}" var="enrollments"/>
    <c:choose>
        <c:when test="${empty enrollments}">
            <p style="text-align: center"><fmt:message key="enrollments.empty.message"/></p>
        </c:when>
        <c:otherwise>
            <h3 class="page-header" style="text-align: center; margin-top: 30px; margin-bottom: 30px">
                <fmt:message key="faculty.finalList"/>
            </h3>

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
                        <c:choose>
                            <c:when test="${auth.admin}">
                                <td><a href="${contextPath}/admin/user?userId=${enrollment.user.id}">${enrollment.user.fullName}</a></td>
                            </c:when>
                            <c:otherwise>
                                <td>${enrollment.user.fullName}</td>
                            </c:otherwise>
                        </c:choose>
                        <td><fmt:message key="user.status.${enrollment.user.status}"/></td>
                        <td>${enrollment.marksSum}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>
</div>