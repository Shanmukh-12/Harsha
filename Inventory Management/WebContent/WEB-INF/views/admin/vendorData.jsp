<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <style>
        body {
            background-color: #fff;
        }

        .center {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
    </style>

    <div class="container center">
        <h1>Vendor List</h1>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Vendor ID</th>
                    <th>Vendor Name</th>
                    <th>Vendor Address</th>
                    <th>Vendor Phone Number</th>
                    <th>Vendor Status</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="vendor" items="${vendors}">
                    <tr>
                        <td>${vendor.vendorId}</td>
                        <td>${vendor.vendorName}</td>
                        <td>${vendor.vendorAddress}</td>
                        <td>${vendor.vendorPhone}</td>
                        <td>${vendor.status}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

