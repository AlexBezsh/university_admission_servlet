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
        <h3 class="page-header" style="text-align: center; margin-top: 30px; margin-bottom: 30px"><fmt:message
                key="enrollments.header"/></h3>
        <table class="table">
            <thead>
            <tr>
                <th scope="col"><fmt:message key="enrollment.id"/></th>
                <th scope="col"><fmt:message key="enrollment.faculty"/></th>
                <th scope="col"><fmt:message key="enrollment.faculty.status"/></th>
                <th scope="col"><fmt:message key="enrollment.marksSum"/></th>
                <th scope="col"><fmt:message key="enrollment.status"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${enrollments}" var="enrollment">
                <tr class="${enrollment.finalized ? 'table-success' : ''}">
                    <th scope="row">${enrollment.id}</th>
                    <td>
                        <a href="${contextPath}/${auth.admin ? 'admin' : 'entrant'}/faculty?facultyId=${enrollment.faculty.id}">
                                ${lang.equals('ua') ? enrollment.faculty.nameUa : enrollment.faculty.nameEn}
                        </a>
                    </td>
                    <td><fmt:message key="faculty.status.${enrollment.faculty.status}"/></td>
                    <td>${enrollment.marksSum}</td>
                    <td><fmt:message key="enrollment.status.${enrollment.status}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:otherwise>
</c:choose>
