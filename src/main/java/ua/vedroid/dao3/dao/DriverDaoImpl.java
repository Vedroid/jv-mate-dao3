package ua.vedroid.dao3.dao;

import java.util.List;
import java.util.Optional;
import ua.vedroid.dao3.db.Storage;
import ua.vedroid.dao3.lib.Dao;
import ua.vedroid.dao3.model.Driver;

@Dao
public class DriverDaoImpl implements DriverDao {
    @Override
    public Driver create(Driver driver) {
        return Storage.addDriver(driver);
    }

    @Override
    public Optional<Driver> getById(Long driverId) {
        return Storage.drivers.stream()
                .filter(driver -> driver.getId().equals(driverId))
                .findFirst();
    }

    @Override
    public List<Driver> getAll() {
        return Storage.drivers;
    }

    @Override
    public Driver update(Driver driver) {
        int index = Storage.drivers.indexOf(getById(driver.getId()).get());
        return Storage.drivers.set(index, driver);
    }

    @Override
    public boolean deleteById(Long driverId) {
        return Storage.drivers.removeIf(driver -> driver.getId().equals(driverId));
    }
}
