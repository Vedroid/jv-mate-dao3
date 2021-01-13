package ua.vedroid.dao3.service;

import java.util.List;
import ua.vedroid.dao3.dao.CarDao;
import ua.vedroid.dao3.lib.Inject;
import ua.vedroid.dao3.lib.Service;
import ua.vedroid.dao3.model.Car;
import ua.vedroid.dao3.model.Driver;

@Service
public class CarServiceImpl implements CarService {
    @Inject
    private CarDao carDao;

    @Override
    public Car create(Car car) {
        return carDao.create(car);
    }

    @Override
    public Car getById(Long id) {
        return carDao.getById(id).get();
    }

    @Override
    public List<Car> getAll() {
        return carDao.getAll();
    }

    @Override
    public Car update(Car car) {
        return carDao.update(car);
    }

    @Override
    public boolean deleteById(Long id) {
        return carDao.deleteById(id);
    }

    @Override
    public void addDriverToCar(Driver driver, Car car) {
        car.getDrivers().add(driver);
        update(car);
    }

    @Override
    public void removeDriverFromCar(Driver driver, Car car) {
        car.getDrivers().remove(driver);
        update(car);
    }

    @Override
    public List<Car> getAllByDriver(Long driverId) {
        return carDao.getAllByDriver(driverId);
    }
}
