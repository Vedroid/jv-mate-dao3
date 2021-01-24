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
                <th>Drivers
                    <table style="border: 0">
                        <tr>
                            <th style="text-align: center; width: 50%; border: 0">Name</th>
                            <th style="border: 0">|</th>
                            <th style="text-align: center; width: 50%; border: 0">Licence number
                            </th>
                        </tr>
                    </table>
                </th>
            </tr>
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
                    <td style="text-align: center">
                        <c:choose>
                            <c:when test="${car.drivers.size() > 0}">
                                <table style="text-align: center; width: 100%; border: 0">
                                    <c:forEach var="drivers" items="${car.drivers}">
                                        <tr>
                                            <td style="width: 50%; border: 0">
                                                <c:out value="${drivers.name}"/>
                                            </td>
                                            <td style="border: 0">|</td>
                                            <td style="width: 50%; border: 0">
                                                <c:out value="${drivers.licenceNumber}"/>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </c:when>
                            <c:otherwise>
                                No drivers
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}
                        /cars/delete?car_id=${car.id}" style="color: darkred">
                            Delete
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
