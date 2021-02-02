<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="i18n.messages"/>

<html>
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="faculty.enrollment.title"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>

<jsp:include page="../fragments/bodyHeader.jsp"/>

<div class="container" style="margin-top: 60px">
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <c:if test="${sessionScope.lang.equals('en')}">
                <h3 class="special-text" style="margin-bottom: 30px;">${enrollment.faculty.nameEn}</h3>
            </c:if>
            <c:if test="${sessionScope.lang.equals('ua')}">
                <h3 class="special-text" style="margin-bottom: 30px;">${enrollment.faculty.nameUa}</h3>

            </c:if>
            <p><b><fmt:message key="faculty.enrollment.marks"/></b></p>
            <form style="margin-bottom: 30px" name="form" autocomplete="off"
                  action="${pageContext.request.contextPath}/entrant/enroll"
                  method="post">
                <c:forEach items="${enrollment.marks}" var="mark">
                    <c:if test="${sessionScope.lang.equals('en')}">
                        <p> ${mark.subject.nameEn} (${mark.subject.type})</p>
                    </c:if>
                    <c:if test="${sessionScope.lang.equals('ua')}">
                        <p> ${mark.subject.nameUa} (${mark.subject.type})</p>
                    </c:if>
                    <label>
                        <input name="${mark.subject.nameEn} ${mark.subject.type}" type="number" min="0" step="0.01" required/>
                    </label>
                </c:forEach>
                <br/>
                <button type="submit" class="btn btn-success" style="margin-top:30px">
                    <fmt:message key="submit"/>
                </button>
            </form>
        </div>
    </div>
</div>
</body>
</html>