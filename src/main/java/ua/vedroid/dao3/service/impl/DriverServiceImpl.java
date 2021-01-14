package ua.vedroid.dao3.service.impl;

import java.util.List;
import ua.vedroid.dao3.dao.DriverDao;
import ua.vedroid.dao3.lib.Inject;
import ua.vedroid.dao3.lib.Service;
import ua.vedroid.dao3.model.Driver;
import ua.vedroid.dao3.service.DriverService;

@Service
public class DriverServiceImpl implements DriverService {
    @Inject
    private DriverDao driverDao;

    @Override
    public Driver create(Driver driver) {
        return driverDao.create(driver);
    }

    @Override
    public Driver getById(Long id) {
        return driverDao.getById(id).get();
    }

    @Override
    public List<Driver> getAll() {
        return driverDao.getAll();
    }

    @Override
    public Driver update(Driver driver) {
        return driverDao.update(driver);
    }

    @Override
    public boolean deleteById(Long id) {
        return driverDao.deleteById(id);
    }
}
