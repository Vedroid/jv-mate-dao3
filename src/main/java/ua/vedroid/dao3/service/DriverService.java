package ua.vedroid.dao3.service;

import java.util.Optional;
import ua.vedroid.dao3.model.Driver;

public interface DriverService extends GenericService<Driver, Long> {
    Optional<Driver> findByLogin(String login);
}
