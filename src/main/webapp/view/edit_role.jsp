<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <title>Редактировать роль</title>
</head>
<body>
<jsp:include page="/jspf/header.jsp" />

<h1>Редактировать роль</h1>
<a href="${pageContext.request.contextPath}/roles">Назад</a>

<form action="${pageContext.request.contextPath}/roles" method="post">
    <input type="hidden" name="id" value="${role.id}">

    <label for="name">Название роли:</label><br>
    <input type="text" id="name" name="name" value="${role.name}" required><br>

    <label for="canEditMovies">Может редактировать фильмы:</label>
    <input type="checkbox" id="canEditMovies" name="canEditMovies"
           <c:if test="${role.canEditMovies}"></c:if>><br><br>

    <button type="submit">Сохранить изменения</button>
</form>

<jsp:include page="/jspf/footer.jsp" />
</body>
</html>
