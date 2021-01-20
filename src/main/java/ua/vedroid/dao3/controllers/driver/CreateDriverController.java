package ua.vedroid.dao3.controllers.driver;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.vedroid.dao3.ApplicationStarter;
import ua.vedroid.dao3.lib.Injector;
import ua.vedroid.dao3.model.Driver;
import ua.vedroid.dao3.service.DriverService;

public class CreateDriverController extends HttpServlet {
    private static final String CREATE_JSP = "/WEB-INF/views/drivers/create.jsp";
    private static final Injector injector =
            Injector.getInstance(ApplicationStarter.class.getPackageName());
    private DriverService service =
            (DriverService) injector.getInstance(DriverService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher(CREATE_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        String licenceNumber = req.getParameter("licenceNumber");
        service.create(new Driver(name, licenceNumber));
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
