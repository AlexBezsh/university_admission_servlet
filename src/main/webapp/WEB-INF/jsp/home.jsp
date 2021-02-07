<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<html>
<jsp:include page="fragments/head_tag.jsp"/>

<body>
<jsp:include page="fragments/body_header.jsp"/>

<div class="container" style="margin-top: 60px">
    <div class="special-text">
        <span><fmt:message key="home.description"/> </span>
    </div>
</div>
</body>
</html>