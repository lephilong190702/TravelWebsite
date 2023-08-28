<%-- 
    Document   : addnew
    Created on : Aug 6, 2023, 6:36:13 PM
    Author     : lephi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h1 class="text-center text-info mt-1">QUẢN LÝ TIN TỨC</h1>


<c:url value="/admin/news" var="action" />
<form:form modelAttribute="new" method="post" action="${action}" enctype="multipart/form-data">
    
    <form:hidden path="newsId" />
    <form:hidden path="newsImageurl" />
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="newsTitle" id="newsTitle" 
                    placeholder="Tiêu đề" name="newsTitle" />
        <label for="name">Tiêu đề</label>
        <form:errors path="newsTitle" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="newsContent" id="newsContent" 
                    placeholder="Nội dung" name="newsContent" />
        <label for="name">Nội dung</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="file" class="form-control" path="file" id="file" 
                    placeholder="Hình ảnh"/>
        <label for="file">Hình ảnh</label>
    </div>
    
    <div class="form-floating mb-3 mt-3">
        <button class ="btn btn-info mt-1" type="submit">
            <c:choose>
                <c:when test="${news.newsId != null}">
                    Cập nhật tin tức
                </c:when>
                <c:otherwise>
                    Thêm tin tức
                </c:otherwise>
            </c:choose>
        </button>
    </div>
</form:form>