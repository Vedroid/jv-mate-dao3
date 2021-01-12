package ua.vedroid.dao3.db;

import java.util.ArrayList;
import java.util.List;
import ua.vedroid.dao3.model.Car;
import ua.vedroid.dao3.model.Driver;
import ua.vedroid.dao3.model.Manufacturer;

public class Storage {
    public static final List<Car> cars = new ArrayList<>();
    public static final List<Driver> drivers = new ArrayList<>();
    public static final List<Manufacturer> manufacturers = new ArrayList<>();

    private static long carId = 0;
    private static long driverId = 0;
    private static long manufacturerId = 0;

    public static Car addCar(Car car) {
        car.setId(carId++);
        cars.add(car);
        return car;
    }

    public static Driver addDriver(Driver driver) {
        driver.setId(driverId++);
        drivers.add(driver);
        return driver;
    }

    public static Manufacturer addManufacturer(Manufacturer manufacturer) {
        manufacturer.setId(manufacturerId++);
        manufacturers.add(manufacturer);
        return manufacturer;
    }
}
