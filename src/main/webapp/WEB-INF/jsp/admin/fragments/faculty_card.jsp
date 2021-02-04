<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="i18n.messages"/>

<div class="card" style="margin-bottom: 20px; margin-top: 20px">
    <c:choose>
        <c:when test="${sessionScope.lang.equals('ua')}">
            <a class="card-header" href="${pageContext.request.contextPath}/admin/faculty?facultyId=${faculty.id}">${faculty.nameUa}</a>
        </c:when>
        <c:otherwise>
            <a class="card-header" href="${pageContext.request.contextPath}/admin/faculty?facultyId=${faculty.id}">${faculty.nameEn}</a>
        </c:otherwise>
    </c:choose>
    <div class="card-body">
        <div class="row" style="justify-content: space-between">
            <div class="list-view" style="width: 30rem">
                <c:if test="${sessionScope.lang.equals('en')}">
                    <p class="card-text" style="margin-right: 10px; width: available">${faculty.descriptionEn}</p>
                </c:if>
                <c:if test="${sessionScope.lang.equals('ua')}">
                    <p class="card-text" style="margin-right: 10px; width: available">${faculty.descriptionUa}</p>
                </c:if>
                <p class="card-text" style="margin-right: 10px"><fmt:message
                        key="faculty.status"/>: ${faculty.status}</p>
                <p class="card-text" style="margin-right: 10px"><fmt:message
                        key="faculty.stateFundedPlaces"/>: ${faculty.stateFundedPlaces}</p>
                <p class="card-text" style="margin-right: 10px"><fmt:message
                        key="faculty.contractPlaces"/>: ${faculty.contractPlaces}</p>
                <p class="card-text" style="margin-right: 10px"><fmt:message
                        key="faculty.totalPlaces"/>: ${faculty.totalPlaces}</p>
                <div class="row">
                    <a class="btn btn-primary" style="margin-right: 5px; margin-left: 25px; margin-bottom: 5px;"
                       href="${pageContext.request.contextPath}/admin/faculty/edit?facultyId=${faculty.id}">
                        <fmt:message key="edit"/>
                    </a>
                    <a class="btn btn-danger" style="margin-right: 5px; margin-left: 5px; margin-bottom: 5px;"
                       href="${pageContext.request.contextPath}/admin/faculty/delete?facultyId=${faculty.id}">
                    <fmt:message key="delete"/>
                    </a>
                </div>
            </div>
            <div class="card" style="width: 20rem; margin-left: 10px; margin-right: 10px;">
                <div class="card-body">
                    <h5 class="card-title">
                        <fmt:message key="faculty.subjects"/>
                    </h5>
                    <ul>
                        <c:forEach items="${faculty.subjects}" var="subject">
                            <c:choose>
                                <c:when test="${sessionScope.lang.equals('ua')}">
                                    <li>${subject.nameUa} (${subject.type})</li>
                                </c:when>
                                <c:otherwise>
                                    <li>${subject.nameEn} (${subject.type})</li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
