package com.unifonic.rest;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.unifonic.model.Message;
import com.unifonic.util.ConfigConstants;

import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;

import static com.unifonic.util.ConfigConstants.QUEUED_STATUS_DESC;

/**
 * @author Shareef Hiasat
 */

public class JacksonCustomMessageSerializer extends StdSerializer<Message> {

    public JacksonCustomMessageSerializer() {
        this(null);
    }

    protected JacksonCustomMessageSerializer(Class<Message> t) {
        super(t);
    }

    /**
     * for adding root name with custom serialization
     *
     * @param message
     * @param gen
     * @param provider
     * @throws IOException
     * @see <a href="https://unifonic.docs.apiary.io/#reference/messages/send">Send message API Docs</a>
     */
    @Override
    public void serializeWithType(Message message, JsonGenerator gen,
                                  SerializerProvider provider, TypeSerializer typeSer)
        throws IOException {

        typeSer.writeTypePrefixForObject(message, gen, Message.class);
        serialize(message, gen, provider); // call your customized serialize method
        typeSer.writeTypeSuffixForObject(message, gen);
    }

    /**
     * ordered and formatted as the link in docs
     * Check conditional json when this is used with statusAble flag
     * {@link com.unifonic.rest.MessageRestController}
     *
     * @param message
     * @param jgen
     * @param provider
     * @throws IOException
     * @see <a href="https://unifonic.docs.apiary.io/#reference/messages/send">Send message API Docs</a>
     */
    @Override
    public void serialize(
        Message message,
        JsonGenerator jgen,
        SerializerProvider provider) throws IOException {

        Format formatter = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");

        if (message.isStatusAble()) {//for get message status

            jgen.writeObjectFieldStart("data");//naming root json message
            jgen.writeStringField("Status", QUEUED_STATUS_DESC);
            jgen.writeEndObject();// message status

            return;
        } else if (message.isSendBulk()) {//for bulk send

            jgen.writeObjectFieldStart("data");//naming root json message
            jgen.writeArrayFieldStart("Messages");

            for (Message msgDTO : message.getMessages()) {
                jgen.writeStartObject(); // message entry
                if (msgDTO.getId() == null) {
                    jgen.writeNullField("MessageID");
                } else {
                    jgen.writeNumberField("MessageID", msgDTO.getId());
                }
                jgen.writeNumberField("Recipient", msgDTO.getRecipient());
                jgen.writeStringField("Status", QUEUED_STATUS_DESC);
                jgen.writeEndObject(); // message
            }

            jgen.writeEndArray();

            //ordered as API
            jgen.writeStringField("NumberOfUnits", message.getNumberOfUnits());
            jgen.writeNumberField("Cost", message.getCost());
            jgen.writeStringField("Balance", message.getBalance());
            jgen.writeStringField("TimeCreated", formatter.format(message.getDateCreated()));
            jgen.writeStringField("CurrencyCode", ConfigConstants.DEFAULT_CURRENCY);

            jgen.writeEndObject(); // message

            return;
        }
        //default behaviour
        jgen.writeObjectFieldStart("data");//naming root json message
        if (message.getId() == null) {
            jgen.writeNullField("MessageID");
        } else {
            jgen.writeNumberField("MessageID", message.getId());
        }

        //ordered as API
        jgen.writeStringField("Status", QUEUED_STATUS_DESC);
        jgen.writeStringField("NumberOfUnits", message.getNumberOfUnits());
        jgen.writeNumberField("Cost", message.getCost());
        jgen.writeStringField("Balance", message.getBalance());
        jgen.writeNumberField("Recipient", message.getRecipient());
        jgen.writeStringField("DataCreated", formatter.format(message.getDateCreated()));

        jgen.writeEndObject(); // message
    }

}
