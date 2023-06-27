<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="./adminHome/scripts/storeDataScripts.js"></script>
<div class="container1">
<select id="store-status">
<option value="" disabled selected>Select Store</option>
<option value="Active">Active</option>
<option value="Inactive">InActive</option>
</select>
<button onclick="getStores()">reset</button>
</div>
<div class="container">
    <h1>Stores List</h1>
    <div class="table-container">
        <table id="storeTable">
            <thead>
                <tr>
                    <th>Store ID</th>
                    <th>Store Name</th>
                    <th>Store Address</th>
                    <th>Store Contact Number</th>
                    <th>Store Status</th>
                </tr>
            </thead>
            <!-- Empty tbody for initial loading -->
            <tbody></tbody>
        </table>
    </div>
</div>






