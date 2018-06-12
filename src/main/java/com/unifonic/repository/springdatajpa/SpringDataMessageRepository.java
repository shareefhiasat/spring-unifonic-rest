package com.unifonic.repository.springdatajpa;

import com.unifonic.model.Message;
import com.unifonic.repository.MessageRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.Repository;


/**
 * @author Shareef Hiasat
 */

@Profile("spring-data-jpa")
public interface SpringDataMessageRepository extends MessageRepository, Repository<Message, Integer>, MessageRepositoryOverride {

}
