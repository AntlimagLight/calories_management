<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ru">
<head>
    <title>Users</title>
    <style type="text/css">
        TD, TH {
            padding: 2px;
        }

        TH {
            border: 1px solid black;
        }

        TD {
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
        <td><b>Время</b></td>
        <td><b>Каллории</b></td>
    </tr>
    <c:forEach var="meal" items="${mealList}">
        <tr style="color: <c:if test="${meal.isExcess()}">red</c:if><c:if test="${!meal.isExcess()}">green</c:if>">
            <td>${meal.getDescription()}</td>
            <td>${meal.getDateTime().toLocalTime()}</td>
            <td>${meal.getCalories()}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>