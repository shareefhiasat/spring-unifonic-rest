package com.unifonic.rest;

import com.unifonic.model.Message;
import com.unifonic.model.Status;
import com.unifonic.service.ClinicService;
import com.unifonic.util.UnifonicUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.*;

import static com.unifonic.util.ConfigConstants.QUEUED_STATUS_ID;
import static com.unifonic.util.UnifonicUtil.*;

/**
 * @author Shareef Hiasat
 */
@Api(description = "Message Rest Controller CRUD")
@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("api/messages")
public class MessageRestController {

    @Autowired
    private ClinicService clinicService;

    /**
     * Send Message to save it after success
     *
     * @param appSid
     * @param body
     * @param priority
     * @param recipient
     * @param senderId
     * @return
     */
    @RequestMapping(value = "/send",
        method = RequestMethod.POST,
        consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<Message> sendMessage(
//        BindingResult bindingResult,
        @RequestParam(value = "appSid", required = true) String appSid,
        @RequestParam(value = "recipient", required = true) Integer recipient,
        @RequestParam(value = "body", required = true) String body,
        @RequestParam(value = "senderId", required = false) String senderId,
        @RequestParam(value = "priority", required = false) String priority
    ) {
        Message message = new Message();
        //Force using auto increament only
        message.setId(null);
        //Required parameters saved
        message.setAppSid(appSid);
        message.setBody(body);
        message.setRecipient(recipient);
        //Optional parameters saved
        message.setPriority(getDefaultPriority());
        message.setSenderId(getDefaultSenderId(senderId));

        int numberOfUnits = getCalculatedNumberOfUntis(body);
        message.setBalance(getDefaultBalance());
        message.setCost(getCalculatedCost(numberOfUnits));
        message.setDateCreated(new Date());
        message.setNumberOfUnits(String.valueOf(numberOfUnits));

        Status status = this.clinicService.findStatusById(QUEUED_STATUS_ID);
        if (status == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        message.setStatusType(status);

        this.clinicService.saveMessage(message);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    /**
     * Send Message to save it after success
     *
     * @param appSid
     * @param body
     * @param timeScheduled in formate yyyy-mm-dd HH:mm:s
     * @param recipients    comma separated numbers to send
     * @param senderId
     * @return
     */
    @RequestMapping(value = "/SendBulk",
        method = RequestMethod.POST,
        consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<Message> sendBulkMessage(
//        BindingResult bindingResult,
        @RequestParam(value = "appSid", required = true) String appSid,
        @RequestParam(value = "recipient", required = true) String recipients,
        @RequestParam(value = "body", required = true) String body,
        @RequestParam(value = "senderId", required = false) String senderId,
        @RequestParam(value = "timeScheduled", required = false) String timeScheduled
    ) {
        //get a status desc to send in json response readable
        Status status = this.clinicService.findStatusById(QUEUED_STATUS_ID);
        if (status == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<String> recipientNumbers = Arrays.asList(recipients.split(","));
        List<Message> bulkMessages = new ArrayList<>(recipientNumbers.size());
        Message data = new Message();

        int totalCost = 0;
        int numberOfUnits = getCalculatedNumberOfUntis(body);
        int cost = getCalculatedCost(numberOfUnits);

        for (String number : recipientNumbers) {
            Message message = new Message();
            message.setSendBulk(true);
            //Force using auto increament only
            message.setId(null);
            //Required parameters saved
            message.setAppSid(appSid);
            message.setBody(body);
            message.setRecipient(UnifonicUtil.parsePrimitiveInt(number));
            //Optional parameters saved
            message.setPriority(getDefaultPriority());
            message.setSenderId(getDefaultSenderId(senderId));

            totalCost += cost;

            message.setBalance(getDefaultBalance());
            message.setCost(cost);
            message.setDateCreated(new Date());
            message.setNumberOfUnits(String.valueOf(numberOfUnits));

            message.setStatusType(status);

            this.clinicService.saveMessage(message);
            bulkMessages.add(message);
        }

        data.setSendBulk(true);
        //Required parameters saved
        data.setAppSid(appSid);
        data.setBody(body);
        data.setRecipient(1);//dummy default @TODO test if its not provided

        //Optional parameters saved
        data.setPriority(getDefaultPriority());
        data.setSenderId(getDefaultSenderId(senderId));
        data.setBalance(getDefaultBalance());
        data.setCost(totalCost);
        data.setDateCreated(new Date());
        data.setNumberOfUnits(String.valueOf(bulkMessages.size()));

        data.setMessages(bulkMessages);

        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }

    /**
     * Query to get all messages
     * For Experimental API Dose not have delet
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Collection<Message>> getAllMessages() {
        Collection<Message> messages = new ArrayList<>();
        messages.addAll(this.clinicService.findAllMessages());
        if (messages.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    /**
     * Query Message By Message Id
     * For Experimental API Dose not have delet
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Message> getMessage(@PathVariable("id") int id) {
        Message message = this.clinicService.findMessageById(id);
        if (message == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    /**
     * Query Message STATUS By Message Id
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/GetMessageIDStatus", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Message> getMessageIDStatus(
        @RequestParam(value = "appSid", required = true) String appSid,
        @RequestParam(value = "id", required = true) int id
    ) {
        Message message = this.clinicService.findByIdAndAppSid(id, appSid);
        if (message == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    /**
     * For Experimental API Dose not have delete
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Transactional
    public ResponseEntity<Void> deleteMessage(@PathVariable("id") int id) {
        Message message = this.clinicService.findMessageById(id);
        if (message == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.clinicService.deleteMessage(message);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
