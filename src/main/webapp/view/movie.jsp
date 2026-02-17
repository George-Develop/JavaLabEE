<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <title>Список фильмов</title>
</head>
<body>
<jsp:include page="/jspf/header.jsp" />
<h1>Список фильмов</h1>
<a href="/JavaLabEE">Назад</a>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Название</th>
        <th>Жанр</th>
        <th>Год выпуска</th>
        <th>Описание</th>
        <th>Добавил</th>
        <th>Действия</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="movie" items="${movies}">
        <tr>
            <td>${movie.id}</td>
            <td>${movie.title}</td>
            <td>${movie.genre}</td>
            <td>${movie.releaseYear}</td>
            <td>${movie.description}</td>
            <td>${movie.addedBy.firstName} ${movie.addedBy.lastName}</td>
            <td>
                <a href="${pageContext.request.contextPath}/movies?action=edit&id=${movie.id}">Редактировать</a>
                |
                <a href="${pageContext.request.contextPath}/movies?action=delete&id=${movie.id}"
                   onclick="return confirm('Вы уверены, что хотите удалить фильм?');">Удалить</a>
            </td>
        </tr>
    </c:forEach>

    </tbody>

</table>
<h2>Добавить новый фильм</h2>
<form action="${pageContext.request.contextPath}/movies" method="post">
    <label for="title">Название:</label><br>
    <input type="text" id="title" name="title" required><br>

    <label for="genre">Жанр:</label><br>
    <input type="text" id="genre" name="genre" required><br>

    <label for="releaseYear">Год выпуска:</label><br>
    <input type="number" id="releaseYear" name="releaseYear" required><br>

    <label for="description">Описание:</label><br>
    <textarea id="description" name="description" rows="3"></textarea><br><br>

    <button type="submit">Добавить фильм</button>
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
