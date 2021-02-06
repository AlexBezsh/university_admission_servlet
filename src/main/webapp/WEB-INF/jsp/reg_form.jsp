<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>

<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="container" style="margin-top: 60px">
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <h2 class="page-header"><fmt:message key="registration.form.header"/></h2>

            <form style="margin-bottom: 30px" name="form" autocomplete="off" action="${contextPath}/register" method="post">
                <div class="form-group">
                    <label for="fullName"><fmt:message key="user.fullName"/></label>
                    <c:if test="${param.fullNameError ne null}">
                        <div class="error" style="color: red;">
                            <span><fmt:message key="user.fullNameError"/></span>
                        </div>
                    </c:if>
                    <input id="fullName"
                           type="text"
                           name="fullName"
                           class="form-control"
                           required>
                </div>
                <div class="form-group">
                    <label for="email"><fmt:message key="user.email"/></label>
                    <c:if test="${param.emailError ne null}">
                        <div class="error" style="color: red;">
                            <span><fmt:message key="user.emailError"/></span>
                        </div>
                    </c:if>
                    <input id="email"
                           type="email"
                           name="email"
                           class="form-control"
                           required>
                </div>
                <div class="form-group">
                    <label for="password"><fmt:message key="user.password"/></label>
                    <c:if test="${param.passwordError ne null}">
                        <div class="error" style="color: red;">
                            <span><fmt:message key="user.passwordError"/></span>
                        </div>
                    </c:if>
                    <input id="password"
                           type="password"
                           name="password"
                           class="form-control"
                           required>
                </div>
                <div class="form-group">
                    <label for="city"><fmt:message key="user.city"/></label>
                    <c:if test="${param.cityError ne null}">
                        <div class="error" style="color: red;">
                            <span><fmt:message key="user.cityError"/></span>
                        </div>
                    </c:if>
                    <input id="city"
                           type="text"
                           name="city"
                           class="form-control"
                           required>
                </div>
                <div class="form-group">
                    <label for="region"><fmt:message key="user.region"/></label>
                    <c:if test="${param.regionError ne null}">
                        <div class="error" style="color: red;">
                            <span><fmt:message key="user.regionError"/></span>
                        </div>
                    </c:if>
                    <input id="region"
                           type="text"
                           name="region"
                           class="form-control"
                           required>
                </div>
                <div class="form-group">
                    <label for="education"><fmt:message key="user.education"/></label>
                    <c:if test="${param.educationError ne null}">
                        <div class="error" style="color: red;">
                            <span><fmt:message key="user.educationError"/></span>
                        </div>
                    </c:if>
                    <input id="education"
                           type="text"
                           name="education"
                           class="form-control"
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