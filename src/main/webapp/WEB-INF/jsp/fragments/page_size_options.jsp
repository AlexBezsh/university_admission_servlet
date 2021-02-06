<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<div style="margin-left: 10px; margin-right: 10px;">
    <b style="font-size: 18px; margin-right: 5px;"><fmt:message key="pageSize.label"/></b>
    <a class="btn ${pageInfo.size == 1 ? 'btn-light' : 'btn-default'}" style="margin-bottom: 5px;"
       href="?size=1">
        1
    </a>
    <a class="btn ${pageInfo.size == 2 ? 'btn-light' : 'btn-default'}" style="margin-bottom: 5px;"
       href="?size=2">
        2
    </a>
    <a class="btn ${pageInfo.size == 4 ? 'btn-light' : 'btn-default'}" style="margin-bottom: 5px;"
       href="?size=4">
        4
    </a>
</div>