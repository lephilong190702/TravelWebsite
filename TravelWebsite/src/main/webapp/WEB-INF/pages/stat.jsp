<%-- 
    Document   : stats
    Created on : Aug 14, 2023, 5:11:54 PM
    Author     : lephi
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.text.NumberFormat" %>
<!DOCTYPE html>
<h1 class="text-center text-info">THỐNG KÊ</h1>




<section class = "container">
    <table class="table table-hover">
        <h2 class="text-center mt-5 text-success">SỐ LƯỢNG TOUR</h2>
        <thead>
            <tr>
                <th>Điểm đến</th>
                <th>Số lượng tour</th> 
            </tr>
        </thead>
        <tbody>
            <c:forEach items = "${count}" var = "c">
                <tr>
                    <td>${c[1]}</td>
                    <td>${c[2]}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <table class="table table-hover">
        <h2 class="text-center mt-5 text-success">DOANH THU</h2>
        <thead>
            <tr>
                <th>ID</th>
                <th>Tên tour</th>
                <th>Tổng doanh thu</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items = "${stat}" var = "s">
                <tr>
                    <td>${s[0]}</td>
                    <td>${s[1]}</td>
                    <td><fmt:formatNumber type="number" value="${s[2]}" pattern="#,##0" /></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    
    
</section>