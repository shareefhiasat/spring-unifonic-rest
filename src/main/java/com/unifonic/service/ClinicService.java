package com.unifonic.service;

import com.unifonic.model.Message;
import com.unifonic.model.Status;
import org.springframework.dao.DataAccessException;

import java.util.Collection;


/**
 * Mostly used as a facade so all controllers have a single point of entry
 *
 * @author Shareef Hiasat
 */
public interface ClinicService {

    Message findMessageById(int id);

    Message findByIdAndAppSid(int id, String appSid);

    Collection<Message> findAllMessages() throws DataAccessException;

    void saveMessage(Message message) throws DataAccessException;

    void deleteMessage(Message message) throws DataAccessException;

    Status findStatusById(int statusId);

    Collection<Status> findAllStatus() throws DataAccessException;

    void saveStatus(Status status) throws DataAccessException;

    void deleteStatus(Status status) throws DataAccessException;

}
