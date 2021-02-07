<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<html>
<jsp:include page="../fragments/head_tag.jsp"/>

<body>
<jsp:include page="../fragments/body_header.jsp"/>

<div class="container" style="margin-top: 60px">
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <h3 class="special-text"
                style="margin-bottom: 30px;">
                ${lang.equals('ua') ? enrollment.faculty.nameUa : enrollment.faculty.nameEn}
            </h3>
            <p><b><fmt:message key="enrollment.marks"/></b></p>

            <form style="margin-bottom: 30px" name="form" autocomplete="off"
                  action="${contextPath}/entrant/enroll"
                  method="post">
                <c:forEach items="${enrollment.marks}" var="mark">
                    <p> ${lang.equals('ua') ? mark.subject.nameUa : mark.subject.nameEn} (<fmt:message
                            key="subject.type.${mark.subject.type}"/>)
                    </p>
                    <label>
                        <input name="${mark.subject.nameEn} ${mark.subject.type}" type="number" min="0" step="0.01"
                               required/>
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