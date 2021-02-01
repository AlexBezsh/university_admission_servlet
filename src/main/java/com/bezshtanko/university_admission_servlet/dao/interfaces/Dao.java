package com.bezshtanko.university_admission_servlet.dao.interfaces;

import java.util.List;
import java.util.Optional;

public interface Dao<T> extends AutoCloseable {
    void save(T entity);
    Optional<T> findById(Long id);
    List<T> findAll();
    void update(T entity);
    void deleteById(Long id);
    void close();
}
