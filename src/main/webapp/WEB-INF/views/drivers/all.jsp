<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <style>
        table, th, td {
            border: 1px solid black;
        }
    </style>
    <head>
        <title>Drivers</title>
    </head>
    <body>
        <h1>All drivers page</h1>
        <a href="${pageContext.request.contextPath}/">Go to main page</a>
        <table>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Licence number</th>
            </tr>
            <%--@elvariable id="drivers" type="java.util.List"--%>
            <c:forEach var="driver" items="${drivers}">
                <tr>
                    <td>
                        <c:out value="${driver.id}"/>
                    </td>
                    <td>
                        <c:out value="${driver.name}"/>
                    </td>
                    <td>
                        <c:out value="${driver.licenceNumber}"/>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}
                        /drivers/delete?id=${driver.id}" style="color: darkred">
                            Delete
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
