<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="i18n.messages"/>

<html>
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="faculties.title"/></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>

<jsp:include page="../fragments/bodyHeader.jsp"/>

<h3 class="page-header" style="text-align: center; margin-top: 30px; margin-bottom: 30px">
    <fmt:message key="faculties.header"/>
</h3>

<div class="row" style="justify-content: space-between">
    <div style="margin-left: 45px;">
        <b style="font-size: 18px;" ><fmt:message key="faculties.sort.label"/></b>
        <a class="btn btn-default" style="margin-bottom: 5px;"
           th:href="@{'?sort=name' + ${#strings.capitalize(#locale)}}">
            <fmt:message key="faculties.sort.a-z"/>
        </a>
        <a class="btn btn-default" style="margin-bottom: 5px;"
           th:href="@{'?sort=name' + ${#strings.capitalize(#locale)} + ',desc'}">
            <fmt:message key="faculties.sort.z-a"/>
        </a>
        <a class="btn btn-default" style="margin-bottom: 5px;"
           th:href="@{'?sort=stateFundedPlaces,desc'}">
            <fmt:message key="faculties.sort.stateFundedPlaces"/>
        </a>
        <a class="btn btn-default" style="margin-bottom: 5px;"
           th:href="@{'?sort=contractPlaces,desc'}">
            <fmt:message key="faculties.sort.contractPlaces"/>
        </a>
    </div>
    <div sec:authorize="hasAuthority('ADMIN')">
        <a class="btn btn-secondary" style="margin-right: 45px; margin-left: 45px; margin-top: 5px; margin-bottom: 5px;"
           th:href="@{'/faculty/new'}">
            <fmt:message key="faculty.new"/>
        </a>
    </div>
</div>

<div style="margin-left: 30px; margin-right: 30px;">

    <div th:if="${faculties.totalElements eq 0}">
        <p style="text-align: center">
            <fmt:message key="faculty.empty.message"/>
        </p>
    </div>

    <div th:each="faculty : ${faculties.content}">
        <div class="card" style="margin-bottom: 10px; margin-top: 10px">
            <a class="card-header" th:href="@{'/faculty/' + ${faculty.id}}" th:text="${faculty.name}"></a>
            <div class="card-body">
                <div class="row" style="justify-content: space-between">
                    <div class="list-view" style="width: 30rem">
                        <p class="card-text" style="margin-right: 10px; width: available"
                           th:text="${faculty.description}"></p>
                        <p class="card-text" style="margin-right: 10px"
                           th:text="#{faculty.status} + ${': ' + faculty.status}"></p>
                        <p class="card-text" style="margin-right: 10px"
                           th:text="#{faculty.stateFundedPlaces} + ${': ' + faculty.stateFundedPlaces}"></p>
                        <p class="card-text" style="margin-right: 10px"
                           th:text="#{faculty.contractPlaces} + ${': ' + faculty.contractPlaces}"></p>
                        <p class="card-text" style="margin-right: 10px"
                           th:text="#{faculty.totalPlaces} + ${': ' + faculty.totalPlaces}"></p>
                        <div th:if="${faculty.status == faculty.status.ACTIVE}">
                            <div sec:authorize="hasAuthority('ENTRANT')">
                                <a class="btn btn-success" style="margin-right: 5px; margin-left: 15px; margin-bottom: 5px;"
                                   th:href="@{'/faculty/' + ${faculty.id} + '/enroll'}">
                                    <fmt:message key="faculty.enroll"/>
                                </a>
                            </div>
                            <div class="row" sec:authorize="hasAuthority('ADMIN')">
                                <a class="btn btn-primary" style="margin-right: 5px; margin-left: 25px; margin-bottom: 5px;"
                                   th:href="@{'/faculty/' + ${faculty.id} + '/edit'}">
                                    <fmt:message key="edit"/>
                                </a>
                                <a class="btn btn-danger" style="margin-right: 5px; margin-left: 5px; margin-bottom: 5px;"
                                   th:href="@{'/faculty/' + ${faculty.id} + '/delete'}">
                                    <fmt:message key="delete"/>
                                </a>
                            </div>
                        </div>
                    </div>
                    <div class="card" style="width: 20rem; margin-left: 10px; margin-right: 10px;">
                        <div class="card-body">
                            <h5 class="card-title">
                                <fmt:message key="faculty.subjects"/>
                            </h5>
                            <ul th:each="subject : ${faculty.subjects}">
                                <li th:text="${subject.name + ' (' + subject.type + ')'}"></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div th:if="${faculties.totalElements gt 0}">
    <nav aria-label="Pagination">
        <ul class="pagination justify-content-center font-weight-medium">

            <li class="page-item" th:classappend="${faculties.number eq 0} ? 'disabled'">
                <a class="page-link" th:href="@{'?page=' + ${faculties.number - 1} + '&size=' + ${faculties.size} + '&sort=' + ${faculties.sort.toString().replace(': ', ',')}}">
                    <fmt:message key="pageable.previous"/>
                </a>
            </li>

            <li class="page-item disabled">
                <a class="page-link" th:href="@{'?page=' + ${faculties.number} + '&size=' + ${faculties.size} + '&sort=' + ${faculties.sort.toString().replace(': ', ',')}}"
                   th:text="${faculties.number + 1} + ' / ' + ${faculties.totalPages}"></a>
            </li>

            <li class="page-item" th:classappend="${(faculties.number + 1) eq faculties.totalPages} ? 'disabled'">
                <a class="page-link" th:href="@{'?page=' + ${faculties.number + 1} + '&size=' + ${faculties.size} + '&sort=' + ${faculties.sort.toString().replace(': ', ',')}}">
                    <fmt:message key="pageable.next"/>
                </a>
            </li>
        </ul>
    </nav>
</div>

</body>
</html>