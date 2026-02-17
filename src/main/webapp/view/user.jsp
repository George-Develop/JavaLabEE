<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="/css/style.css">
    <title>Список пользователей</title>
</head>
<body>
<jsp:include page="/jspf/header.jsp" />
<h1>Список пользователей</h1>
<a href="/JavaLabEE">Назад</a>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Имя</th>
        <th>Фамилия</th>
        <th>Телефон</th>
        <th>Email</th>
        <th>ID роли</th>
        <th>Роль</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.id}</td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.phone}</td>
            <td>${user.email}</td>
            <td>${user.roleId}</td>
            <td>${user.role.name}</td>  <%-- Предполагается, что у Role есть поле name --%>
        </tr>
    </c:forEach>
    </tbody>
</table>
<jsp:include page="/jspf/footer.jsp" />
</body>
<style>
    table {
        width: 100%;
        border-collapse: collapse;
    }
    th, td {
        border: 1px solid black;
        padding: 8px;
        text-align: left;
    }
    th {
        background-color: #f2f2f2;
    }
</style>
</html>
