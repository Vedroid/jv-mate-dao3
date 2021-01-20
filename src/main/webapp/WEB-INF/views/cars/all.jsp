<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <style>
        table, th, td {
            border: 1px solid black;
        }
    </style>
    <head>
        <title>Cars</title>
    </head>
    <body>
        <h1>All cars page</h1>
        <a href="${pageContext.request.contextPath}/">Go to main page</a>
        <table>
            <tr>
                <th>ID</th>
                <th>Model</th>
                <th>Manufacturer</th>
                <th>Drivers</th>
            </tr>
            <%--@elvariable id="cars" type="java.util.List"--%>
            <c:forEach var="car" items="${cars}">
                <tr>
                    <td>
                        <c:out value="${car.id}"/>
                    </td>
                    <td>
                        <c:out value="${car.model}"/>
                    </td>
                    <td>
                        <c:out value="${car.manufacturer.name}"/>
                    </td>
                    <td>
                        <c:forEach var="drivers" items="${car.drivers}">
                            <c:out value="${drivers.name}"/>
                        </c:forEach>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}
                        /cars/delete?id=${car.id}" style="color: darkred">
                            Delete
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
