<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="i18n.messages"/>

<html>
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="faculty.create.title"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>

<jsp:include page="../fragments/bodyHeader.jsp"/>

<div class="container" style="margin-top: 60px">
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <h2 class="page-header"><fmt:message key="faculty.create.header"/></h2>
            <form style="margin-bottom: 30px" name="form" autocomplete="off"
                  action="${pageContext.request.contextPath}/admin/faculty/new" method="post">
                <div class="form-group">
                    <label for="nameUa"><fmt:message key="faculty.nameUa"/></label>
                    <c:if test="${param.nameUaError ne null}">
                        <div class="error" style="color: red;">
                            <span><fmt:message key="Size.faculty.nameUa"/></span>
                        </div>
                    </c:if>
                    <input id="nameUa"
                           name="nameUa"
                           type="text"
                           class="form-control"
                           required>
                </div>
                <div class="form-group">
                    <label for="nameEn"><fmt:message key="faculty.nameEn"/></label>
                    <c:if test="${param.nameEnError ne null}">
                        <div class="error" style="color: red;">
                            <span><fmt:message key="Size.faculty.nameEn"/></span>
                        </div>
                    </c:if>
                    <input id="nameEn"
                           name="nameEn"
                           type="text"
                           class="form-control"
                           required>
                </div>
                <div class="form-group">
                    <label for="descriptionUa"><fmt:message key="faculty.descriptionUa"/></label>
                    <c:if test="${param.descriptionUaError ne null}">
                        <div class="error" style="color: red;">
                            <span><fmt:message key="Size.faculty.descriptionUa"/></span>
                        </div>
                    </c:if>
                    <input id="descriptionUa"
                           name="descriptionUa"
                           type="text"
                           class="form-control"
                           required>
                </div>
                <div class="form-group">
                    <label for="descriptionEn"><fmt:message key="faculty.descriptionEn"/></label>
                    <c:if test="${param.descriptionEnError ne null}">
                        <div class="error" style="color: red;">
                            <span><fmt:message key="Size.faculty.descriptionEn"/></span>
                        </div>
                    </c:if>
                    <input id="descriptionEn"
                           name="descriptionEn"
                           type="text"
                           class="form-control"
                           required>
                </div>
                <div class="form-group">
                    <label for="stateFundedPlaces"><fmt:message key="faculty.stateFundedPlaces"/></label>
                    <c:if test="${param.stateFundedPlacesError ne null}">
                        <div class="error" style="color: red;">
                            <span><fmt:message key="Min.faculty.stateFundedPlaces"/></span>
                        </div>
                    </c:if>
                    <input id="stateFundedPlaces"
                           name="stateFundedPlaces"
                           type="number"
                           min="0"
                           class="form-control"
                           required>
                </div>
                <div class="form-group">
                    <label for="contractPlaces"><fmt:message key="faculty.contractPlaces"/></label>
                    <c:if test="${param.contractPlacesError ne null}">
                        <div class="error" style="color: red;">
                            <span><fmt:message key="Min.faculty.contractPlaces"/></span>
                        </div>
                    </c:if>
                    <input id="contractPlaces"
                           name="contractPlaces"
                           type="number"
                           min="0"
                           class="form-control"
                           required>
                </div>
                <div>
                    <p><b><fmt:message key="faculty.subjects"/> </b></p>
                    <c:forEach items="${subjects}" var="subject">
                        <input id="${subject.nameEn} ${subject.type}"
                               type="checkbox"
                               name="${subject.nameEn} ${subject.type}"
                               class="check-box"
                        />
                        <c:choose>
                            <c:when test="${sessionScope.lang.equals('ua')}">
                                <label for="${subject.nameEn} ${subject.type}">${subject.nameUa}
                                    (${subject.type})</label>
                            </c:when>
                            <c:otherwise>
                                <label for="${subject.nameEn} ${subject.type}">${subject.nameEn}
                                    (${subject.type})</label>
                            </c:otherwise>
                        </c:choose>
                        <br/>
                    </c:forEach>
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