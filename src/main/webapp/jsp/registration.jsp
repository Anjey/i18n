<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title><spring:message code='login.create-account' /></title>

    <link rel="stylesheet" type="text/css" href="css/opensans-font.css">
    <link rel="stylesheet" type="text/css" href="fonts/line-awesome/css/line-awesome.min.css">
    <!-- Jquery -->
    <link rel="stylesheet" href="https://jqueryvalidation.org/files/demo/site-demos.css">
    <!-- Main Style Css -->
    <link rel="stylesheet" href="css/registrationStyle.css"/>

    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="js/script.js"></script>
</head>
<body class="form-v7">
<div class="page-content">
    <div class="form-v7-content">
        <div class="form-left">
            <img src="images/form-v7.jpg" alt="form">
            <p class="text-1"><spring:message code='register.sign-up' /></p>
            <p class="text-2">Privaci policy & Term of service</p>
        </div>

        <form:form method="POST" modelAttribute="userForm" class="form-detail" action="#" id="myform">

            <spring:bind path="firstName">
                <div class="form-row ${status.error ? 'has-error' : ''}">
                    <form:label path="firstName" for="username"><spring:message code='register.name' /></form:label>
                    <form:input type="text" name="username" id="username" path="firstName" class="input-text"
                                autofocus="true" required="required"/>
                    <form:errors path="firstName"/>
                </div>
            </spring:bind>

            <spring:bind path="lastName">
                <div class="form-row ${status.error ? 'has-error' : ''}">
                    <form:label path="lastName" for="lastName"><spring:message code='register.lastName' /></form:label>
                    <form:input type="text" name="lastName" id="lastName" path="lastName" class="input-text"
                                autofocus="true" required="required"/>
                    <form:errors path="lastName"/>
                </div>
            </spring:bind>

            <spring:bind path="email">
                <div class="form-row ${status.error ? 'has-error' : ''}">
                    <form:label path="email" for="your_email"><spring:message code='register.email' /></form:label>
                    <form:input type="text" name="your_email" id="your_email" path="email" class="input-text"
                                autofocus="true" pattern="[^@]+@[^@]+.[a-zA-Z]{2,6}" required="required"/>
                    <form:errors path="email"/>
                </div>
            </spring:bind>

            <spring:bind path="password">
                <div class="form-row ${status.error ? 'has-error' : ''}">
                    <form:label path="password" for="password"><spring:message code="register.password" /></form:label>
                    <form:input type="password" name="password" id="password" path="password" class="input-text"
                                autofocus="true" required="required"/>
                    <form:errors path="password"/>
                </div>
            </spring:bind>

            <div class="form-row">
                <label for="confirm_password"><spring:message code='register.confirm-password' /></label>
                <input type="password" name="confirm_password" id="confirm_password" class="input-text" required>
            </div>

            <div class="form-row-last">
                <input class="register" name="register" type="submit" value="<spring:message code='register.submit' />"/>
                <p>Or <a href="${contextPath}/login"><spring:message code='login.submit' /></a></p>
            </div>
        </form:form>
    </div>
</div>
</body>
</html>
