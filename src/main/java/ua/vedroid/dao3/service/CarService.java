package ua.vedroid.dao3.service;

import java.util.List;
import ua.vedroid.dao3.model.Car;
import ua.vedroid.dao3.model.Driver;

public interface CarService extends GenericService<Car, Long> {
    void addDriverToCar(Driver driver, Car car);

    void removeDriverFromCar(Driver driver, Car car);

    List<Car> getAllByDriver(Long driverId);
}
