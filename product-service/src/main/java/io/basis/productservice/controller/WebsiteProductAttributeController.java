package io.basis.productservice.controller;

import io.basis.productservice.custom.exception.WebsiteProductAttributeNotFoundException;
import io.basis.productservice.model.WebsiteProductAttribute;
import io.basis.productservice.service.WebsiteProductAttributeService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.ResponseEntity.status;

@RestController
public class WebsiteProductAttributeController {

    private final WebsiteProductAttributeService websiteProductAttributeService;

    public WebsiteProductAttributeController(WebsiteProductAttributeService websiteProductAttributeService) {
        this.websiteProductAttributeService = websiteProductAttributeService;
    }

    @GetMapping("/website-product-attributes")
    public ResponseEntity<List<WebsiteProductAttribute>> findAll() {
        List<WebsiteProductAttribute> attributes = websiteProductAttributeService.findAll();
        return !attributes.isEmpty() ? status(OK).body(attributes) : status(NO_CONTENT).build();
    }

    @GetMapping("/website-product-attributes/{id}")
    public ResponseEntity<WebsiteProductAttribute> findById(@PathVariable("id") int id) {
        return websiteProductAttributeService.findById(id)
                                                .map(websiteProductAttribute -> status(OK).body(websiteProductAttribute))
                                                .orElseGet(status(NOT_FOUND)::build);
    }

    @PutMapping("/website-product-attributes/{id}")
    public ResponseEntity<WebsiteProductAttribute> update(@PathVariable("id") int id, String attributeName) {
        try {
            return ResponseEntity.status(OK).body(websiteProductAttributeService.update(id, attributeName));
        } catch (WebsiteProductAttributeNotFoundException e) {
            return ResponseEntity.status(BAD_REQUEST).build();
        }
    }

    @PostMapping(value = "/website-product-attributes", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebsiteProductAttribute> create(@RequestBody WebsiteProductAttribute websiteProductAttribute) {
        return status(CREATED).body(websiteProductAttributeService.create(websiteProductAttribute));
    }

    @PostMapping(value = "/website-product-attributes", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WebsiteProductAttribute> createDefault(@PathVariable("website_id") int websiteId) {
        return status(CREATED).body(websiteProductAttributeService.createDefault(websiteId));
    }
}
