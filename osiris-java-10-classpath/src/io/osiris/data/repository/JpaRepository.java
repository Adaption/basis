package io.osiris.data.repository;

import io.osiris.data.common.repository.Repository;
import io.osiris.data.jpa.Entity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface JpaRepository<T extends Entity, R extends Serializable> extends Repository {

    List<T> findAll();

    Optional<T> findById(R[] id);

    void save(T dto);

    void remove(R[] id);
}
