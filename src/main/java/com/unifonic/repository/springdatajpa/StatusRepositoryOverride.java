package com.unifonic.repository.springdatajpa;

import com.unifonic.model.Status;
import org.springframework.context.annotation.Profile;

/**
 * @author Shareef Hiasat
 */

@Profile("spring-data-jpa")
public interface StatusRepositoryOverride {

    public void delete(Status status);

}
