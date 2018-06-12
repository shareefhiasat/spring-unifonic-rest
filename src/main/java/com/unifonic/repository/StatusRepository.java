package com.unifonic.repository;

import com.unifonic.model.Status;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;

import java.util.Collection;

/**
 * @author Shareef Hiasat
 */

public interface StatusRepository {

    @Cacheable("statusById")
    Status findById(int id) throws DataAccessException;

    @Cacheable("allStatuses")
    Collection<Status> findAll() throws DataAccessException;

    void save(Status status) throws DataAccessException;

    void delete(Status stat) throws DataAccessException;

}
