package com.bezshtanko.university_admission_servlet.dao.interfaces;

import java.util.Set;

public interface Dao<T> {
    void save(T entity);
    T findById(Integer id);
    Set<T> findAll();
    void update(T entity);
    void deleteById(Integer id);
}
