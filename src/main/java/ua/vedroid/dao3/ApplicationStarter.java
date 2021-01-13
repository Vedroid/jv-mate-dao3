package ua.vedroid.dao3;

import java.util.List;
import ua.vedroid.dao3.lib.Injector;
import ua.vedroid.dao3.model.Car;
import ua.vedroid.dao3.model.Driver;
import ua.vedroid.dao3.model.Manufacturer;
import ua.vedroid.dao3.service.CarService;
import ua.vedroid.dao3.service.DriverService;
import ua.vedroid.dao3.service.ManufacturerService;

public class ApplicationStarter {
    private static final Injector injector =
            Injector.getInstance(ApplicationStarter.class.getPackageName());

    private static ManufacturerService manufacturerService;
    private static DriverService driverService;
    private static CarService carService;

    public static void main(String[] args) {
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
        System.out.println("All Manufacturers:\n\t" + manufacturerService.getAll());

        Manufacturer updatedAudi = manufacturerService.getById(2L);
        updatedAudi.setName("Audi");
        manufacturerService.update(updatedAudi);

        System.out.println("Update 'Avde' -> 'Audi':\n\t" + manufacturerService.getAll());

        manufacturerService.deleteById(0L);

        System.out.println("Deleted Id '0':\n\t" + manufacturerService.getAll());
    }

    private static void driverServiceTest() {
        System.out.println("\n@@@ driverServiceTest @@@");
        System.out.println("All drivers:\n\t" + driverService.getAll());

        Driver driverById = driverService.getById(1L);
        driverById.setLicenceNumber("@newLicenceNumber@");
        driverService.update(driverById);

        System.out.println("All drivers after update:\n\t" + driverService.getAll());

        driverService.deleteById(3L);

        System.out.println("Deleted Id '3':\n\t" + driverService.getAll());
    }

    private static void carServiceTest() {
        System.out.println("\n@@@ carServiceTest @@@");
        System.out.println("All cars:\n\t" + carService.getAll());

        Car carById = carService.getById(1L);
        carById.setDrivers(driverService.getAll());
        carService.update(carById);

        System.out.println("All cars after update:\n\t" + carService.getAll());

        Driver newDriver = driverService.getById(1L);
        carService.addDriverToCar(newDriver, carService.getById(1L));
        carService.addDriverToCar(newDriver, carService.getById(2L));

        System.out.println("All cars after add driver:\n\t" + carService.getAll());

        List<Car> allByDriver = carService.getAllByDriver(newDriver.getId());

        System.out.println("All cars by driver:\n\t" + allByDriver);

        System.out.println("All cars before remove driver:\n\t" + carService.getAll());
        carService.removeDriverFromCar(newDriver, carById);
        System.out.println("All cars after  remove driver:\n\t" + carService.getAll());

        carService.deleteById(0L);

        System.out.println("Deleted Id '0':\n\t" + carService.getAll());
    }
}
