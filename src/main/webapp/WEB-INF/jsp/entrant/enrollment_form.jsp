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
            <h3 class="special-text" style="margin-bottom: 30px;" th:text="${enrollment.faculty.nameEn}"></h3>
            <p><b><fmt:message key="faculty.enrollment.marks"/></b></p>
            <form style="margin-bottom: 30px" name="form" autocomplete="off"
                  th:action="@{'/faculty/' + ${enrollment.faculty.id} + '/enroll'}"
                  th:object="${enrollment}" method="post">
                <label>
                    <input hidden
                           name="faculty"
                           th:value="${enrollment.faculty}"
                           th:field="*{faculty}"
                    />
                </label>
                <div th:each="mark,i : ${enrollment.marks}">
                    <p th:text="${' ' + mark.subject.nameEn + ' (' + mark.subject.type + ')'}"></p>
                    <label>
                        <input hidden th:value="${mark.id}" th:field="*{marks[__${i.index}__].id}"/>
                    </label>
                    <label>
                        <input hidden th:value="${mark.enrollment.id}"
                               th:field="*{marks[__${i.index}__].enrollment.id}"/>
                    </label>
                    <label>
                        <input hidden th:value="${mark.subject.id}" th:field="*{marks[__${i.index}__].subject.id}"/>
                    </label>
                    <label>
                        <input type="number" min="0" step="0.01" th:field="*{marks[__${i.index}__].mark}"/>
                    </label>
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