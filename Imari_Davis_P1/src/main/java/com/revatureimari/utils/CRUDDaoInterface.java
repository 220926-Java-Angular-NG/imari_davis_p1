package com.revatureimari.utils;

import java.util.List;

public interface CRUDDaoInterface<T> {
    int create(T t);

    List<T> getAll();

    T getById(int id);

    T update(T t);

    boolean delete(T t);

    boolean deleteById(int id);
}
