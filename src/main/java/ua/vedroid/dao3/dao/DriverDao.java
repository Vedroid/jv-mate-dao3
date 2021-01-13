package ua.vedroid.dao3.dao;

import java.util.List;
import java.util.Optional;
import ua.vedroid.dao3.model.Driver;

public interface DriverDao {
    Driver create(Driver driver);

    Optional<Driver> getById(Long driverId);

    List<Driver> getAll();

    Driver update(Driver driver);

    boolean deleteById(Long driverId);
}
