<%-- 
    Document   : stats
    Created on : Aug 14, 2023, 5:11:54 PM
    Author     : lephi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<h1 class="text-center text-info">THỐNG KÊ</h1>
<section class = "container">
    <table class="table table-hover">
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
</section>