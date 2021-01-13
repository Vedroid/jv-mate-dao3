package ua.vedroid.dao3.dao;

import java.util.List;
import java.util.Optional;
import ua.vedroid.dao3.model.Car;

public interface CarDao {
    Car create(Car car);

    Optional<Car> getById(Long carId);

    List<Car> getAll();

    List<Car> getAllByDriver(Long driverId);

    Car update(Car car);

    boolean deleteById(Long carId);
}
