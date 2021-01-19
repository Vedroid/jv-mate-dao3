package ua.vedroid.dao3.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T, I> {

    T create(T value);

    Optional<T> getById(I id);

    List<T> getAll();

    T update(T value);

    boolean deleteById(I id);
}
