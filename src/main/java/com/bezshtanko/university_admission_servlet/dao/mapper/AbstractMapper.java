package com.bezshtanko.university_admission_servlet.dao.mapper;

import com.bezshtanko.university_admission_servlet.model.AbstractEntity;

import java.util.Map;

public abstract class AbstractMapper<T extends AbstractEntity> implements Mapper<T> {

    public T makeUnique(Map<Long, T> cache, T object) {
        cache.putIfAbsent(object.getId(), object);
        return cache.get(object.getId());
    }

}
