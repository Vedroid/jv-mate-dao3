package ua.vedroid.dao3.dao;

import java.util.Optional;
import ua.vedroid.dao3.model.Driver;

public interface DriverDao extends GenericDao<Driver, Long> {
    Optional<Driver> findByLogin(String login);
}
