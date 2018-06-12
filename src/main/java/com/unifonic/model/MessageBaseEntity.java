package com.unifonic.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Simple JavaBean domain object with an id property. Used as a base class for objects needing this property.
 *
 * @author Shareef Hiasat
 */
@MappedSuperclass
public class MessageBaseEntity implements Serializable{

    private static final long serialVersionUID = -4232280428469660467L;

    /**
     * A unique ID that identifies a message
     */
    @Id
    @JsonProperty("MessageID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonIgnore
    public boolean isNew() {
        return this.id == null;
    }

    @Override
    public String toString() {
        return "MessageBaseEntity{" +
            "id=" + id +
            '}';
    }
}
