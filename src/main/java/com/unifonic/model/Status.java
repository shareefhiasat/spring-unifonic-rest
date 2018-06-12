package com.unifonic.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * "Queued" other possiblities are , "Sent", "Failed" and "Rejected"
 * @author Shareef Hiasat
 */
@Entity
@Table(name = "status")
@JsonPropertyOrder({"id", "name"})
public class Status extends NamedEntity implements Serializable {
    private static final long serialVersionUID = -4235680428469550467L;
}
