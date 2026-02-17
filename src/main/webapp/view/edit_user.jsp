<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <title>Редактировать пользователя</title>
</head>
<body>
<jsp:include page="/jspf/header.jsp" />

<h1>Редактировать пользователя</h1>
<a href="${pageContext.request.contextPath}/users">Назад</a>

<form action="${pageContext.request.contextPath}/users" method="post">
    <input type="hidden" name="id" value="${user.id}">

    <label for="firstName">Имя:</label><br>
    <input type="text" id="firstName" name="firstName" value="${user.firstName}" required><br>

    <label for="lastName">Фамилия:</label><br>
    <input type="text" id="lastName" name="lastName" value="${user.lastName}" required><br>

    <label for="phone">Телефон:</label><br>
    <input type="text" id="phone" name="phone" value="${user.phone}"><br>

    <label for="email">Email:</label><br>
    <input type="email" id="email" name="email" value="${user.email}"><br>

    <label for="roleId">Роль:</label><br>
    <select id="roleId" name="roleId" required>
        <c:forEach var="role" items="${roles}">
            <option value="${role.id}" <c:if test="${role.id == user.role.id}">selected</c:if>>
                    ${role.name}
            </option>
        </c:forEach>
    </select><br><br>

    <button type="submit">Сохранить изменения</button>
</form>

<jsp:include page="/jspf/footer.jsp" />
</body>
</html>
