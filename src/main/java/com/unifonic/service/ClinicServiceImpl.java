package com.unifonic.service;

import com.unifonic.model.*;
import com.unifonic.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

import static com.unifonic.util.ConfigConstants.QUEUED_STATUS_DESC;

/**
 * Mostly used as a facade for all Unifonic controllers
 * Also a placeholder for @Transactional and @Cacheable annotations
 *
 * @author Shareef Hiasat
 */
@Service

public class ClinicServiceImpl implements ClinicService {

    private MessageRepository messageRepository;
    private StatusRepository statusRepository;

    @Autowired
    public ClinicServiceImpl(
        MessageRepository messageRepository,
        StatusRepository statusRepository) {
        this.messageRepository = messageRepository;
        this.statusRepository = statusRepository;
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value="UnifonicCache",key="#messageId",unless="#result==null")
    public Message findMessageById(int messageId) {
        Message message = null;
        try {
            message = messageRepository.findById(messageId);
        } catch (ObjectRetrievalFailureException | EmptyResultDataAccessException e) {
            // just ignore not found exceptions for Jdbc/Jpa realization
            return null;
        }
        return message;
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value="UnifonicCache",key="#appSid",unless="#result==null")
    public Message findByIdAndAppSid(int id, String appSid) {
        Message message = null;
        try {
            message = messageRepository.findByIdAndAppSid(id, appSid);
        } catch (ObjectRetrievalFailureException | EmptyResultDataAccessException e) {
            // just ignore not found exceptions for Jdbc/Jpa realization
            return null;
        }
        if(message != null)
            message.setStatusAble(true);
        if(message.getStatus() == null || message.getStatus().isEmpty()) {//work around for JDBC profile bug with status and many to one
            message.setStatus(QUEUED_STATUS_DESC);
            Status queued = new Status();
            queued.setId(1);
            queued.setName("Queued");
            message.setStatusType(queued);
        }
        return message;
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Message> findAllMessages() throws DataAccessException {
        return messageRepository.findAll();
    }

    @Override
    public void saveMessage(Message message) throws DataAccessException {
        messageRepository.save(message);
    }

    @Override

    public void deleteMessage(Message message) throws DataAccessException {
        messageRepository.delete(message);
    }

    @Override
    @Transactional(readOnly = true)
//    @Cacheable(value="UnifonicCache",key="#statusId",unless="#result==null")
    public Status findStatusById(int statusId) {
        Status status = null;
        try {
            status = statusRepository.findById(statusId);
        } catch (ObjectRetrievalFailureException | EmptyResultDataAccessException e) {
            // just ignore not found exceptions for Jdbc/Jpa realization
            return null;
        }
        return status;
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Status> findAllStatus() throws DataAccessException {
        return statusRepository.findAll();
    }

    @Override
    public void saveStatus(Status status) throws DataAccessException {
        statusRepository.save(status);
    }

    @Override
    public void deleteStatus(Status status) throws DataAccessException {
        statusRepository.delete(status);
    }

}
