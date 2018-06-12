package com.unifonic.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.unifonic.rest.JacksonCustomMessageSerializer;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.annotation.Generated;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * Models a message attempt to send
 *
 * @author Shareef Hiasat
 */
@Generated("com.robohorse.robopojogenerator")
@Entity
@Table(name = "message")
@JsonTypeInfo(use = Id.NAME, include = As.EXTERNAL_PROPERTY, property = "data")
@JsonPropertyOrder({"id", "status", "numberOfUnits", "cost", "balance", "recipient", "dataCreated"})
@JsonSerialize(using = JacksonCustomMessageSerializer.class)
public class Message extends MessageBaseEntity implements StatusAble, SendBulk, Serializable {

    private static final long serialVersionUID = -4235680428469660467L;
    /**
     * Store list of bulk messages
     */
    @JsonProperty("Messages")
    @Transient
    public Collection<Message> messages;

    /**
     * This for get message status for dynamically serialize
     * only status field
     */
    @Transient
    @JsonIgnore
    public boolean statusAble = false;

    /**
     * This for marking send bulk for dynamically serialize
     * only send bulk flag field
     */
    @Transient
    @JsonIgnore
    public boolean sendBulk = false;

    /**
     * Message send status,
     * the possible values are
     * "Queued" , "Sent", "Failed" and "Rejected"
     */
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "status")
    private Status statusType;

    /**
     * Message send status,
     * the possible values are
     * "Queued" , "Sent", "Failed" and "Rejected"
     * <p>
     * Used to deserialize the json in response
     */
    @JsonProperty("Status")
    @Transient
    private String status;

    /**
     * SenderID: (optional, string)
     */
    @Column(name = "sender_id")
    @JsonProperty("senderId")
    private String senderId;

    /**
     * Number of unit in a message
     */
    @Column(name = "number_of_units")
    @NotEmpty
    @JsonProperty("NumberOfUnits")
    private String numberOfUnits;

    /**
     * AppSid: (required, string)
     */
    @Column(name = "app_sid")
    @NotEmpty
    @JsonIgnore
    private String appSid;

    /**
     * Date a message was created in,
     * in the following format yyyy-mm-dd hh:mm:ss
     */
    @JsonProperty("DateCreated")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-mm-dd hh:mm:ss")
    @NotNull
    private Date dateCreated;

    /**
     * The mobile number the message was sent to
     * Recipient: (required, integer)
     */
    @Column(name = "recipient")
    @NotNull
    @JsonProperty("Recipient")
    private Integer recipient;

    /**
     * SMS body
     * Body: (required, string)
     *
     * @see <a href="https://stackoverflow.com/questions/6766781/maximum-length-for-mysql-type-text">MySql TEXT length</a>
     * @see <a href="https://stackoverflow.com/questions/3868096/jpa-how-do-i-persist-a-string-into-a-database-field-type-mysql-text">MySql Text in JPA 2.0</a>
     */
    @Column(name = "body", columnDefinition = "TEXT")
    @NotEmpty
    @JsonIgnore
    private String body;

    /**
     * Priority: (optional, string)
     */
    @Column(name = "priority")
    @JsonIgnore
    private String priority;

    /**
     * Price of a message total units
     */
    @Column(name = "cost")
    @JsonProperty("Cost")
    @NotNull
    private double cost;

    /**
     * Current balance of your account
     */
    @Column(name = "balance")
    @NotEmpty
    @JsonIgnore
    private String balance;

    public Message() {
    }

    public Status getStatusType() {
        return statusType;
    }

    public void setStatusType(Status statusType) {
        this.statusType = statusType;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setNumberOfUnits(String numberOfUnits) {
        this.numberOfUnits = numberOfUnits;
    }

    public String getNumberOfUnits() {
        return numberOfUnits;
    }

    public void setAppSid(String appSid) {
        this.appSid = appSid;
    }

    public String getAppSid() {
        return appSid;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setRecipient(Integer recipient) {
        this.recipient = recipient;
    }

    public Integer getRecipient() {
        return recipient;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getPriority() {
        return priority;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getCost() {
        return cost;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getBalance() {
        return balance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Collection<Message> getMessages() {
        return messages;
    }

    public void setMessages(Collection<Message> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return
            " {\"data\": {" +
                "\"status\" : \"" + status + "\"" +
                ",\"senderId\" : \"" + senderId + "\"" +
                ",\"numberOfUnits\" : \"" + numberOfUnits + "\"" +
                ",\"appSid\" : \"" + appSid + "\"" +
                ",\"dateCreated\" : \"" + dateCreated + "\"" +
                ",\"recipient\" : " + recipient +
                ",\"body\" : \"" + body + "\"" +
                ",\"priority\" : \"" + priority + "\"" +
                ",\"cost\" : " + cost +
                ",\"balance\" : \"" + balance + "\"" +
                ",\"id\" : " + id +
                "}}";
    }

    @Override
    public boolean isStatusAble() {
        return statusAble;
    }

    @Override
    public void setStatusAble(boolean statusAble) {
        this.statusAble = statusAble;
    }

    @Override
    public boolean isSendBulk() {
        return sendBulk;
    }

    @Override
    public void setSendBulk(boolean sendBulk) {
        this.sendBulk = sendBulk;
    }


}

/**
 * This for get message status for dynamically serialize
 * only status field
 */
@JsonIgnoreProperties("hidden")
interface StatusAble {
    boolean isStatusAble();

    void setStatusAble(boolean statusAble);
}

/**
 * This for marking send bulk for dynamically serialize
 * only send bulk flag field
 */
@JsonIgnoreProperties("hidden")
interface SendBulk {
    boolean isSendBulk();

    void setSendBulk(boolean isSendBulk);
}
