package io.basic.website.controller;

import io.basic.website.model.Page;
import io.basic.website.service.PageService;
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
public class PageController {
    private final PageService pageService;

    public PageController(PageService pageService) {
        this.pageService = pageService;
    }

    @GetMapping(value = "/pages/{id}")
    public ResponseEntity<Page> getById(@PathVariable("id") String id) {
        try {
            return this.pageService.findOneById(Integer.parseInt(id))
                    .map(page -> status(OK).body(page))
                    .orElseGet(status(NOT_FOUND)::build);
        } catch (NumberFormatException e) {
            return status(BAD_REQUEST).build();
        }
    }

    @GetMapping(value = "/pages", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Page>> getAll() {
        List<Page> pages = this.pageService.findAll();
        return !pages.isEmpty() ? status(OK).body(pages)
                : status(NO_CONTENT).build();
    }

    @PostMapping(value = "/pages", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page> create(@RequestBody Page page) {
        return status(CREATED).body(pageService.create(page));
    }

    @DeleteMapping(value = "/pages/{id}")
    @ResponseStatus(ACCEPTED)
    public void delete(@PathVariable("id") int id) {
        try {
            this.pageService.deleteById(id);
        } catch (EmptyResultDataAccessException e) {

        }
    }
}
