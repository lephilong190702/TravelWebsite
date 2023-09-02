<%-- 
    Document   : topbar
    Created on : Aug 18, 2023, 10:28:13 AM
    Author     : lephi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

    <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
        <i class="fa fa-bars"></i>
    </button>


    <c:url value="/news" var="n"/>
    <c:url value="/" var="action"/>
    <form class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
        <div class="input-group">
            <input type="text" class="form-control bg-light border-0 small" name = "kw" placeholder="Search for..."
                   aria-label="Search" aria-describedby="basic-addon2">
            <div class="input-group-append">
                <button class="btn btn-primary" type="submit">
                    <i class="fas fa-search fa-sm"></i>
                </button>
            </div>
        </div>
    </form>



    <ul class="navbar-nav ml-auto">
        <c:choose>
            <c:when test="${pageContext.request.userPrincipal.name != null}">
                <li class="nav-item">
                    <a class="nav-link text-danger" href="<c:url value="/"/>">${pageContext.request.userPrincipal.name}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-danger" href="<c:url value="/logout"/>">Đăng xuất</a>
                </li>
            </c:when>
            <c:otherwise>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/login"/>">Đăng nhập</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/register"/>">Đăng ký</a>
                </li>
            </c:otherwise>
        </c:choose>  

    </ul>

</nav>