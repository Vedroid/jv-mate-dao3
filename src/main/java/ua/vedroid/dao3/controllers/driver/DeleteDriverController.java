package ua.vedroid.dao3.controllers.driver;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.vedroid.dao3.ApplicationStarter;
import ua.vedroid.dao3.lib.Injector;
import ua.vedroid.dao3.service.DriverService;

public class DeleteDriverController extends HttpServlet {
    private static final Injector injector =
            Injector.getInstance(ApplicationStarter.class.getPackageName());
    private DriverService service =
            (DriverService) injector.getInstance(DriverService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        service.deleteById(Long.valueOf(req.getParameter("driver_id")));
        resp.sendRedirect(req.getContextPath() + "/drivers");
    }
}
