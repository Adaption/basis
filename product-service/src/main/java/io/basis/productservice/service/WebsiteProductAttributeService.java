package io.basis.productservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.basis.productservice.custom.exception.WebsiteProductAttributeNotFoundException;
import io.basis.productservice.model.WebsiteProductAttribute;
import io.basis.productservice.repository.WebsiteProductAttributeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
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

    public WebsiteProductAttribute createDefault(int websiteId) {
        WebsiteProductAttribute websiteProductAttribute = new WebsiteProductAttribute();
        List<String> attributes = Arrays.asList("Nhà sản xuất",
                "Tình trạng",
                "Đèn LED",
                "Bảo hành");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            websiteProductAttribute.setAttributeName(objectMapper.writeValueAsString(attributes));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        websiteProductAttribute.setWebsiteId(websiteId);

        websiteProductAttributeRepository.saveAndFlush(websiteProductAttribute);
        return websiteProductAttribute;
    }
}
