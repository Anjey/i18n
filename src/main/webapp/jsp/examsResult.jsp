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

    <title><spring:message code='exam.result' /></title>
    <link rel="stylesheet" href="css/examsStyle.css">
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="js/script.js"></script>
</head>
<body>

<jsp:include page="sidebarMenu.jsp"/>

<div class="main-content">
    <h2><spring:message code='exam.enter-results' /></h2>

    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
    </c:if>

    <form:form id="set-exams-results-form" method="POST" action="${contextPath}/addExams" enctype="multipart/form-data">
        <div class="subject-data">
            <c:if test="${not empty subjects}">
                <div class="subject">
                    <div class="subject-name">
                        <select id="subject1" name="subject1"></select>
                    </div>
                    <div class="mark">
                        <input type="number" name="mark1" placeholder="<spring:message code='exam.mark' />">
                    </div>
                </div>

                <div class="subject">
                    <div class="subject-name">
                        <select id="subject2" name="subject2"></select>
                    </div>
                    <div class="mark">
                        <input type="number" name="mark2">
                    </div>
                </div>

                <div class="subject">
                    <div class="subject-name">
                        <select id="subject3" name="subject3"></select>
                    </div>
                    <div class="mark">
                        <input type="number" name="mark3">
                    </div>
                </div>

                <div class="subject">
                    <div class="subject-name">
                        <select id="subject4" name="subject4"></select>
                    </div>
                    <div class="mark">
                        <input type="number" name="mark4">
                    </div>
                </div>


                <div class="subject">
                    <div class="subject-name">
                        <select id="subject5" name="subject5">
                            <option value="CERTIFICATE"><spring:message code='exam.CERTIFICATE' /></option>
                        </select>
                    </div>
                    <div class="mark">
                        <input type="number" name="mark5">
                    </div>
                </div>
                <div class="subject image-upload">
                    <input type="file" name="image">
                </div>
                <c:forEach items="${subjects}" var="currentSubject">
                    <script>
                        var tag = "<option value=\"${currentSubject}\"><spring:message code='exam.${currentSubject}' /></option>";
                        document.getElementById('subject1').innerHTML += tag;
                        document.getElementById('subject2').innerHTML += tag;
                        document.getElementById('subject3').innerHTML += tag;
                        document.getElementById('subject4').innerHTML += tag;
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