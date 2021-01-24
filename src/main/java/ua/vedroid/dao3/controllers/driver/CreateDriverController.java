package ua.vedroid.dao3.controllers.driver;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.vedroid.dao3.lib.Injector;
import ua.vedroid.dao3.model.Driver;
import ua.vedroid.dao3.service.DriverService;

public class CreateDriverController extends HttpServlet {
    private static final String CREATE_JSP = "/WEB-INF/views/drivers/create.jsp";
    private static final Injector injector =
            Injector.getInstance("ua.vedroid.dao3");
    private DriverService service =
            (DriverService) injector.getInstance(DriverService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher(CREATE_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String login = req.getParameter("driver_login");
        String pwd = req.getParameter("driver_pwd");
        String name = req.getParameter("driver_name");
        String licenceNumber = req.getParameter("driver_licenceNumber");
        service.create(new Driver(login, pwd, name, licenceNumber));
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
