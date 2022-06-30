package com.andersen.repository;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<E, K> {
    void delete(E entity);

    void deleteById(K id);

    boolean existsById(K id);

    List<E> findAll();

    Optional<E> findById(K id);

    E getById(K id);

    E save(E entity);
}