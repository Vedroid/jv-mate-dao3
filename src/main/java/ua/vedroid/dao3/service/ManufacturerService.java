package ua.vedroid.dao3.service;

import java.util.List;
import ua.vedroid.dao3.model.Manufacturer;

public interface ManufacturerService {
    Manufacturer create(Manufacturer manufacturer);

    Manufacturer getById(Long manufacturerId);

    List<Manufacturer> getAll();

    Manufacturer update(Manufacturer manufacturer);

    boolean deleteById(Long manufacturerId);
}
