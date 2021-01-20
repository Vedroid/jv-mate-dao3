<jsp:useBean id="msg" scope="request" type="java.lang.String"/>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>Add Drivers to Car</title>
    </head>
    <body>
        <a href="${pageContext.request.contextPath}/">Go to main page</a>
        <h4 style="color: red">${msg}</h4>
        <form method="post" action="${pageContext.request.contextPath}/cars/drivers/add">
            <label for="carId">Car ID:</label>
            <input id="carId" type="number" name="carId" required min="1"><br>
            <label for="driverId">Driver ID:</label>
            <input id="driverId" type="number" name="driverId" required min="1"><br>
            <button type="submit">Create</button>
        </form>
    </body>
</html>
