package com.unifonic.repository.springdatajpa;

import com.unifonic.model.Message;
import org.springframework.context.annotation.Profile;

/**
 * @author Shareef Hiasat
 */

@Profile("spring-data-jpa")
public interface MessageRepositoryOverride {

    public void delete(Message message);

}
