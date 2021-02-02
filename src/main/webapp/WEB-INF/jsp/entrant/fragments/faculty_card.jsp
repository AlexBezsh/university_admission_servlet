<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="i18n.messages"/>

<div class="card" style="margin-bottom: 20px; margin-top: 20px">
    <c:if test="${sessionScope.lang.equals('en')}">
        <a class="card-header" href="${pageContext.request.contextPath}/entrant/faculty?facultyId=${faculty.id}">${faculty.nameEn}</a>
    </c:if>
    <c:if test="${sessionScope.lang.equals('ua')}">
        <a class="card-header" href="${pageContext.request.contextPath}/entrant/faculty?facultyId=${faculty.id}">${faculty.nameUa}</a>
    </c:if>
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

                <c:if test="${faculty.active && sessionScope.user.active && !sessionScope.user.isEnrolled(faculty)}">
                    <a class="btn btn-success" style="margin-right: 5px; margin-left: 15px; margin-bottom: 5px;"
                       href="${pageContext.request.contextPath}/entrant/enroll?facultyId=${faculty.id}">
                        <fmt:message key="faculty.enroll"/>
                    </a>
                </c:if>
                <c:if test="${sessionScope.user.isEnrolled(faculty)}">
                    <div class="alert alert-primary" role="alert">
                        <fmt:message key="faculty.message.alreadyRegistered"/>
                    </div>
                </c:if>
            </div>
            <div class="card" style="width: 20rem; margin-left: 10px; margin-right: 10px;">
                <div class="card-body">
                    <h5 class="card-title">
                        <fmt:message key="faculty.subjects"/>
                    </h5>
                    <ul>
                        <c:forEach items="${faculty.subjects}" var="subject">
                            <c:if test="${sessionScope.lang.equals('en')}">
                                <li>${subject.nameEn} (${subject.type})</li>
                            </c:if>
                            <c:if test="${sessionScope.lang.equals('ua')}">
                                <li>${subject.nameUa} (${subject.type})</li>
                            </c:if>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>