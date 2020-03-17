<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title><spring:message code='submission.title' /></title>
    <link rel="stylesheet" href="css/submissionStyle.css">
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="js/script.js"></script>
</head>
<body>

<jsp:include page="sidebarMenu.jsp"/>

<div class="main-content">
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
    </c:if>

    <form:form method="POST" action="${contextPath}/submissionDocuments">
        <c:if test="${not empty specialities}">
            <c:forEach items="${specialities}" var="currentSpeciality">
                <div class="card">
                    <div class="left-side">
                        <div class="top">
                            <h3>${currentSpeciality.name}</h3>
                        </div>
                        <div class="bottom">
                            <c:forEach items="${currentSpeciality.subjectsAndValue}" var="currentSubject">
                                <div class="subject-and-value">
                                    <div class="subject">
                                        <h4><spring:message code='exam.${currentSubject.subjects}' /></h4>
                                    </div>
                                    <div class="value">
                                        <h4>${currentSubject.value}</h4>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="right-side">
                        <div class="top-university-info">
                            <h3>${currentSpeciality.university.name}</h3>

                            <h4><spring:message code='submission.free-place' /> ${currentSpeciality.numberOfStudentsForEntering}</h4>
                        </div>

                        <div class="submissionApply">
                            <input id="${currentSpeciality.id}" type="submit" name="" onclick="setName(this.id)"
                                   value="Apply">
                            <input type="hidden" name="${_csrf.parameterName}"
                                   value="${_csrf.token}"/>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </c:if>
    </form:form>
</div>
<script>
    function setName(id) {
        document.getElementById(id).setAttribute("name", "chosen-speciality");
        document.getElementById(id).setAttribute("value", id);
    }
</script>
</body>
</html>
