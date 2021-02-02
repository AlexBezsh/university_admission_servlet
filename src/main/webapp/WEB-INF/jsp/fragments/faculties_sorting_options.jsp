<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="i18n.messages"/>

<div style="margin-left: 45px;">
    <b style="font-size: 18px;"><fmt:message key="faculties.sort.label"/></b>
    <a class="btn btn-default" style="margin-bottom: 5px;"
       href="?sort=name_${sessionScope.lang}">
        <fmt:message key="faculties.sort.a-z"/>
    </a>
    <a class="btn btn-default" style="margin-bottom: 5px;"
       href="?sort=name_${sessionScope.lang},desc">
        <fmt:message key="faculties.sort.z-a"/>
    </a>
    <a class="btn btn-default" style="margin-bottom: 5px;"
       href="?sort=stateFundedPlaces,desc">
        <fmt:message key="faculties.sort.stateFundedPlaces"/>
    </a>
    <a class="btn btn-default" style="margin-bottom: 5px;"
       href="?sort=contractPlaces,desc">
        <fmt:message key="faculties.sort.contractPlaces"/>
    </a>
</div>