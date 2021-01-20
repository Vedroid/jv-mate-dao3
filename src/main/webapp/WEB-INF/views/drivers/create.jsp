<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Registration</title>
    </head>
    <body>
        <h1>Hello! Please provide driver details</h1>
        <h4 style="color: red">${msg}</h4>
        <form method="post" action="${pageContext.request.contextPath}/drivers/create">
            <label for="name">Name:</label>
            <input id="name" type="text" name="name"> <br>

            <label for="licenceNumber"> Licence number:</label>
            <input id="licenceNumber" type="text" name="licenceNumber"> <br>

            <button type="submit">Create</button>
        </form>
    </body>
</html>
