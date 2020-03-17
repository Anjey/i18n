<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="css/sidebarMenuStyle.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.css" rel="stylesheet">
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="js/script.js"></script>
</head>
<body>
<nav class="main-menu">
    <div class="scrollbar" id="style-1">
        <ul>
            <li>
                <a href="/home">
                    <i class="fa fa-home fa-lg"></i>
                    <span class="nav-text"><spring:message code='home.title' /></span>
                </a>
            </li>

            <security:authorize access="hasRole('ADMINISTRATOR')">
            <li>
                <a href="/university-control">
                    <i class="fa fa-university fa-lg"></i>
                    <span class="nav-text"><spring:message code='sidebar.add-university' /></span>
                </a>
            </li>


            <li>
                <a href="/specialityControl">
                    <i class="fa fa-plus-circle fa-lg"></i>
                    <span class="nav-text"><spring:message code='sidebar.add-speciality' /></span>
                </a>
            </li>
            </security:authorize>

            <security:authorize access="hasRole('USER')">
            <li>
                <a href="/examsResult">
                    <i class="fa fa-calculator fa-lg"></i>
                    <span class="nav-text"><spring:message code='sidebar.set-exams' /></span>
                </a>
            </li>

            <li>
                <a href="/getSpecialitiesForSubmission">
                    <i class="fa fa-passport fa-lg"></i>
                    <span class="nav-text"><spring:message code='sidebar.submission-documents' /></span>
                </a>
            </li>
            </security:authorize>
            <li>
                <a href="/submissionOnSpeciality">
                    <i class="fa fa-th fa-lg"></i>
                    <span class="nav-text"><spring:message code='sidebar.speciality-submission' /></span>
                </a>
            </li>

            <li>
                <a onclick="document.forms['logoutForm'].submit()">
                    <i class="fa fa-sign-out-alt fa-lg"></i>
                    <span class="nav-text"><spring:message code='sidebar.exit' /></span>
                </a>
            </li>
        </ul>
    </div>
</nav>
</body>
</html>