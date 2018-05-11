package io.basic.website.controller;

import io.basic.website.model.Domain;
import io.basic.website.service.DomainService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.ResponseEntity.status;

@RestController
@CrossOrigin
public class DomainController {
    private final DomainService domainService;

    public DomainController(DomainService domainService) {
        this.domainService = domainService;
    }

    @GetMapping(value = "/domains/{id}")
    public ResponseEntity<Domain> getById(@PathVariable("id") String id) {
        try {
            return this.domainService.findOneById(Integer.parseInt(id))
                    .map(businessType -> status(OK).body(businessType))
                    .orElseGet(status(NOT_FOUND)::build);
        } catch (NumberFormatException e) {
            return status(NOT_FOUND).build();
        }
    }

    @GetMapping(value = "/domains", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Domain>> getAll() {
        List<Domain> domains = this.domainService.findAll();
        return !domains.isEmpty() ? status(OK).body(domains)
                : status(NO_CONTENT).build();
    }

    @PostMapping(value = "/domains", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Domain> create(@RequestBody Domain domain) {
        return status(CREATED).body(domainService.create(domain));
    }

    @DeleteMapping(value = "/domains/{id}")
    @ResponseStatus(ACCEPTED)
    public void delete(@PathVariable("id") int id) {
        try {
            this.domainService.deleteById(id);
        } catch (EmptyResultDataAccessException e) {

        }
    }
}
