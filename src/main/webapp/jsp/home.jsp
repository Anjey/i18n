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

    <title><spring:message code='home.title' /></title>
        <link rel="stylesheet" href="css/homeStyle.css">
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

    <c:if test="${not empty exams}">
        <table id="customers">
            <tr>
                <th><spring:message code='home.name-surname' /></th>
                <th><spring:message code='exam.CERTIFICATE' /></th>
                <th><spring:message code='home.average-mark' /></th>
                <th><spring:message code='home.certificate-photo' /></th>
                <th><spring:message code='home.accept' /></th>
                <th><spring:message code='home.refuse' /></th>
            </tr>
            <c:forEach items="${exams}" var="currentExam">
                <form:form action="${contextPath}/aRApply" method="post">
                    <tr>
                        <td>${currentExam.user.firstName} ${currentExam.user.lastName}</td>
                        <td><spring:message code='exam.${currentExam.subject}' /></td>
                        <td>${currentExam.mark}</td>
                        <td class="image">
                            <a href="data:image/jpg;base64, ${currentExam.user.encodedImage}" target="_blank">
                                <spring:message code='home.open-photo' />
                            </a>
                        </td>

                        <td><input id="${currentExam.user.email}A" type="submit" onclick="setParameter(this.id)"
                                   value="Accept"></td>
                        <td><input id="${currentExam.user.email}R" type="submit" onclick="setParameter(this.id)"
                                   value="Refuse"></td>

                        <input type="hidden" id="${currentExam.user.email}">
                    </tr>
                </form:form>
            </c:forEach>
        </table>
    </c:if>

    <c:if test="${not empty user}">
        <form:form action="${contextPath}/stopSubmission" method="post">
            <input type="submit" value="Stop session">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        </form:form>
    </c:if>
</div>

<script>
    function setParameter(id) {
        var value = document.getElementById(id).getAttribute("value");
        console.log(value);

        debugger;
        var stringLength = id.length;
        if (stringLength > 1) {
            var userEmail = id.slice(0, stringLength - 1);
            document.getElementById(id).setAttribute("name", "email");
            document.getElementById(id).setAttribute("value", userEmail);


            document.getElementById(userEmail).setAttribute("name", "button");
            document.getElementById(userEmail).setAttribute("value", value);
        }
    }
</script>

</body>
</html>