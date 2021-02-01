<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="i18n.messages"/>

<nav class="navbar navbar-expand-lg navbar-light bg-light justify-content-between">

    <a class="navbar-brand" href="${pageContext.request.contextPath}/">
        <fmt:message key="navbar.title"/>
    </a>

    <div class="row" style="margin-right: 10px">
        <div style="margin-right: 20px">
            <c:url var="params" value="">
                <c:forEach items="${param}" var="entry">
                    <c:if test="${entry.key != 'lang'}">
                        <c:param name="${entry.key}" value="${entry.value}" />
                    </c:if>
                </c:forEach>
            </c:url>

            <c:choose>
                <c:when test="${not empty params}">
                    <a class="btn btn-default" href="${params}&lang=ua"><fmt:message key="lang.ua.label"/></a>
                </c:when>
                <c:otherwise>
                    <a class="btn btn-default" href="${params}?lang=ua"><fmt:message key="lang.ua.label"/></a>
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${not empty params}">
                    <a class="btn btn-default" href="${params}&lang=en"><fmt:message key="lang.en.label"/></a>
                </c:when>
                <c:otherwise>
                    <a class="btn btn-default" href="${params}?lang=en"><fmt:message key="lang.en.label"/></a>
                </c:otherwise>
            </c:choose>
        </div>

        <c:if test="${sessionScope.user.entrant}">
            <a class="btn btn-success" style="margin-right: 5px; margin-left: 20px;"
               href="${pageContext.request.contextPath}/entrant/profile">
                <fmt:message key="user.profile"/>
            </a>
        </c:if>

        <c:if test="${sessionScope.user ne null}">
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/logout">
                <fmt:message key="navbar.logout"/>
            </a>
        </c:if>

        <c:if test="${sessionScope.user == null}">
            <div style="margin-left: 20px; margin-right: 3px;">
                <a class="btn btn-success" href="${pageContext.request.contextPath}/login">
                    <fmt:message key="login.button"/>
                </a>
            </div>
            <div style="margin-left: 3px; margin-right: 5px;">
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/register">
                    <fmt:message key="registration.button"/>
                </a>
            </div>
        </c:if>

    </div>
</nav>
