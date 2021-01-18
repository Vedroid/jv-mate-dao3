package ua.vedroid.dao3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import ua.vedroid.dao3.exceptions.DataProcessingException;
import ua.vedroid.dao3.lib.Injector;
import ua.vedroid.dao3.model.Car;
import ua.vedroid.dao3.model.Driver;
import ua.vedroid.dao3.model.Manufacturer;
import ua.vedroid.dao3.service.CarService;
import ua.vedroid.dao3.service.DriverService;
import ua.vedroid.dao3.service.ManufacturerService;
import ua.vedroid.dao3.util.ConnectionUtil;

public class ApplicationStarter {
    private static final Injector injector =
            Injector.getInstance(ApplicationStarter.class.getPackageName());

    private static ManufacturerService manufacturerService;
    private static DriverService driverService;
    private static CarService carService;

    public static void main(String[] args) {
        truncateTable();

        manufacturerService = (ManufacturerService) injector.getInstance(ManufacturerService.class);
        driverService = (DriverService) injector.getInstance(DriverService.class);
        carService = (CarService) injector.getInstance(CarService.class);

        Manufacturer volkswagen = new Manufacturer("Volkswagen", "Germany");
        Manufacturer audi = new Manufacturer("Avde", "Germany");
        Manufacturer tesla = new Manufacturer("Tesla", "USA");

        manufacturerService.create(volkswagen);
        manufacturerService.create(tesla);
        manufacturerService.create(audi);
        manufacturerServiceTest();

        Driver vasya = new Driver("Vasya", "BO123456");
        Driver petya = new Driver("Petya", "TT32534");
        Driver sasha = new Driver("Sasha", "ME46387");
        Driver abdula = new Driver("Abdula", "");

        driverService.create(vasya);
        driverService.create(petya);
        driverService.create(sasha);
        driverService.create(abdula);
        driverServiceTest();

        Car golfVariant = new Car(volkswagen, "Golf Variant");
        Car cybertruck = new Car(tesla, "Cybertruck");
        Car passat = new Car(volkswagen, "Passat");
        Car audi80 = new Car(audi, "80");

        carService.create(golfVariant);
        carService.create(cybertruck);
        carService.create(passat);
        carService.create(audi80);
        carServiceTest();
    }

    private static void manufacturerServiceTest() {
        System.out.println("\n@@@ manufacturerServiceTest @@@");
        printAll("All Manufacturers:", manufacturerService.getAll());

        Manufacturer updatedAudi = manufacturerService.getById(3L);
        updatedAudi.setName("Audi");
        manufacturerService.update(updatedAudi);

        printAll("All manufacturers after update:", manufacturerService.getAll());

        manufacturerService.deleteById(1L);

        System.out.println("Deleted Id '1':\n\t" + manufacturerService.getAll());
    }

    private static void driverServiceTest() {
        System.out.println("\n@@@ driverServiceTest @@@");
        printAll("All drivers:", driverService.getAll());

        Driver driverById = driverService.getById(1L);
        driverById.setLicenceNumber("@newLicenceNumber@");
        driverService.update(driverById);

        printAll("All drivers after update:", driverService.getAll());

        driverService.deleteById(4L);

        printAll("Deleted Id '4':", driverService.getAll());
    }

    private static void carServiceTest() {
        System.out.println("\n@@@ carServiceTest @@@");
        printAll("All cars:", carService.getAll());

        Car carById = carService.getById(1L);
        carById.setModel("@NEW MODEL@");
        carService.update(carById);

        printAll("All cars after update:", carService.getAll());

        Driver newDriver = driverService.getById(1L);
        carById.setDrivers(driverService.getAll());
        carService.update(carById);
        carService.addDriverToCar(newDriver, carService.getById(2L));
        carService.addDriverToCar(newDriver, carService.getById(3L));

        printAll("All cars after add driver:", carService.getAll());

        printAll("All cars by driverId=1:", carService.getAllByDriver(1L));
        printAll("All cars by driverId=2:", carService.getAllByDriver(2L));

        printAll("All cars before remove driver(carId=" + carById.getId()
                + ", driverId=" + newDriver.getId() + "):", carService.getAll());
        carService.removeDriverFromCar(newDriver, carById);
        printAll("All cars after  remove driver:", carService.getAll());

        carService.deleteById(2L);
        printAll("Deleted Id '2':", carService.getAll());
    }

    private static void truncateTable() {
        String query = "TRUNCATE TABLE taxi_service.manufacturers RESTART IDENTITY CASCADE; "
                + "TRUNCATE TABLE taxi_service.cars RESTART IDENTITY CASCADE; "
                + "TRUNCATE TABLE taxi_service.drivers RESTART IDENTITY CASCADE; ";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DataProcessingException("Truncate tables is failed", ex);
        }
    }

    private static void printAll(String msg, List<?> cars) {
        System.out.println(msg);
        cars.forEach(c -> System.out.println("\t" + c));
    }
}
