package ua.vedroid.dao3.controllers.car;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.vedroid.dao3.ApplicationStarter;
import ua.vedroid.dao3.lib.Injector;
import ua.vedroid.dao3.service.CarService;

public class GetAllCarsController extends HttpServlet {
    private static final Injector injector =
            Injector.getInstance(ApplicationStarter.class.getPackageName());
    private CarService service =
            (CarService) injector.getInstance(CarService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("cars", service.getAll());
        req.getRequestDispatcher("/WEB-INF/views/cars/all.jsp").forward(req, resp);
    }
}
