package ua.vedroid.dao3.service;

import java.util.List;
import java.util.Optional;
import ua.vedroid.dao3.dao.ManufacturerDao;
import ua.vedroid.dao3.lib.Inject;
import ua.vedroid.dao3.lib.Service;
import ua.vedroid.dao3.model.Manufacturer;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {
    @Inject
    private ManufacturerDao manufacturerDao;

    @Override
    public Manufacturer create(Manufacturer manufacturer) {
        return manufacturerDao.create(manufacturer);
    }

    @Override
    public Manufacturer getById(Long manufacturerId) {
        return manufacturerDao.getById(manufacturerId).orElseThrow();
    }

    @Override
    public List<Manufacturer> getAllManufacturers() {
        return manufacturerDao.getAllManufacturers();
    }

    @Override
    public Manufacturer update(Manufacturer manufacturer) {
        return manufacturerDao.update(manufacturer);
    }

    @Override
    public boolean deleteById(Long manufacturerId) {
        return manufacturerDao.deleteById(manufacturerId);
    }
}
