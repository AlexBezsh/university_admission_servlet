<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<c:choose>
    <c:when test="${empty enrollments}">
        <p style="text-align: center">
            <fmt:message key="enrollments.empty.message"/>
        </p>
    </c:when>
    <c:otherwise>
        <h3 class="page-header" style="text-align: center; margin-top: 30px; margin-bottom: 30px">
            <fmt:message key="enrollments.header"/>
        </h3>
        <table class="table">
            <thead>
            <tr>
                <th scope="col"><fmt:message key="enrollment.id"/></th>
                <th scope="col"><fmt:message key="enrollment.user"/></th>
                <th scope="col"><fmt:message key="enrollment.user.status"/></th>
                <th scope="col"><fmt:message key="enrollment.marksSum"/></th>
                <th scope="col"><fmt:message key="enrollment.status"/></th>
                <th scope="col"><fmt:message key="approve"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${enrollments}" var="enrollment">
                <tr>
                    <th scope="row">${enrollment.id}</th>
                    <td>
                        <a href="${contextPath}/admin/entrant?userId=${enrollment.user.id}">${enrollment.user.fullName}</a>
                    </td>
                    <td><fmt:message key="user.status.${enrollment.user.status}"/></td>
                    <td>${enrollment.marksSum}</td>
                    <td><fmt:message key="enrollment.status.${enrollment.status}"/></td>
                    <td>
                        <c:choose>
                            <c:when test="${enrollment.isNew() && enrollment.user.active && faculty.active}">
                                <a class="btn btn-success"
                                   href="${contextPath}/admin/enrollment/approve?enrollmentId=${enrollment.id}&facultyId=${faculty.id}">
                                    <fmt:message key="approve"/>
                                </a>
                            </c:when>
                            <c:otherwise>
                                <b>-</b>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:otherwise>
</c:choose>