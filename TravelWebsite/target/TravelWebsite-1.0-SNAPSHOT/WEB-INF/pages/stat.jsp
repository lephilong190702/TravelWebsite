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
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>

<!DOCTYPE html>

<h1 class="text-center text-info">THỐNG KÊ</h1>

<div class="container mt-5">
    <div class="row">
        <div class="col-md-12">
            <div class="input-group mb-3">
                <select id="yearSelect" class="form-select">
                    <option value="2021">2021</option>
                    <option value="2022">2022</option>
                    <option value="2023">2023</option>
                    <option value="2024">2024</option>
                </select>
                <button onclick="updateRevenueChart()" class="btn btn-primary ml-2">Thống kê doanh thu tháng</button>
                <button onclick="updateRevenueQuaterChart()" class="btn btn-primary ml-2">Thống kê doanh thu quý</button>
                <button onclick="updateRevenueYearChart()" class="btn btn-primary ml-2">Thống kê doanh thu năm</button>
            </div>
        </div>
    </div>
    <div id="chart_revenue"></div>
</div>

<div class="container mt-5">
    <div class="row">
    <div class="col-md-12">
        <div class="input-group mb-3">
            <select id="yearSelectTour" class="form-select">
                <option value="2021">2021</option>
                <option value="2022">2022</option>
                <option value="2023">2023</option>
                <option value="2024">2024</option>
            </select>
            <button onclick="updateTourChart()" class="btn btn-primary ml-2">Thống kê theo tháng</button>
            <button onclick="updateTourQuarterChart()" class="btn btn-primary ml-2">Thống kê theo quý</button>
            <button onclick="updateTourYearChart()" class="btn btn-primary ml-2">Thống kê theo năm</button>
        </div>
    </div>
    </div>
    <div id="chart_tour"></div>
</div>


