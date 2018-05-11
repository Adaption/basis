package io.basic.website.service;

import io.basic.website.model.Website;
import io.basic.website.repository.WebsiteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WebsiteService {
    private final WebsiteRepository websiteRepository;

    public WebsiteService(WebsiteRepository websiteRepository) {
        this.websiteRepository = websiteRepository;
    }


    public List<Website> findAll() {
        return this.websiteRepository.findAll();
    }

    public Website create(Website website) {
        this.websiteRepository.save(website);
        this.websiteRepository.flush();
        return website;
    }

    public Optional<Website> findOneById(int id) {
        return this.websiteRepository.findById(id);
    }

    public void deleteById(int id) {
        this.websiteRepository.deleteById(id);
    }
}
