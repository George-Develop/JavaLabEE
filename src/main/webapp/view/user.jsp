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
<h2>Добавить нового пользователя</h2>
<form action="${pageContext.request.contextPath}/users" method="post">
    <label for="firstName">Имя:</label><br>
    <input type="text" id="firstName" name="firstName" required><br>

    <label for="lastName">Фамилия:</label><br>
    <input type="text" id="lastName" name="lastName" required><br>

    <label for="phone">Телефон:</label><br>
    <input type="text" id="phone" name="phone"><br>

    <label for="email">Email:</label><br>
    <input type="email" id="email" name="email"><br>

    <label for="roleId">Роль:</label><br>
    <select id="roleId" name="roleId" required>
        <c:forEach var="role" items="${roles}">
            <option value="${role.id}">${role.name}</option>
        </c:forEach>
    </select><br><br>

    <button type="submit">Добавить пользователя</button>
</form>
<hr>
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
