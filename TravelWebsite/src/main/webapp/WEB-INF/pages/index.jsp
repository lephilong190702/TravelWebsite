<%-- 
    Document   : index
    Created on : Jul 25, 2023, 11:14:50 PM
    Author     : lephi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %> 
<h1 class="text-center text-info"> QUẢN LÝ ĐIỂM ĐẾN </h1>

<c:url value="/" var="action"/>
<section class = "container">
    <sec:authorize access="hasRole('ROLE_ADMIN')"> 
        <div>
            <a href="<c:url value="/admin/tours"/>" class = "btn btn-info" mt-2>Thêm tour </a>
        </div>
    </sec:authorize>
    <c:if test="${counter > 1}">
        <ul class="pagination mt-2">
            <li class="page-item"><a class="page-link" href="${action}">Tất cả</a></li>
                <c:forEach begin="1" end ="${counter}" var ="i">
                    <c:url value = "" var="pageAction">
                        <c:if test="${desId != null}">
                            <c:param name="desId" value="${desId}"/>
                        </c:if>
                        <c:param name="page" value="${i}" />
                    </c:url>
                <li class="page-item"><a class="page-link" href="${pageAction}">${i}</a></li>
                </c:forEach>
        </ul>
    </c:if>
    <form class="d-flex" onsubmit="return filterSearchParams();">
        <c:if test="${desId != null}">
            <input hidden class="form-control me-2" type="text" name = "desId" value="${desId}">
        </c:if>
        <input class="form-control me-2" type="number" name = "fromPrice" placeholder="Nhập giá thấp nhất">
        <input class="form-control me-2" type="number" name = "toPrice" placeholder="Nhập giá cao nhất">
        <input class="form-control me-2" type="date" name = "fromDate" placeholder="Nhập ngày bắt đầu">
        <input class="form-control me-2" type="date" name = "toDate" placeholder="Nhập ngày kết thúc">
        <button class="btn btn-primary" type="submit">Tìm</button>
    </form>
    <table class="table table-hover">
        <thead>
            <tr>
                <sec:authorize access="hasRole('ROLE_ADMIN')"> 
                    <th>STT</th>
                    </sec:authorize>
                <th>Hình ảnh</th> 
                <th>Tên </th>
                <th>Giá </th>
                <th>Ngày bắt đầu</th>
                <th>Ngày kết thúc</th>
                    <sec:authorize access="hasRole('ROLE_ADMIN')"> 
                    <th>Chức năng</th>
                    </sec:authorize>

            </tr>
        </thead>

        <c:forEach items = "${tours}" var = "t">
            <tr>
                <sec:authorize access="hasRole('ROLE_ADMIN')"> 
                    <td>${t.tourId}</td>
                </sec:authorize>

                <td>
                    <c:if test="${t.imageSet!= null}">
                        <c:set var="lastImage" value="${t.imageSet[t.imageSet.size() - 1]}" />
                        <a href="<c:url value="/tours/${t.tourId}"/>">
                            <img src="${lastImage.imageUrl}" alt="Last Image" width="200" height="140">
                        </a>
                    </c:if>
                </td>



                <td>${t.tourTitle}</td>
                <td>${t.tourPrice}</td>
                <td>${t.tourStartdate}</td>
                <td>${t.tourEnddate}</td>
                <sec:authorize access="hasRole('ROLE_ADMIN')"> 
                    <td>
                        <c:url value ="/admin/tours/${t.tourId}" var="api"/>
                        <a href ="${api}" class = "btn btn-success">Cập nhật</a>
                        
                        <button class = "btn btn-danger" onClick="deleteTour('${api}')">Xóa</button>
                    </td>
                </sec:authorize>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</section>

<script src="<c:url value="resources/js/main.js" />"></script>
<script>
function filterSearchParams() {
    // Lấy danh sách các input trong form
    const formInputs = document.querySelectorAll('form input');

    // Lặp qua các input và gán giá trị rỗng cho thuộc tính name nếu input không có giá trị
    formInputs.forEach(input => {
        if (!input.value) {
            input.name = '';
        }
    });

    // Cho phép form tiếp tục submit
    return true;
}
</script>