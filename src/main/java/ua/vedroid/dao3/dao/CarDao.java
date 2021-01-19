package ua.vedroid.dao3.dao;

import java.util.List;
import ua.vedroid.dao3.model.Car;

public interface CarDao extends GenericDao<Car, Long> {
    List<Car> getAllByDriver(Long driverId);
}
