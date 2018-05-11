package io.basic.website.controller;

import io.basic.website.model.Website;
import io.basic.website.service.WebsiteService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.ResponseEntity.status;

@RestController
@CrossOrigin
public class WebsiteController {
    private final WebsiteService websiteService;

    public WebsiteController(WebsiteService websiteService) {
        this.websiteService = websiteService;
    }

    @GetMapping(value = "/websites/{id}")
    public ResponseEntity<Website> getById(@PathVariable("id") String id) {
        try {
            return this.websiteService.findOneById(Integer.parseInt(id))
                    .map(website -> status(OK).body(website))
                    .orElseGet(status(NOT_FOUND)::build);
        } catch (NumberFormatException e) {
            return status(NOT_FOUND).build();
        }
    }

    @GetMapping(value = "/websites", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Website>> getAll() {
        List<Website> websites = this.websiteService.findAll();
        return !websites.isEmpty() ? status(OK).body(websites)
                : status(NO_CONTENT).build();
    }

    @PostMapping(value = "/websites", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Website> create(@RequestBody Website website) {
        return status(CREATED).body(websiteService.create(website));
    }

    @DeleteMapping(value = "/websites/{id}")
    @ResponseStatus(ACCEPTED)
    public void delete(@PathVariable("id") int id) {
        try {
            this.websiteService.deleteById(id);
        } catch (EmptyResultDataAccessException e) {

        }
    }
}
