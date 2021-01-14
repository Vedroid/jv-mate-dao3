package ua.vedroid.dao3.dao.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import ua.vedroid.dao3.dao.CarDao;
import ua.vedroid.dao3.db.Storage;
import ua.vedroid.dao3.lib.Dao;
import ua.vedroid.dao3.model.Car;

@Dao
public class CarDaoImpl implements CarDao {
    @Override
    public Car create(Car car) {
        return Storage.addCar(car);
    }

    @Override
    public Optional<Car> getById(Long carId) {
        return Storage.cars.stream()
                .filter(car -> car.getId().equals(carId))
                .findFirst();
    }

    @Override
    public List<Car> getAll() {
        return Storage.cars;
    }

    @Override
    public List<Car> getAllByDriver(Long driverId) {
        return Storage.cars.stream()
                .filter(car -> car.getDrivers().stream()
                        .anyMatch(driver -> driver.getId().equals(driverId)))
                .collect(Collectors.toList());
    }

    @Override
    public Car update(Car car) {
        int index = Storage.cars.indexOf(getById(car.getId()).get());
        return Storage.cars.set(index, car);
    }

    @Override
    public boolean deleteById(Long carId) {
        return Storage.cars.removeIf(car -> car.getId().equals(carId));
    }
}
