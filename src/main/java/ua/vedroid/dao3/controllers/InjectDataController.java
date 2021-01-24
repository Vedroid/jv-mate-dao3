package ua.vedroid.dao3.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ua.vedroid.dao3.lib.Injector;
import ua.vedroid.dao3.model.Car;
import ua.vedroid.dao3.model.Driver;
import ua.vedroid.dao3.model.Manufacturer;
import ua.vedroid.dao3.service.CarService;
import ua.vedroid.dao3.service.DriverService;
import ua.vedroid.dao3.service.ManufacturerService;

public class InjectDataController extends HttpServlet {
    private static final Injector injector =
            Injector.getInstance("ua.vedroid.dao3");
    private ManufacturerService manufacturerService =
            (ManufacturerService) injector.getInstance(ManufacturerService.class);
    private DriverService driverService =
            (DriverService) injector.getInstance(DriverService.class);
    private CarService carService =
            (CarService) injector.getInstance(CarService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Manufacturer audi = manufacturerService
                .create(new Manufacturer("Audi", "DE"));
        Manufacturer volkswagen = manufacturerService
                .create(new Manufacturer("Volkswagen", "DE"));
        Manufacturer tesla = manufacturerService
                .create(new Manufacturer("Tesla", "USA"));
        Manufacturer toyota = manufacturerService
                .create(new Manufacturer("Toyota", "JP"));

        Driver bob = driverService.create(new Driver("Bob", "BO123456"));
        Driver alice = driverService.create(new Driver("Alice", "TT32534"));
        Driver john = driverService.create(new Driver("John", "ME46387"));
        Driver bruce = driverService.create(new Driver("Bruce", "TE540653"));

        Car golfVariant = carService.create(new Car(volkswagen, "Golf Variant"));
        Car cybertruck = carService.create(new Car(tesla, "Cybertruck"));
        Car passat = carService.create(new Car(volkswagen, "Passat"));
        Car camry = carService.create(new Car(toyota, "Camry"));
        Car audi80 = carService.create(new Car(audi, "80"));

        carService.addDriverToCar(bob, golfVariant);
        carService.addDriverToCar(bob, cybertruck);
        carService.addDriverToCar(alice, golfVariant);
        carService.addDriverToCar(john, cybertruck);
        carService.addDriverToCar(john, passat);
        carService.addDriverToCar(bruce, camry);

        req.setAttribute("msg", "Your mock data was added to DB!");
        req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
    }
}
