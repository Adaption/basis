package io.basic.website.controller;

import io.basic.website.model.BusinessType;
import io.basic.website.service.BusinessTypeService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.ResponseEntity.status;

@RestController
@CrossOrigin
public class BusinessTypeController {
    private final BusinessTypeService businessTypeService;

    public BusinessTypeController(BusinessTypeService businessTypeService) {
        this.businessTypeService = businessTypeService;
    }

    @GetMapping(value = "/businessTypes/{id}")
    public ResponseEntity<BusinessType> getById(@PathVariable("id") String id) {
        try {
            return this.businessTypeService.findOneById(Integer.parseInt(id))
                    .map(businessType -> status(OK).body(businessType))
                    .orElseGet(status(NOT_FOUND)::build);
        } catch (NumberFormatException e) {
            return status(NOT_FOUND).build();
        }
    }

    @GetMapping(value = "/businessTypes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BusinessType>> getAll() {
        List<BusinessType> businessTypes = this.businessTypeService.findAll();
        return !businessTypes.isEmpty() ? status(OK).body(businessTypes)
                : status(NO_CONTENT).build();
    }

    @PostMapping(value = "/businessTypes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BusinessType> create(@RequestBody BusinessType businessType) {
        return status(CREATED).body(businessTypeService.create(businessType));
    }

    @DeleteMapping(value = "/businessTypes/{id}")
    @ResponseStatus(ACCEPTED)
    public void delete(@PathVariable("id") int id) {
        try {
            this.businessTypeService.deleteById(id);
        } catch (EmptyResultDataAccessException e) {

        }
    }
}
