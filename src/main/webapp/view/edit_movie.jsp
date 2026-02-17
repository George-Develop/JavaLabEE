<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <title>Редактировать роль</title>
</head>
<body>
<jsp:include page="/jspf/header.jsp" />

<h1>Редактировать фильм</h1>
<a href="${pageContext.request.contextPath}/movies">Назад</a>

<form action="${pageContext.request.contextPath}/movies" method="post">
    <input type="hidden" name="id" value="${movie.id}">
    <label>Название:</label><br>
    <input type="text" name="title" value="${movie.title}" required><br>
    <label>Жанр:</label><br>
    <input type="text" name="genre" value="${movie.genre}" required><br>
    <label>Год выпуска:</label><br>
    <input type="number" name="releaseYear" value="${movie.releaseYear}" required><br>
    <label>Описание:</label><br>
    <textarea name="description" rows="3">${movie.description}</textarea><br><br>
    <button type="submit">Сохранить</button>
</form>

<jsp:include page="/jspf/footer.jsp" />
</body>
</html>
