<%-- 
    Document   : news
    Created on : Aug 2, 2023, 11:04:21 PM
    Author     : lephi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<h1 class="text-center text-info"> QUẢN LÝ TIN TỨC </h1>
<c:url value="/news" var="action"/>
<section class = "container">
    <sec:authorize access="hasRole('ROLE_ADMIN') or hasRole('ROLE_EMPLOYEE')"> 
    <div>
        <a href="<c:url value="/admin/news"/>" class = "btn btn-info" mt-2>Thêm tin tức </a>
    </div>
    </sec:authorize>
    
    <c:if test="${counter > 1}">
        <ul class="pagination mt-2">
            <li class="page-item"><a class="page-link" href="${action}">Tất cả</a></li>
                <c:forEach begin="1" end ="${counter}" var ="i">
                    <c:url value = "" var="pageAction">
                        <c:param name="page" value="${i}" />
                    </c:url>
                <li class="page-item"><a class="page-link" href="${pageAction}">${i}</a></li>
                </c:forEach>
        </ul>
    </c:if>
    <table class="table table-hover">
        <thead>
            <tr>
                <th>STT</th>
                <th>Hình ảnh</th> 
                <th>Tiêu đề </th>
                <th>Nội dung </th>
                <sec:authorize access="hasRole('ROLE_ADMIN') or hasRole('ROLE_EMPLOYEE')"> 
                <th>Chức năng</th>
                </sec:authorize>
            </tr>
        </thead>
        <tbody>
            <c:forEach items = "${news}" var = "n">
                <tr>
                    <td>${n.newsId}</td>
                    <td>
                        <img src="${n.newsImageurl}" alt="${n.newsTitle}" width="200" height="130"/>
                    </td>

                    <td>${n.newsTitle}</td>
                     <td>${n.newsContent}</td>
                    <sec:authorize access="hasRole('ROLE_ADMIN') or hasRole('ROLE_EMPLOYEE')"> 
                    <td>
                        <c:url value ="/admin/news/${n.newsId}" var="api"/>
                        <a href ="${api}" class = "btn btn-success">Cập nhật</a>
                        <button class = "btn btn-danger" onClick="deleteNew('${api}')">Xóa</button>
                    </td>
                    </sec:authorize>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</section>

<script src="<c:url value="resources/js/main.js" />"></script>