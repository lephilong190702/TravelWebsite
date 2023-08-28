<%-- 
    Document   : employees
    Created on : Aug 21, 2023, 1:09:40 PM
    Author     : lephi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<h1 class="text-center text-info"> QUẢN LÝ NHÂN VIÊN </h1>

<section class = "container">
    <sec:authorize access="hasRole('ROLE_ADMIN')"> 
    <div>
        <a href="<c:url value ="/admin/employees"/>" class = "btn btn-info" mt-2>Thêm nhân viên </a>
    </div>
    </sec:authorize>
    <table class="table table-hover">
        <thead>
            <tr>
                <th>STT</th>
                <th>Họ</th> 
                <th>Tên </th>
                <th>Email </th>
                    <sec:authorize access="hasRole('ROLE_ADMIN')"> 
                    <th>Chức năng</th>
                    </sec:authorize>
            </tr>
        </thead>
        <tbody>
            <c:forEach items = "${employees}" var = "e">
                <tr>
                    <td>${e.employeeId}</td>
                    <td>${e.employeeFirstname}</td>
                    <td>${e.employeeLastname}</td>
                    <td>${e.employeeEmail}</td>
                    <sec:authorize access="hasRole('ROLE_ADMIN')"> 
                        <td>
                            <c:url value ="/admin/employees/${e.employeeId}" var="api"/>
                            <a href ="${api}" class = "btn btn-success">Cập nhật</a>
                           
                            <button class = "btn btn-danger" onClick="deleteNew('${api}')">Xóa</button>
                        </td>
                    </sec:authorize>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</section>