<jsp:useBean id="msg" scope="request" type="java.lang.String"/>
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
            <input id="name" type="text" name="name" required> <br>

            <label for="licenceNumber"> Licence number:</label>
            <input id="licenceNumber" type="text" name="licenceNumber" required> <br>

            <button type="submit">Create</button>
        </form>
    </body>
</html>
