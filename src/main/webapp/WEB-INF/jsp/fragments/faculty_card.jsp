<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<div class="card" style="margin-bottom: 20px; margin-top: 20px">
    <a class="card-header"
       href="${contextPath}/${auth.admin ? 'admin' : 'entrant'}/faculty?facultyId=${faculty.id}">
        ${lang.equals('ua') ? faculty.nameUa : faculty.nameEn}
    </a>
    <div class="card-body">
        <div class="row" style="justify-content: space-between">
            <div class="list-view" style="width: 30rem">
                <p class="card-text" style="margin-right: 10px; width: available">
                    ${lang.equals('ua') ? faculty.descriptionUa : faculty.descriptionEn}
                </p>
                <p class="card-text" style="margin-right: 10px">
                    <fmt:message key="faculty.status"/>: <fmt:message key="faculty.status.${faculty.status}"/></p>
                <p class="card-text" style="margin-right: 10px"><fmt:message
                        key="faculty.stateFundedPlaces"/>: ${faculty.stateFundedPlaces}</p>
                <p class="card-text" style="margin-right: 10px"><fmt:message
                        key="faculty.contractPlaces"/>: ${faculty.contractPlaces}</p>
                <p class="card-text" style="margin-right: 10px"><fmt:message
                        key="faculty.totalPlaces"/>: ${faculty.totalPlaces}</p>
                <c:choose>
                    <c:when test="${auth.admin}">
                        <div class="row">
                            <a class="btn btn-primary" style="margin-right: 5px; margin-left: 25px; margin-bottom: 5px;"
                               href="${contextPath}/admin/faculty/edit?facultyId=${faculty.id}">
                                <fmt:message key="edit"/>
                            </a>
                            <a class="btn btn-danger" style="margin-right: 5px; margin-left: 5px; margin-bottom: 5px;"
                               href="${contextPath}/admin/faculty/delete?facultyId=${faculty.id}">
                                <fmt:message key="delete"/>
                            </a>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <c:if test="${faculty.active && auth.active && !auth.hasAppliedFor(faculty)}">
                            <a class="btn btn-success" style="margin-right: 5px; margin-left: 15px; margin-bottom: 5px;"
                               href="${contextPath}/entrant/enroll?facultyId=${faculty.id}">
                                <fmt:message key="faculty.enroll"/>
                            </a>
                        </c:if>
                        <c:if test="${auth.hasAppliedFor(faculty) && faculty.active}">
                            <div class="alert alert-primary" role="alert">
                                <fmt:message key="faculty.message.alreadyRegistered"/>
                            </div>
                        </c:if>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="card" style="width: 20rem; margin-left: 10px; margin-right: 10px;">
                <div class="card-body">
                    <h5 class="card-title">
                        <fmt:message key="faculty.subjects"/>
                    </h5>
                    <ul>
                        <c:forEach items="${faculty.subjects}" var="subject">
                            <li>${lang.equals('ua') ? subject.nameUa : subject.nameEn}
                                (<fmt:message key="subject.type.${subject.type}"/>)
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
