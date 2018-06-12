package com.unifonic.repository.jdbc;

import com.unifonic.model.Message;
import com.unifonic.model.Status;
import com.unifonic.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static com.unifonic.util.ConfigConstants.QUEUED_STATUS_DESC;

/**
 * @author Shareef Hiasat
 */

@Repository
@Profile("jdbc")
public class JdbcMessageRepositoryImpl implements MessageRepository {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert insertData;

    @Autowired
    public JdbcMessageRepositoryImpl(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.insertData = new SimpleJdbcInsert(dataSource)
            .withTableName("message")
            .usingGeneratedKeyColumns("id");
    }

    /**
     * find message by message_id
     *
     * @param id
     * @return
     */
    @Override
    public Message findById(int id) {
        Message message;
        try {
            Map<String, String> params = new HashMap<>();
            params.put("id", String.valueOf(id));
            message = this.namedParameterJdbcTemplate.queryForObject(
                "SELECT app_sid, balance, body, cost, date_created, id, number_of_units, priority, recipient, sender_id, status FROM message  WHERE id = :id",
                params,
                BeanPropertyRowMapper.newInstance(Message.class));
        } catch (EmptyResultDataAccessException ex) {
            throw new ObjectRetrievalFailureException(Message.class, id);
        }
        return message;
    }

    @Override
    public Message findByIdAndAppSid(int id, String appSid) throws DataAccessException {
        Message message;
        try {
            Map<String, String> params = new HashMap<>();
            params.put("id", String.valueOf(id));
            params.put("appSid", appSid);
            message = this.namedParameterJdbcTemplate.queryForObject(
                "SELECT app_sid, balance, body, cost, date_created, id, number_of_units, priority, recipient, sender_id, status FROM message WHERE id = :id AND app_sid = :appSid",
                params,
                BeanPropertyRowMapper.newInstance(Message.class));
        } catch (EmptyResultDataAccessException ex) {
            throw new ObjectRetrievalFailureException(Message.class, id);
        }
        if (message != null)
            message.setStatusAble(true);
        if (message.getStatus() == null || message.getStatus().isEmpty()) {//work around for JDBC profile bug with status and many to one
            message.setStatus(QUEUED_STATUS_DESC);
            Status queued = new Status();
            queued.setId(1);
            queued.setName("Queued");
            message.setStatusType(queued);
        }
        return message;
    }

    /**
     * return all messages
     *
     * @return
     * @throws DataAccessException
     */
    @Override
    public Collection<Message> findAll() throws DataAccessException {
        Map<String, String> params = new HashMap<>();
        return this.namedParameterJdbcTemplate.query(
            "SELECT app_sid, balance, body, cost, date_created, id, number_of_units, priority, recipient, sender_id, status FROM message",
            params,
            BeanPropertyRowMapper.newInstance(Message.class));
    }

    @Override
    public void save(Message message) throws DataAccessException {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(message);
        if (message.isNew()) {
            Number newKey = this.insertData.executeAndReturnKey(parameterSource);
            message.setId(newKey.intValue());
        } else {
            this.namedParameterJdbcTemplate.update(
                "UPDATE message SET  app_sid = :appSid, balance = :balance, body = :body, cost = :cost, date_created = :dateCreated, number_of_units = :numberOfUnits, priority = :priority, recipient = :recipient, sender_id = :senderId, status = :status WHERE id = :id",
                parameterSource);
        }

    }

    @Override
    public void delete(Message message) throws DataAccessException {
        Map<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(message.getId()));
        this.namedParameterJdbcTemplate.update("DELETE FROM message WHERE id=:id", params);
    }

}
