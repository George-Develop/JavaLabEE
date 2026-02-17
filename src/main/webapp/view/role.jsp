<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <title>Список ролей</title>
</head>
<body>
<jsp:include page="/jspf/header.jsp" />
<h1>Список ролей</h1>
<a href="/JavaLabEE">Назад</a>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Название</th>
        <th>Может редактировать фильмы</th>
        <th>Действия</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="role" items="${roles}">
        <tr>
            <td>${role.id}</td>
            <td>${role.name}</td>
            <td>${role.canEditMovies ? "Да" : "Нет"}</td>
            <td>
                <a href="${pageContext.request.contextPath}/roles?action=edit&id=${role.id}">Редактировать</a> |
                <a href="${pageContext.request.contextPath}/roles?action=delete&id=${role.id}"
                   onclick="return confirm('Вы уверены, что хотите удалить роль?');">Удалить</a>
            </td>
        </tr>
    </c:forEach>

    </tbody>
</table>
<h2>Добавить новую роль</h2>
<form action="${pageContext.request.contextPath}/roles" method="post">
    <label for="name">Название роли:</label><br>
    <input type="text" id="name" name="name" required><br>

    <label for="canEditMovies">Может редактировать фильмы:</label>
    <input type="checkbox" id="canEditMovies" name="canEditMovies"><br><br>

    <button type="submit">Добавить роль</button>
</form>
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
        background-color: #4547e6;
    }
</style>
</html>
