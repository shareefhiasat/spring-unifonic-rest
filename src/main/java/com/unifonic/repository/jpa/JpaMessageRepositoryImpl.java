package com.unifonic.repository.jpa;

import com.unifonic.model.Message;
import com.unifonic.repository.MessageRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

/**
 * @author Shareef Hiasat
 */

@Repository
@Profile("jpa")
public class JpaMessageRepositoryImpl implements MessageRepository {


    @PersistenceContext
    private EntityManager em;

    @Override
    public Message findByIdAndAppSid(int id, String appSid) throws DataAccessException {
        Message message = (Message) this.em.createQuery("SELECT s FROM Message s WHERE id = " + id + " AND app_sid = " + appSid).getSingleResult();
        if(message != null)
            message.setStatusAble(true);
        return message;
    }

    @Override
    public Message findById(int id) {
        return this.em.find(Message.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Collection<Message> findAll() throws DataAccessException {
        return this.em.createQuery("SELECT s FROM Message s").getResultList();
    }

    @Override
    public void save(Message message) throws DataAccessException {
        if (message.getId() == null) {
            this.em.persist(message);
        } else {
            this.em.merge(message);
        }
    }

    @Override
    public void delete(Message message) throws DataAccessException {
        this.em.remove(this.em.contains(message) ? message : this.em.merge(message));
        String id = message.getId().toString();
        this.em.createNativeQuery("DELETE FROM Message WHERE id=" + id).executeUpdate();
    }

}
