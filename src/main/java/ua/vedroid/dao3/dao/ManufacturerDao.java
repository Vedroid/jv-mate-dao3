package ua.vedroid.dao3.dao;

import java.util.List;
import java.util.Optional;
import ua.vedroid.dao3.model.Manufacturer;

public interface ManufacturerDao {
    Manufacturer create(Manufacturer manufacturer);

    Optional<Manufacturer> getById(Long manufacturerId);

    List<Manufacturer> getAll();

    Manufacturer update(Manufacturer manufacturer);

    boolean deleteById(Long manufacturerId);
}
