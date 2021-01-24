<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>Registration</title>
    </head>
    <body>
        <h1>Hello! Please provide driver details</h1>
        <h4 style="color: red">${msg}</h4>
        <form method="post" action="${pageContext.request.contextPath}/drivers/create">
            <label for="name">Name:</label>
            <input id="name" type="text" name="driver_name" required> <br>

            <label for="licenceNumber"> Licence number:</label>
            <input id="licenceNumber" type="text" name="driver_licenceNumber" required> <br>

            <button type="submit">Create</button>
        </form>
    </body>
</html>