<script type="text/javascript">
    google.charts.load('current', {'packages': ['corechart']});
    google.charts.setOnLoadCallback(function () {
        loadRevenueData('2021');
        loadTourCountData('2021');
    });

    function loadRevenueData(selectedYear) {
        console.log(selectedYear);
        fetch(`/TravelWebsite/admin/revenue/?year=\${selectedYear}`)
                .then(response => response.json())
                .then(data => {
                    drawRevenueChart(data);
                    console.log(data);
                })
                .catch(error => {
                    console.error('Lỗi khi tải dữ liệu doanh thu:', error);
                });
    }

    function loadRevenueQuarterData(selectedYear) {
        fetch(`/TravelWebsite/admin/revenue/?year=\${selectedYear}`)
                .then(response => response.json())
                .then(data => {
                    drawRevenueQuarterChart(data);
                    console.log(data);
                })
                .catch(error => {
                    console.error('Lỗi khi tải dữ liệu thống kê theo quý:', error);
                });
    }

    function loadRevenueYearData(selectedYear) {
        fetch(`/TravelWebsite/admin/revenue/?year.isEmpty()`)
                .then(response => response.json())
                .then(data => {
                    drawRevenueYearChart(data);
                    console.log(data);
                })
                .catch(error => {
                    console.error('Lỗi khi tải dữ liệu thống kê theo năm:', error);
                });
    }

    function loadTourCountData(selectedYearTour) {
        fetch(`/TravelWebsite/admin/count/?years=\${selectedYearTour}`)
                .then(response => response.json())
                .then(data => {
                    drawTourChart(data);
                    console.log(data);
                })
                .catch(error => {
                    console.error('Lỗi khi tải dữ liệu số lượng tour:', error);
                });
    }

    function loadTourQuarterData(selectedYearTour) {
        fetch(`/TravelWebsite/admin/count/?years=\${selectedYearTour}`)
                .then(response => response.json())
                .then(data => {
                    drawTourQuarterChart(data);
                    console.log(data);
                })
                .catch(error => {
                    console.error('Lỗi khi tải dữ liệu số lượng tour theo quý:', error);
                });
    }

    function loadTourYearData() {
        // Gọi API để lấy dữ liệu số lượng tour của mỗi năm
        fetch(`/TravelWebsite/admin/count/?years.isEmpty()`)
                .then(response => response.json())
                .then(data => {
                    drawTourYearChart(data);
                    console.log(data);
                })
                .catch(error => {
                    console.error('Lỗi khi tải dữ liệu số lượng tour theo năm:', error);
                });
    }

    function drawRevenueChart(data) {
        var dataTable = new google.visualization.DataTable();
        dataTable.addColumn('number', 'Tháng');
        dataTable.addColumn('number', 'Doanh thu');

        // Thêm dữ liệu từ data vào DataTable
        for (var i = 0; i < data.length; i++) {
            var month = data[i][0];
            var revenue = data[i][3];
            dataTable.addRow([month, revenue]);
        }

        var options = {
            title: 'Biểu đồ thống kê doanh thu tháng',
            width: '1100',
            height: '500',
            hAxis: {title: 'Tháng'},
            vAxis: {title: 'Doanh thu'},
            bars: 'vertical'
        };

        var chart = new google.visualization.ColumnChart(document.getElementById('chart_revenue'));
        chart.draw(dataTable, options);
    }

    function drawRevenueQuarterChart(data) {
        var dataTable = new google.visualization.DataTable();
        dataTable.addColumn('string', 'Quý');
        dataTable.addColumn('number', 'Doanh thu');


        var quarterRevenue = {
            'Quý 1': 0,
            'Quý 2': 0,
            'Quý 3': 0,
            'Quý 4': 0
        };


        for (var i = 0; i < data.length; i++) {
            var quarter = data[i][1];
            var revenue = data[i][3];

            switch (quarter) {
                case 1:
                    quarterRevenue['Quý 1'] += revenue;
                    break;
                case 2:
                    quarterRevenue['Quý 2'] += revenue;
                    break;
                case 3:
                    quarterRevenue['Quý 3'] += revenue;
                    break;
                case 4:
                    quarterRevenue['Quý 4'] += revenue;
                    break;
            }
        }

        // Thêm dữ liệu đã tính vào DataTable
        for (var q in quarterRevenue) {
            dataTable.addRow([q, quarterRevenue[q]]);
        }

        var options = {
            title: 'Biểu đồ thống kê doanh thu theo quý',
            width: '1100',
            height: '500',
            hAxis: {title: 'Quý'},
            vAxis: {title: 'Doanh thu'},
            bars: 'vertical'
        };

        var chart = new google.visualization.ColumnChart(document.getElementById('chart_revenue'));
        chart.draw(dataTable, options);
    }

    function drawRevenueYearChart(data) {
        var dataTable = new google.visualization.DataTable();
        dataTable.addColumn('number', 'Năm');
        dataTable.addColumn('number', 'Doanh thu');
        var yearRevenueMap = {};
        for (var i = 0; i < data.length; i++) {
            var year = data[i][2];
            var revenue = data[i][3];

            if (yearRevenueMap[year]) {
                yearRevenueMap[year] += revenue;
            } else {
                yearRevenueMap[year] = revenue;
            }
        }

        for (var year in yearRevenueMap) {
            dataTable.addRow([parseInt(year), yearRevenueMap[year]]);
        }


        var options = {
            title: 'Biểu đồ thống kê doanh thu theo năm',
            width: '1100',
            height: '500',
            hAxis: {title: 'Năm', ticks: [2021, 2022, 2023, 2024],  format: '####'},
            vAxis: {title: 'Doanh thu'},
            bars: 'vertical'
        };

        var chart = new google.visualization.ColumnChart(document.getElementById('chart_revenue'));
        chart.draw(dataTable, options);
    }

    function drawTourChart(data) {
        var dataTable = new google.visualization.DataTable();
        dataTable.addColumn('number', 'Tháng');
        dataTable.addColumn('number', 'Số lượng');

        // Thêm dữ liệu từ data vào DataTable
        for (var i = 0; i < data.length; i++) {
            var month = data[i][2];
            var quantity = data[i][5];
            dataTable.addRow([month, quantity]);
        }

        var options = {
            title: 'Biểu đồ thống kê số lượng tour theo tháng',
            width: '1100',
            height: '500',
            hAxis: {title: 'Tháng', ticks: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]},
            vAxis: {title: 'Số lượng'},
            bars: 'vertical'
        };

        var chart = new google.visualization.ColumnChart(document.getElementById('chart_tour'));
        chart.draw(dataTable, options);
    }

    function drawTourQuarterChart(data) {
        var dataTable = new google.visualization.DataTable();
        dataTable.addColumn('string', 'Quý');
        dataTable.addColumn('number', 'Số lượng');


        var quarterCounts = {
            'Quý 1': 0,
            'Quý 2': 0,
            'Quý 3': 0,
            'Quý 4': 0
        };


        for (var i = 0; i < data.length; i++) {
            var quarter = data[i][3];
            var quantity = data[i][5];

            switch (quarter) {
                case 1:
                    quarterCounts['Quý 1'] += quantity;
                    break;
                case 2:
                    quarterCounts['Quý 2'] += quantity;
                    break;
                case 3:
                    quarterCounts['Quý 3'] += quantity;
                    break;
                case 4:
                    quarterCounts['Quý 4'] += quantity;
                    break;
            }
        }

        // Thêm dữ liệu đã tính vào DataTable
        for (var q in quarterCounts) {
            dataTable.addRow([q, quarterCounts[q]]);
        }

        var options = {
            title: 'Biểu đồ thống kê số lượng tour theo quý',
            width: '1100',
            height: '500',
            hAxis: {title: 'Quý'},
            vAxis: {title: 'Số lượng'},
            bars: 'vertical'
        };

        var chart = new google.visualization.ColumnChart(document.getElementById('chart_tour'));
        chart.draw(dataTable, options);
    }

    function drawTourYearChart(data) {
        var dataTable = new google.visualization.DataTable();
        dataTable.addColumn('number', 'Năm');
        dataTable.addColumn('number', 'Số lượng');

        var yearQuantityMap = {}; // Sử dụng một đối tượng để lưu số lượng tour của từng năm

        // Tính số lượng tour của từng năm
        for (var i = 0; i < data.length; i++) {
            var year = data[i][4];
            var quantity = data[i][5];

            if (yearQuantityMap[year]) {
                yearQuantityMap[year] += quantity;
            } else {
                yearQuantityMap[year] = quantity;
            }
        }

        // Thêm dữ liệu từ đối tượng vào DataTable
        for (var year in yearQuantityMap) {
            dataTable.addRow([parseInt(year), yearQuantityMap[year]]);
        }

        var options = {
            title: 'Biểu đồ thống kê số lượng tour theo năm',
            width: '1100',
            height: '500',
            hAxis: {title: 'Năm', ticks: [2021, 2022, 2023, 2024], format: '####'},
            vAxis: {title: 'Số lượng'},
            bars: 'vertical'
        };

        var chart = new google.visualization.ColumnChart(document.getElementById('chart_tour'));
        chart.draw(dataTable, options);
    }

    function updateRevenueChart() {
        var selectedYear = document.getElementById('yearSelect').value;
        loadRevenueData(selectedYear);
    }

    function updateRevenueQuaterChart() {
        var selectedYear = document.getElementById('yearSelect').value;
        loadRevenueQuarterData(selectedYear);
    }

    function updateRevenueYearChart() {
        loadRevenueYearData();
    }

    function updateTourChart() {
        var selectedYearTour = document.getElementById('yearSelectTour').value;
        loadTourCountData(selectedYearTour);
    }

    function updateTourQuarterChart() {
        var selectedYearTour = document.getElementById('yearSelectTour').value;
        loadTourQuarterData(selectedYearTour);
    }

    function updateTourYearChart() {
        loadTourYearData();
    }

</script>