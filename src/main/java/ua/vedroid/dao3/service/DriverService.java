package ua.vedroid.dao3.service;

import java.util.List;
import ua.vedroid.dao3.model.Driver;

public interface DriverService {
    Driver create(Driver driver);

    Driver getById(Long id);

    List<Driver> getAll();

    Driver update(Driver driver);

    boolean deleteById(Long id);
}
