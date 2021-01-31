package com.bezshtanko.university_admission_servlet.dao.interfaces;

import java.util.List;

public interface Dao<T> extends AutoCloseable {
    void save(T entity);
    T findById(Integer id);
    List<T> findAll();
    void update(T entity);
    void deleteById(Integer id);
    void close();
}
