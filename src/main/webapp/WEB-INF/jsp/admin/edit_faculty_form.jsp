<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<html>
<jsp:include page="../fragments/headTag.jsp"/>

<body>
<jsp:include page="../fragments/bodyHeader.jsp"/>

<c:set var="faculty" value="${sessionScope.facultyToEdit}"/>
<div class="container" style="margin-top: 60px">
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <h2 class="page-header"><fmt:message key="faculty.edit.header"/> </h2>
            <form style="margin-bottom: 30px" name="form" autocomplete="off"
                  action="${contextPath}/admin/faculty/edit?facultyId=${faculty.id}" method="post">
                <div class="form-group">
                    <label for="nameUa"><fmt:message key="faculty.nameUa"/></label>
                    <c:if test="${requestScope.nameUaError ne null}">
                        <div class="error" style="color: red;">
                            <span><fmt:message key="faculty.nameUaError"/></span>
                        </div>
                    </c:if>
                    <input id="nameUa"
                           name="nameUa"
                           type="text"
                           value="${faculty.nameUa}"
                           class="form-control"
                           required>
                </div>
                <div class="form-group">
                    <label for="nameEn"><fmt:message key="faculty.nameEn"/></label>
                    <c:if test="${requestScope.nameEnError ne null}">
                        <div class="error" style="color: red;">
                            <span><fmt:message key="faculty.nameEnError"/></span>
                        </div>
                    </c:if>
                    <input id="nameEn"
                           name="nameEn"
                           type="text"
                           value="${faculty.nameEn}"
                           class="form-control"
                           required>
                </div>
                <div class="form-group">
                    <label for="descriptionUa"><fmt:message key="faculty.descriptionUa"/></label>
                    <c:if test="${requestScope.descriptionUaError ne null}">
                        <div class="error" style="color: red;">
                            <span><fmt:message key="faculty.descriptionUaError"/></span>
                        </div>
                    </c:if>
                    <input id="descriptionUa"
                           name="descriptionUa"
                           type="text"
                           value="${faculty.descriptionUa}"
                           class="form-control"
                           required>
                </div>
                <div class="form-group">
                    <label for="descriptionEn"><fmt:message key="faculty.descriptionEn"/></label>
                    <c:if test="${requestScope.descriptionEnError ne null}">
                        <div class="error" style="color: red;">
                            <span><fmt:message key="faculty.descriptionEnError"/></span>
                        </div>
                    </c:if>
                    <input id="descriptionEn"
                           name="descriptionEn"
                           type="text"
                           value="${faculty.descriptionEn}"
                           class="form-control"
                           required>
                </div>
                <div class="form-group">
                    <label for="stateFundedPlaces"><fmt:message key="faculty.stateFundedPlaces"/></label>
                    <c:if test="${requestScope.stateFundedPlacesError ne null}">
                        <div class="error" style="color: red;">
                            <span><fmt:message key="faculty.stateFundedPlacesError"/></span>
                        </div>
                    </c:if>
                    <input id="stateFundedPlaces"
                           name="stateFundedPlaces"
                           type="number"
                           value="${faculty.stateFundedPlaces}"
                           min="0"
                           class="form-control"
                           required>
                </div>
                <div class="form-group">
                    <label for="contractPlaces"><fmt:message key="faculty.contractPlaces"/></label>
                    <c:if test="${requestScope.contractPlacesError ne null}">
                        <div class="error" style="color: red;">
                            <span><fmt:message key="faculty.contractPlacesError"/></span>
                        </div>
                    </c:if>
                    <input id="contractPlaces"
                           name="contractPlaces"
                           type="number"
                           value="${faculty.contractPlaces}"
                           min="0"
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