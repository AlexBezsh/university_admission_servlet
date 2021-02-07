<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<html>
<jsp:include page="fragments/head_tag.jsp"/>

<body>
<jsp:include page="fragments/body_header.jsp"/>

<div class="container" style="margin-top: 60px;">
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <h2 class="page-header"><fmt:message key="login.header"/></h2>
            <c:if test="${param.error ne null}">
                <div class="alert alert-danger">
                    <span><fmt:message key="login.error"/></span>
                </div>
            </c:if>
            <c:if test="${param.registrationSuccess ne null}">
                <div class="alert alert-success">
                    <span><fmt:message key="registration.success"/></span>
                </div>
            </c:if>
            <c:if test="${param.userBlocked ne null}">
                <div class="alert alert-danger">
                    <span><fmt:message key="login.userBlocked.message"/></span>
                </div>
            </c:if>
            <c:if test="${param.userIsLogged ne null}">
                <div class="alert alert-danger">
                    <span><fmt:message key="login.userIsLogged.message"/></span>
                </div>
            </c:if>
            <form style="margin-bottom: 30px" autocomplete="off" action="${contextPath}/login"
                  method="post">
                <div class="form-group">
                    <label for="email"><fmt:message key="user.email"/></label>
                    <input id="email"
                           name="email"
                           type="email"
                           class="form-control"
                           required>
                </div>
                <div class="form-group">
                    <label for="password"><fmt:message key="user.password"/></label>
                    <input id="password"
                           name="password"
                           type="password"
                           class="form-control"
                           required>
                </div>
                <div class="row">
                    <div class="col-md-8 col-md-offset-2">
                        <div style="float: left;">
                            <button type="submit" class="btn btn-default" style="margin-top:10px">
                                <fmt:message key="submit"/>
                            </button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
