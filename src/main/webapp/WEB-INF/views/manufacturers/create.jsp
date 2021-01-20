<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Registration</title>
    </head>
    <body>
        <h1>Hello! Please provide manufacturer details</h1>
        <h4 style="color: red">${msg}</h4>
        <form method="post" action="${pageContext.request.contextPath}/manufacturers/create">

            <label for="name">Name:</label>
            <input id="name" type="text" name="name"> <br>

            <label for="country">Country:</label>
            <input id="country" type="text" name="country"> <br>

            <button type="submit">Create</button>
        </form>
    </body>
</html>
