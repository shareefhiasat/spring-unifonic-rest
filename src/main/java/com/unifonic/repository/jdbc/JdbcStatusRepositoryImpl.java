package com.unifonic.repository.jdbc;

import com.unifonic.model.Status;
import com.unifonic.repository.StatusRepository;
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

/**
 * @author Shareef Hiasat
 */

@Repository
@Profile("jdbc")
public class JdbcStatusRepositoryImpl implements StatusRepository {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert insertStatus;

    @Autowired
    public JdbcStatusRepositoryImpl(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.insertStatus = new SimpleJdbcInsert(dataSource)
            .withTableName("status")
            .usingGeneratedKeyColumns("id");
    }

    @Override
    public Status findById(int id) {
        Status status;
        try {
            Map<String, String> params = new HashMap<>();
            params.put("id", String.valueOf(id));
            status = this.namedParameterJdbcTemplate.queryForObject(
                "SELECT id, name FROM status WHERE id= :id",
                params,
                BeanPropertyRowMapper.newInstance(Status.class));
        } catch (EmptyResultDataAccessException ex) {
            throw new ObjectRetrievalFailureException(Status.class, id);
        }
        return status;
    }

    @Override
    public Collection<Status> findAll() throws DataAccessException {
        Map<String, String> params = new HashMap<>();
        return this.namedParameterJdbcTemplate.query(
            "SELECT id, name FROM status",
            params,
            BeanPropertyRowMapper.newInstance(Status.class));
    }

    @Override
    public void save(Status status) throws DataAccessException {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(status);
        if (status.isNew()) {
            Number newKey = this.insertStatus.executeAndReturnKey(parameterSource);
            status.setId(newKey.intValue());
        } else {
            this.namedParameterJdbcTemplate.update("UPDATE status SET name=:name WHERE id=:id",
                parameterSource);
        }
    }

    @Override
    public void delete(Status status) throws DataAccessException {
        Map<String, Object> status_params = new HashMap<>();
        status_params.put("id", status.getId());
        this.namedParameterJdbcTemplate.update("DELETE FROM status WHERE id=:id", status_params);
    }

}
