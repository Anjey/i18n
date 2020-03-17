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

    <title><spring:message code='own-submission.title' /></title>
    <link rel="stylesheet" href="css/ownSubmissionStyle.css">
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


    <c:if test="${not empty submissions}">
        <table id="customers">
            <tr>
                <th><spring:message code='own-submission.number' /></th>
                <th><spring:message code='own-submission.priority' /></th>
                <th><spring:message code='own-submission.is-entered' /></th>
                <th><spring:message code='home.name-surname' /></th>
                <th><spring:message code='own-submission.speciality' /></th>
                <th><spring:message code='own-submission.university' /></th>
                <th><spring:message code='home.average-mark' /></th>
            </tr>
            <c:forEach items="${submissions}" var="currentSubmission">
                <tr>
                    <td>${currentSubmission.place}</td>
                    <td>${currentSubmission.priority}</td>
                    <td>${currentSubmission.entered}</td>
                    <td>${currentSubmission.user.firstName} ${currentSubmission.user.lastName}</td>
                    <td>${currentSubmission.speciality.name}</td>
                    <td>${currentSubmission.speciality.university.name}</td>
                    <td>${currentSubmission.averageMark}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

</div>
</body>
</html>
