<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ru">
<head>
    <title>Users</title>
    <style type="text/css">
        td, th {
            padding: 2px;
        }

        th {
            border: 1px solid black;
        }

        td {
            border-bottom: 1px solid black;
        }
    </style>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<table>
    <tr>
        <td><b>Наименование</b></td>
        <td><b>Дата/Время</b></td>
        <td><b>Каллории</b></td>
    </tr>
    <c:forEach var="meal" items="${mealList}">
        <tr style="color: ${meal.isExcess()? 'red': 'green'}">
            <td>${meal.description}</td>
            <td>${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}</td>
            <td>${meal.calories}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>