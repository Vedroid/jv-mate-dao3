package ua.vedroid.dao3.controllers.manufacturer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.vedroid.dao3.ApplicationStarter;
import ua.vedroid.dao3.lib.Injector;
import ua.vedroid.dao3.service.ManufacturerService;

public class DeleteManufacturerController extends HttpServlet {
    private static final Injector injector =
            Injector.getInstance(ApplicationStarter.class.getPackageName());
    private ManufacturerService service =
            (ManufacturerService) injector.getInstance(ManufacturerService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long manufacturerId = Long.valueOf(req.getParameter("id"));
        service.deleteById(manufacturerId);
        resp.sendRedirect(req.getContextPath() + "/manufacturers/all");
    }
}
