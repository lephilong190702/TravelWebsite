<%-- 
    Document   : employee
    Created on : Aug 21, 2023, 2:31:02 PM
    Author     : lephi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h1 class="text-center text-info mt-1">QUẢN LÝ NHÂN VIÊN</h1>

<c:url value="/admin/employees" var="action" />
<form:form modelAttribute="employee" method="post" action="${action}" enctype="multipart/form-data">

    <form:hidden path="employeeId" />
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="employeeFirstname" id="employeeFirstname" 
                    placeholder="Họ" name="employeeFirstname" />
        <label for="name">Họ</label>
        <form:errors path="employeeFirstname" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="employeeLastname" id="employeeLastname" 
                    placeholder="Họ" name="employeeLastname" />
        <label for="name">Tên</label>
        <form:errors path="employeeLastname" element="div" cssClass="text-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="email" class="form-control" path="employeeEmail" id="employeeEmail" 
                    placeholder="Email"/>
        <label for="email">Email</label>
    </div>
    <div class="form-floating">
        <form:select class="form-select" path="tourId" id="tourId" name="tourId">
            <c:forEach items ="${tours}" var="t">
                <c:choose>
                    <c:when test="${t.tourId == employee.tourId.tourId}">
                        <option value="${t.tourId}" selected>${t.tourTitle}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${t.tourId}">${t.tourTitle}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </form:select>
        <label for="tourId" class="form-label">Chọn tour phụ trách</label>
    </div>

    <div class="form-floating mb-3 mt-3">
        <button class ="btn btn-info mt-1" type="submit">
            <c:choose>
                <c:when test="${employee.employeeId != null}">
                    Cập nhật nhân viên
                </c:when>
                <c:otherwise>
                    Thêm nhân viên
                </c:otherwise>
            </c:choose>
        </button>
    </div>
</form:form>
