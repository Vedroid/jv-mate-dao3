<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Login</title>
    </head>
    <body>
        <h1>Login page</h1>
        <h4 style="color: red">${msg}</h4>
        <a href="${pageContext.request.contextPath}/drivers/create">Registration</a>
        <br>
        <form action="${pageContext.request.contextPath}/login" method="post">
            <label>
                Login:
                <input type="text" name="login" required>
            </label>
            <br>
            <label>
                Password:
                <input type="password" name="pwd" required>
            </label>
            <br>
            <button type="submit">Login</button>
        </form>
    </body>
</html>
