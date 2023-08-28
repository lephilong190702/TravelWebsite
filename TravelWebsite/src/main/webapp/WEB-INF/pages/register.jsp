<%-- 
    Document   : register
    Created on : Aug 8, 2023, 6:17:20 PM
    Author     : lephi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h1 class="text-center text-danger">ĐĂNG KÝ</h1>
<c:if test="${errMsg != null}">
    <div class="alert alert-danger">
        ${errMsg}
    </div>
</c:if>
<c:url value="/register" var="action" />
<form:form method="post" action="${action}" modelAttribute="user" enctype="multipart/form-data">
    <form:hidden path="userId"/>
    <form:hidden path="userImageurl" />
    <form:errors path="*" element="div" cssClass="alert alert-danger" />
    <div class="mb-3 mt-3">
        <label for="username" class="form-label">Username:</label>
        <form:input type="text" class="form-control" id="username" placeholder="Enter username" path="userUsername"/>
        <form:errors path="userUsername" cssClass="text-danger" />
    </div>
    <div class="mb-3">
        <label for="password" class="form-label">Password:</label>
        <form:input type="password" class="form-control" id="password" placeholder="Enter password" path="userPassword" />
        <form:errors path="userPassword" cssClass="text-danger" />
    </div>
    <div class="mb-3">
        <label for="confirmPassword" class="form-label">Confirm password:</label>
        <form:input type="password" class="form-control" id="confirmPassword" placeholder="Enter confirm password" path="confirmPassword"/>
        <form:errors path="confirmPassword" cssClass="text-danger" />
    </div>
    <div class="mb-3 mt-3">
        <label for="email" class="form-label">Email:</label>
        <form:input type="email" class="form-control" id="email" placeholder="Enter email" path="userEmail"/>
    </div>
    <div class="mb-3 mt-3">
        <label for="phone" class="form-label">Phone:</label>
        <form:input type="number" class="form-control" id="phone" placeholder="Enter phone" path="userPhone"/>
    </div>
    <div class="mb-3 mt-3">
        <label for="file" class="form-label">Avatar:</label>
        <form:input type="file" class="form-control" id="file" placeholder="Enter avatar" path="file"/>
    </div>

    <div class="form-group">
        <input type="submit" value="Đăng ký" class="btn btn-danger"/>
    </div>
</form:form>
