package ua.vedroid.dao3.controllers.car;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.vedroid.dao3.ApplicationStarter;
import ua.vedroid.dao3.lib.Injector;
import ua.vedroid.dao3.model.Car;
import ua.vedroid.dao3.model.Driver;
import ua.vedroid.dao3.service.CarService;
import ua.vedroid.dao3.service.DriverService;

public class AddDriversController extends HttpServlet {
    private static final Injector injector =
            Injector.getInstance(ApplicationStarter.class.getPackageName());
    private static final String ADD_DRIVER_JSP = "/WEB-INF/views/cars/addDrivers.jsp";
    private DriverService driverService =
            (DriverService) injector.getInstance(DriverService.class);
    private CarService carService =
            (CarService) injector.getInstance(CarService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher(ADD_DRIVER_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Long driverId = Long.valueOf(req.getParameter("driverId"));
        Long carId = Long.valueOf(req.getParameter("carId"));
        Car car = carService.getById(carId);
        Driver driver = driverService.getById(driverId);
        carService.addDriverToCar(driver, car);
        resp.sendRedirect(req.getContextPath() + "/cars");
    }
}
