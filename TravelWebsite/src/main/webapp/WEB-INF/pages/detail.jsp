<%-- 
    Document   : detail
    Created on : Aug 22, 2023, 8:07:58 PM
    Author     : lephi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<h1 class="text-center text-info">CHI TIẾT TOUR</h1>

<div class="container">
    <div>
        <c:forEach items="${tour.imageSet}" var="t">
            <img src="${t.imageUrl}" alt="${tour.tourTitle}" width="200" height="200"/>
        </c:forEach>
        <h1 class="mt-2">${tour.tourTitle}</h1>
        <h4>${tour.tourPrice} VND</h4>
        <button class="btn btn-primary">Đặt vé</button>
        <div>
            <p class="mt-5">${tour.tourDescription}</p>
        </div>
    </div> 

    <c:url value="/api/tours/${tour.tourId}/comments" var="api"/>
    <sec:authorize access="!isAuthenticated()">
        <strong>Vui lòng <a href="<c:url value="/login" />">Đăng nhập</a> để bình luận!!!</strong>
    </sec:authorize>

    <sec:authorize access="isAuthenticated()">
        <div class="form-group">
            <textarea class="form-control" placeholder="Nhập nội dung bình luận" id="contentId" ></textarea>
        </div>
        <button class="btn btn-danger" onclick="addComment('${api}', ${tour.tourId})">Thêm bình luận</button>
    </sec:authorize>
        
    <ul id="comments" class="list-group"">
    </ul>
</div>
<script src="<c:url value="/resources/js/main.js" />"></script>
<script>
    window.onload = function () {
        loadComments('${api}');
    }
</script>


