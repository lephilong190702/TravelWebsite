<%-- 
    Document   : tour
    Created on : Aug 1, 2023, 4:20:35 PM
    Author     : lephi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h1 class="text-center text-info mt-1">QUẢN LÝ TOUR</h1>
<c:url value="/admin/tours" var="action" />
<form:form modelAttribute="tour" method="post" action="${action}" enctype="multipart/form-data">

    <form:hidden path="tourId" />
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="tourTitle" id="tourTitle" 
                    placeholder="Tên tour" name="tour_category" />
        <label for="name">Tên tour</label>
        <form:errors path="tourTitle" element="div" cssClass="text-danger" />
    </div>

    <div class="form-floating mb-3 mt-3">
        <form:input type="number" class="form-control" path="tourAdultPrice" id="tourPrice" 
                    placeholder="Giá tour người lớn" name="tourPrice" />
        <label for="name">Giá tour người lớn</label>
        <form:errors path="tourAdultPrice" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="number" class="form-control" path="tourChildPrice" id="tourPrice" 
                    placeholder="Giá tour trẻ em" name="tourPrice" />
        <label for="name">Giá tour trẻ em</label>
        <form:errors path="tourChildPrice" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating">
        <form:select class="form-select" path="destinationId" id="destinationId" name="destinationId">
            <c:forEach items ="${destinations}" var="d">
                <c:choose>
                    <c:when test="${d.destinationId == tour.destinationId.destinationId}">
                        <option value="${d.destinationId}" selected>${d.destinationCity}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${d.destinationId}">${d.destinationCity}</option>
                    </c:otherwise>
                </c:choose>

            </c:forEach>
        </form:select>
        <label for="destinationId" class="form-label">Chọn địa điểm</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="date" class="form-control" path="tourStartdate" id="tourStartdate" 
                    placeholder="Ngày bắt đầu" name="tourStartdate" />
        <label for="name">Ngày bắt đầu</label>
         <form:errors path="tourStartdate" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="date" class="form-control" path="tourEnddate" id="tourEnddate" 
                    placeholder="Ngày bắt đầu" name="tourEnddate" />
        <label for="name">Ngày kết thúc</label>
        <form:errors path="tourEnddate" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="file" class="form-control" path="files" id="files" 
                    placeholder="Ảnh tour" multiple = "multiple"/>
        <form:label path="imageSet" for="file">Ảnh tour</form:label>
        <form:errors path="files" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <button class ="btn btn-info mt-1" type="submit">
            <c:choose>
                <c:when test="${tour.tourId != null}">
                    Cập nhật tour
                </c:when>
                <c:otherwise>
                    Thêm tour
                </c:otherwise>
            </c:choose>
        </button>
    </div>
</form:form>