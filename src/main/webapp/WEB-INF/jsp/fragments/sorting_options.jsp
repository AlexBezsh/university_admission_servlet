<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<c:set var="name" value="name_${lang}"/>

<div style="margin-left: 10px; margin-right: 10px;">
    <b style="font-size: 18px; margin-right: 10px;"><fmt:message key="sort.label"/></b>
    <a class="btn ${pageInfo.criteria.toString().equals(name.concat(' ASC')) ? 'btn-light' : 'btn-default'}"
       style="margin-bottom: 5px;"
       href="?sort=${name},asc">
        <fmt:message key="sort.a-z"/>
    </a>
    <a class="btn ${pageInfo.criteria.toString().equals(name.concat(' DESC')) ? 'btn-light' : 'btn-default'}"
       style="margin-bottom: 5px;"
       href="?sort=${name}">
        <fmt:message key="sort.z-a"/>
    </a>
    <a class="btn ${pageInfo.criteria.column.equals('state_funded_places') ? 'btn-light' : 'btn-default'}"
       style="margin-bottom: 5px;"
       href="?sort=state_funded_places">
        <fmt:message key="sort.stateFundedPlaces"/>
    </a>
    <a class="btn ${pageInfo.criteria.column.equals('total_places') ? 'btn-light' : 'btn-default'}"
       style="margin-bottom: 5px;"
       href="?sort=total_places">
        <fmt:message key="sort.totalPlaces"/>
    </a>
</div>