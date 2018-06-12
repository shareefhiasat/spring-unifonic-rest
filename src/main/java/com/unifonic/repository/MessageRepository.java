package com.unifonic.repository;

import com.unifonic.model.BaseEntity;
import com.unifonic.model.Message;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.QueryHint;
import java.util.Collection;

/**
 * Repository class for <code>Message</code> domain objects All method names are compliant with Spring Message naming
 * conventions so this interface can easily be extended for Spring Message See here: http://static.springsource.org/spring-data/jpa/docs/current/reference/html/jpa.repositories.html#jpa.query-methods.query-creation
 *
 * @author Shareef Hiasat
 */
public interface MessageRepository {

    /**
     * Retrieve an <code>Message</code> from the data store by id.
     *
     * @param id the id to search for
     * @return the <code>Message</code> if found
     * @throws org.springframework.dao.DataRetrievalFailureException if not found
     */
    @QueryHints({
        @QueryHint(name = "org.hibernate.cacheable", value = "true")})
    Message findById(int id) throws DataAccessException;

    /**
     * Retrieve <code>Message Status</code> from the data store by id.and appSid
     *
     * @param id     the id to search for
     * @param appSid the API id associated with the message
     * @return the <code>Message</code> if found
     * @throws org.springframework.dao.DataRetrievalFailureException if not found
     */
    @QueryHints({
        @QueryHint(name = "org.hibernate.cacheable", value = "true")})
    Message findByIdAndAppSid(int id, String appSid) throws DataAccessException;


    /**
     * Save an <code>Message</code> to the message store, either inserting or updating it.
     *
     * @see BaseEntity#isNew
     * @param message the <code>Message</code> to save
     */
    void save(Message message) throws DataAccessException;

    /**
     * Retrieve <code>Message</code>s from the data store, returning all datas
     *
     * @return a <code>Collection</code> of <code>Message</code>s (or an empty <code>Collection</code> if none
     * found)
     */
    Collection<Message> findAll() throws DataAccessException;

    /**
     * Delete an <code>Message</code> to the message store by <code>Message</code>.
     *
     * @param message the <code>Message</code> to delete
     */
    void delete(Message message) throws DataAccessException;


}
