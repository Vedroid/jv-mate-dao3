package ua.vedroid.dao3.controllers.manufacturer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.vedroid.dao3.ApplicationStarter;
import ua.vedroid.dao3.lib.Injector;
import ua.vedroid.dao3.model.Manufacturer;
import ua.vedroid.dao3.service.ManufacturerService;

public class CreateManufacturerController extends HttpServlet {
    private static final String REGISTRATION_JSP = "/WEB-INF/views/manufacturers/create.jsp";
    private static final Injector injector =
            Injector.getInstance(ApplicationStarter.class.getPackageName());
    private ManufacturerService service =
            (ManufacturerService) injector.getInstance(ManufacturerService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher(REGISTRATION_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String name = req.getParameter("name");
        String country = req.getParameter("country");
        service.create(new Manufacturer(name, country));
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
