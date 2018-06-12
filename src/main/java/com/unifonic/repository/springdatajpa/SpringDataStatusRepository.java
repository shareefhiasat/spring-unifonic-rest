package com.unifonic.repository.springdatajpa;

import com.unifonic.model.Status;
import com.unifonic.repository.StatusRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.Repository;

/**
 * Spring Message JPA specialization of the {@link com.unifonic.repository.MessageRepository} interface
 *
 * @author Shareef Hiasat
 */

@Profile("spring-data-jpa")
public interface SpringDataStatusRepository extends StatusRepository, Repository<Status, Integer>, StatusRepositoryOverride {

}
