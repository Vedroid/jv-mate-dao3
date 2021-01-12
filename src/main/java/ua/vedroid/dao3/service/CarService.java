package ua.vedroid.dao3.service;

import java.util.List;
import ua.vedroid.dao3.model.Car;
import ua.vedroid.dao3.model.Driver;

public interface CarService {
    Car create(Car car);

    Car getById(Long id);

    List<Car> getAll();

    Car update(Car car);

    boolean deleteById(Long id);

    void addDriverToCar(Driver driver, Car car);

    void removeDriverFromCar(Driver driver, Car car);

    List<Car> getAllByDriver(Long driverId);
}
