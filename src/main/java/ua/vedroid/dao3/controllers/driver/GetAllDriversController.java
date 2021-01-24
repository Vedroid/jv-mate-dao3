package ua.vedroid.dao3.controllers.driver;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.vedroid.dao3.lib.Injector;
import ua.vedroid.dao3.service.DriverService;

public class GetAllDriversController extends HttpServlet {
    private static final Injector injector =
            Injector.getInstance("ua.vedroid.dao3");
    private DriverService service =
            (DriverService) injector.getInstance(DriverService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("drivers", service.getAll());
        req.getRequestDispatcher("/WEB-INF/views/drivers/all.jsp").forward(req, resp);
    }
}
