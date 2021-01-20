<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Registration</title>
    </head>
    <body>
        <h1>Hello! Please provide car details</h1>
        <h4 style="color: red">${msg}</h4>
        <form method="post" action="${pageContext.request.contextPath}/cars/create">
            <label for="model">Model:</label>
            <input id="model" type="text" name="model"> <br>

            <label for="manufacturerId">Manufacturer ID:</label>
            <input id="manufacturerId" type="number" name="manufacturerId"> <br>

            <button type="submit">Create</button>
        </form>
    </body>
</html>
