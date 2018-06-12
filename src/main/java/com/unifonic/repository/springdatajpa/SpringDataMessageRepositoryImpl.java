package com.unifonic.repository.springdatajpa;

import com.unifonic.model.Message;
import org.springframework.context.annotation.Profile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Shareef Hiasat
 */

@Profile("spring-data-jpa")
public class SpringDataMessageRepositoryImpl implements MessageRepositoryOverride {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void delete(Message message) {
        String id = message.getId().toString();
        this.em.createNativeQuery("DELETE FROM Message WHERE id=" + id).executeUpdate();
    }

}
