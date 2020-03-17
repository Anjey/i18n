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

    <title><spring:message code='get-specialities.title' /></title>
    <link rel="stylesheet" href="css/getSpecialitiesForSubmissionStyle.css">
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

    <form:form id="get-specialities-form" method="POST" action="${contextPath}/getSpecialities">
        <div class="speciality-data">

            <c:if test="${not empty universities}">
                <select id="university" name="university">
<%--                    <option value="none"> none </option>--%>
                </select>
                <c:forEach items="${universities}" var="currentUniversity">
                    <script>
                        document.getElementById('university').innerHTML +=
                            "<option value=\"${currentUniversity.id}\"> ${currentUniversity.name} </option>"
                    </script>
                </c:forEach>
            </c:if>
        </div>

        <div class="send-speciality-data">
            <input type="submit" value="<spring:message code='exam.apply' />" id="submitBtn">
            <input type="hidden" name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
        </div>
    </form:form>
</div>
</body>
</html>