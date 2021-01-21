package ua.vedroid.dao3.controllers.car;

import java.io.IOException;
import java.util.NoSuchElementException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.vedroid.dao3.ApplicationStarter;
import ua.vedroid.dao3.lib.Injector;
import ua.vedroid.dao3.model.Car;
import ua.vedroid.dao3.model.Manufacturer;
import ua.vedroid.dao3.service.CarService;
import ua.vedroid.dao3.service.ManufacturerService;

public class CreateCarController extends HttpServlet {
    private static final String CREATE_JSP = "/WEB-INF/views/cars/create.jsp";
    private static final Injector injector =
            Injector.getInstance(ApplicationStarter.class.getPackageName());
    private ManufacturerService manufacturerService =
            (ManufacturerService) injector.getInstance(ManufacturerService.class);
    private CarService carService =
            (CarService) injector.getInstance(CarService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher(CREATE_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String model = req.getParameter("car_model");
        String manufacturerId = req.getParameter("car_manufacturerId");
        try {
            Manufacturer manufacturer =
                    manufacturerService.getById(Long.valueOf(manufacturerId));
            carService.create(new Car(manufacturer, model));
            resp.sendRedirect(req.getContextPath() + "/");
        } catch (NoSuchElementException e) {
            req.setAttribute("msg",
                    "Manufacturer with id=" + manufacturerId + " not found!");
            req.getRequestDispatcher(CREATE_JSP).forward(req, resp);
        }
    }
}
