<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<html>
<jsp:include page="../fragments/head_tag.jsp"/>

<body>
<jsp:include page="../fragments/body_header.jsp"/>

<div style="margin-top: 25px; margin-bottom: 25px; margin-left: 50px; margin-right: 50px;" class="alert alert-success">
    <c:if test="${auth.enrolledContract}">
        <span><fmt:message key="congratulation.contract"/></span>
    </c:if>
    <c:if test="${auth.enrolledStateFunded}">
    <span><fmt:message key="congratulation.stateFunded"/></span>
    </c:if>
</div>
</body>
</html>