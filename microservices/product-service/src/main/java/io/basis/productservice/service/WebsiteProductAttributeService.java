package io.basis.productservice.service;

import io.basis.productservice.custom.exception.WebsiteProductAttributeNotFoundException;
import io.basis.productservice.model.WebsiteProductAttribute;
import io.basis.productservice.repository.WebsiteProductAttributeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WebsiteProductAttributeService {
    private final WebsiteProductAttributeRepository websiteProductAttributeRepository;

    public WebsiteProductAttributeService(WebsiteProductAttributeRepository websiteProductAttributeRepository) {
        this.websiteProductAttributeRepository = websiteProductAttributeRepository;
    }

    public List<WebsiteProductAttribute> findAll() {
        return websiteProductAttributeRepository.findAll();
    }

    public Optional<WebsiteProductAttribute> findById(int id) {
        return websiteProductAttributeRepository.findById(id);
    }

    public WebsiteProductAttribute update(int id, String attributeName) throws WebsiteProductAttributeNotFoundException {
        return websiteProductAttributeRepository.findById(id).map(websiteProductAttribute -> {
            websiteProductAttribute.setAttributeName(attributeName);

            websiteProductAttributeRepository.save(websiteProductAttribute);
            websiteProductAttributeRepository.flush();

            return websiteProductAttribute;
        }).orElseThrow(WebsiteProductAttributeNotFoundException::new);
    }

    public WebsiteProductAttribute create(WebsiteProductAttribute websiteProductAttribute) {
        websiteProductAttributeRepository.save(websiteProductAttribute);
        websiteProductAttributeRepository.flush();
        return websiteProductAttribute;
    }
}
