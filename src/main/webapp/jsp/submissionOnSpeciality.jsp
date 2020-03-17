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

    <title>Welcome</title>
    <link rel="stylesheet" href="css/submissionOnSpecialityStyle.css">
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

    <form:form id="submission-on-speciality-form" method="POST" action="${contextPath}/getSubmission">
        <select id="speciality" name="speciality"> </select>

        <c:if test="${not empty universities}" >
            <c:forEach items="${universities}" var="currentUniversity">
                <script>
                    document.getElementById("speciality").innerHTML +=
                        "<optgroup id=\"${currentUniversity.id}\" label=\"${currentUniversity.name}\">  </optgroup>"
                </script>
            </c:forEach>
        </c:if>

        <c:if test="${not empty specialities}">

            <c:forEach items="${specialities}" var="currentSpeciality">
                <script>
                    document.getElementById('${currentSpeciality.university.id}').innerHTML +=
                        "<option value=\"${currentSpeciality.id}\"> ${currentSpeciality.name} </option>"
                </script>
            </c:forEach>

        </c:if>

        <div class="send-speciality-data">
            <input type="submit" value="Apply" id="submitBtn">
            <input type="hidden" name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
        </div>
    </form:form>
</div>
</body>
</html>
