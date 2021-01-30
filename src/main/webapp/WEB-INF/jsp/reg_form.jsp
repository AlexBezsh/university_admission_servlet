<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="i18n.messages"/>

<html>
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="registration.form.title"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>

<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="container" style="margin-top: 60px">
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <h2 class="page-header"><fmt:message key="registration.form.header"/></h2>
            <form style="margin-bottom: 30px" name="form" autocomplete="off" action="${pageContext.request.contextPath}/register" <%--th:object="${user}"--%> method="post">
                <div class="form-group">
                    <label for="fullName"><fmt:message key="user.fullName"/></label>
                    <br/><span class="error" style="color:red" <%--th:if="${#fields.hasErrors('fullName')}" th:errors="*{fullName}"--%>></span>
                    <input id="fullName"
                           type="text"
                           class="form-control"
                           <%--th:field="*{fullName}"
                           th:placeholder="#{user.fullName}"--%>
                           required>
                </div>
                <div class="form-group">
                    <label for="email"><fmt:message key="user.email"/></label>
                    <br/><span class="error" style="color:red" <%--th:if="${#fields.hasErrors('email')}" th:errors="*{email}"--%>></span>
                    <input id="email"
                           type="email"
                           class="form-control"
                           <%--th:field="*{email}"
                           th:placeholder="#{user.email}"--%>
                           required>
                </div>
                <div class="form-group">
                    <label for="password"><fmt:message key="user.password"/></label>
                    <br/><span class="error" style="color:red" <%--th:if="${#fields.hasErrors('password')}" th:errors="*{password}"--%>></span>
                    <input id="password"
                           type="password"
                           class="form-control"
                           <%--th:field="*{password}"
                           th:placeholder="#{user.password}"--%>
                           required>
                </div>
                <div class="form-group">
                    <label for="city"><fmt:message key="user.city"/></label>
                    <br/><span class="error" style="color:red" <%--th:if="${#fields.hasErrors('city')}" th:errors="*{city}"--%>></span>
                    <input id="city"
                           type="text"
                           class="form-control"
                           <%--th:field="*{city}"
                           th:placeholder="#{user.city}"--%>
                           required>
                </div>
                <div class="form-group">
                    <label for="region"><fmt:message key="user.region"/></label>
                    <br/><span class="error" style="color:red" <%--th:if="${#fields.hasErrors('region')}" th:errors="*{region}"--%>></span>
                    <input id="region"
                           type="text"
                           class="form-control"
                           <%--th:field="*{region}"
                           th:placeholder="#{user.region}"--%>
                           required>
                </div>
                <div class="form-group">
                    <label for="education"><fmt:message key="user.education"/></label>
                    <br/><span class="error" style="color:red" <%--th:if="${#fields.hasErrors('education')}" th:errors="*{education}"--%>></span>
                    <input id="education"
                           type="text"
                           class="form-control"
                           <%--th:field="*{education}"
                           th:placeholder="#{user.education}"--%>
                           required>
                </div>
                <button type="submit" class="btn btn-default" style="margin-top:30px">
                    <fmt:message key="submit"/>
                </button>
            </form>
        </div>
    </div>
</div>
</body>
</html>