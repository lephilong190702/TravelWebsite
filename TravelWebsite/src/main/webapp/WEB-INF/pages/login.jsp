<%-- 
    Document   : login
    Created on : Aug 6, 2023, 4:30:22 PM
    Author     : lephi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<h1 class="text-center text-danger">ĐĂNG NHẬP</h1>
<c:if test="${param.error != null}">
    <div class="alert alert-danger">
        Vui lòng quay lại sau
    </div>
</c:if>
<c:if test="${param.accessDenied != null}">
    <div class="alert alert-danger">
        Bạn không có quyền truy cập!
    </div>
</c:if>
<c:url value="/login" var="action"/>
<form method="post" action="${action}">
    <div class="mb-3 mt-3">
        <label for="username" class="form-label">Username:</label>
        <input type="text" class="form-control" id="username" placeholder="Enter username" name="username"/>
    </div>
    <div class="mb-3">
        <label for="pwd" class="form-label">Password:</label>
        <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="password"/>
    </div>
    <div class="form-group">
        <input type="submit" value="Đăng nhập" class="btn btn-danger"/>
    </div>
</form>
