package com.unifonic.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * "Queued" other possiblities are , "Sent", "Failed" and "Rejected"
 * @author Shareef Hiasat
 */
@Entity
@Table(name = "status")
@JsonPropertyOrder({"id", "name"})
public class Status extends NamedEntity {
}
