package com.unifonic.rest;

import com.unifonic.model.Status;
import com.unifonic.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;

/**
 * For query status its extra not in API
 */
@RestController
@CrossOrigin(exposedHeaders = "errors, content-type")
@RequestMapping("api/status")
public class StatusRestController {

    @Autowired
    private ClinicService clinicService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Collection<Status>> getAllStatus() {
        Collection<Status> statuses = new ArrayList<>(4);
        statuses.addAll(this.clinicService.findAllStatus());
        if (statuses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(statuses, HttpStatus.OK);
    }

    @RequestMapping(value = "/{statusId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Status> getStatus(@PathVariable("statusId") int statusId) {
        Status status = this.clinicService.findStatusById(statusId);
        if (status == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(status, HttpStatus.OK);
    }


    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Status> addStatus(@RequestBody @Valid Status status, BindingResult bindingResult, UriComponentsBuilder ucBuilder) {
        BindingErrorsResponse errors = new BindingErrorsResponse();
        HttpHeaders headers = new HttpHeaders();
        if (bindingResult.hasErrors() || (status == null)) {
            errors.addAllErrors(bindingResult);
            headers.add("errors", errors.toJSON());
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
        status.setId(null);
        this.clinicService.saveStatus(status);
        headers.setLocation(ucBuilder.path("/api/status/{id}").buildAndExpand(status.getId()).toUri());
        return new ResponseEntity<>(status, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{statusId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Status> updateStatus(@PathVariable("statusId") int statusId, @RequestBody @Valid Status status, BindingResult bindingResult) {
        BindingErrorsResponse errors = new BindingErrorsResponse();
        HttpHeaders headers = new HttpHeaders();
        if (bindingResult.hasErrors() || (status == null)) {
            errors.addAllErrors(bindingResult);
            headers.add("errors", errors.toJSON());
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
        Status currentStatus = this.clinicService.findStatusById(statusId);
        if (currentStatus == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        currentStatus.setName(status.getName());
        this.clinicService.saveStatus(currentStatus);
        return new ResponseEntity<>(currentStatus, HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{statusId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Transactional
    public ResponseEntity<Void> deleteStatus(@PathVariable("statusId") int statusId) {
        Status status = this.clinicService.findStatusById(statusId);
        if (status == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.clinicService.deleteStatus(status);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
