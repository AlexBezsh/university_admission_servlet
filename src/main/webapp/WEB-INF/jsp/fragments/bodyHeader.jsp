<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<nav class="navbar navbar-expand-lg navbar-light bg-light justify-content-between">
    <a class="navbar-brand" href="${contextPath}/">
        <fmt:message key="navbar.title"/>
    </a>

    <div class="row" style="margin-right: 10px">
        <div style="margin-right: 20px">
            <c:url var="params" value="">
                <c:forEach items="${param}" var="entry">
                    <c:if test="${entry.key != 'lang'}">
                        <c:param name="${entry.key}" value="${entry.value}"/>
                    </c:if>
                </c:forEach>
            </c:url>

            <a class="btn btn-default" href="${params}${not empty params ? '&' : '?'}lang=ua"><fmt:message
                    key="lang.ua.label"/></a>
            <a class="btn btn-default" href="${params}${not empty params ? '&' : '?'}lang=en"><fmt:message
                    key="lang.en.label"/></a>
        </div>

        <c:if test="${auth.entrant}">
            <a class="btn btn-success" style="margin-right: 5px; margin-left: 20px;"
               href="${contextPath}/entrant/profile">
                <fmt:message key="user.profile"/>
            </a>
        </c:if>

        <c:choose>
            <c:when test="${auth ne null}">
                <a class="btn btn-primary" href="${contextPath}/logout">
                    <fmt:message key="navbar.logout"/>
                </a>
            </c:when>
            <c:otherwise>
                <div style="margin-left: 10px; margin-right: 3px;">
                    <a class="btn btn-success" href="${contextPath}/login">
                        <fmt:message key="login.button"/>
                    </a>
                </div>
                <div style="margin-left: 3px; margin-right: 5px;">
                    <a class="btn btn-primary" href="${contextPath}/register">
                        <fmt:message key="registration.button"/>
                    </a>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</nav>
