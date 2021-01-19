package ua.vedroid.dao3.service;

import java.util.List;

public interface GenericService<T, I> {
    T create(T value);

    T getById(I id);

    List<T> getAll();

    T update(T value);

    boolean deleteById(I id);
}
