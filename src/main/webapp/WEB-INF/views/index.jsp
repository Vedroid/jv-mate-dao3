<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <h1>Hello world!</h1>
        <a href="${pageContext.request.contextPath}/inject">Inject test data into DB</a>
        <h4 style="color: dodgerblue">${msg}</h4>
        <table>
            <tr>
                <td>
                    <form method="get"
                          action="${pageContext.request.contextPath}/cars/my">
                        <button type="submit">My cars</button>
                    </form>
                </td>
            </tr>
            <tr>
                <td>
                    <form method="get"
                          action="${pageContext.request.contextPath}/manufacturers/create">
                        <button type="submit">Create manufacturer</button>
                    </form>
                </td>
                <td>
                    <form method="get"
                          action="${pageContext.request.contextPath}/manufacturers">
                        <button type="submit">All manufacturers</button>
                    </form>
                </td>
            </tr>
            <tr>
                <td>
                    <form method="get" action="${pageContext.request.contextPath}/cars/create">
                        <button type="submit">Create car</button>
                    </form>
                </td>
                <td>
                    <form method="get" action="${pageContext.request.contextPath}/cars">
                        <button type="submit">All cars</button>
                    </form>
                </td>
            </tr>
            <tr>
                <td>
                    <form method="get" action="${pageContext.request.contextPath}/drivers/create">
                        <button type="submit">Create driver</button>
                    </form>
                </td>
                <td>
                    <form method="get" action="${pageContext.request.contextPath}/drivers">
                        <button type="submit">All drivers</button>
                    </form>
                </td>
            </tr>
            <tr>
                <td>
                    <form method="get" action="${pageContext.request.contextPath}/cars/drivers/add">
                        <button type="submit">Add drivers to car</button>
                    </form>
                </td>
            </tr>
        </table>
    </body>
</html>
