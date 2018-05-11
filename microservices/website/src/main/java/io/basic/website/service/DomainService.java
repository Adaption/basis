package io.basic.website.service;

import io.basic.website.model.Domain;
import io.basic.website.repository.DomainRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DomainService {
    private final DomainRepository domainRepository;

    public DomainService(DomainRepository domainRepository) {
        this.domainRepository = domainRepository;
    }

    public List<Domain> findAll() {
        return this.domainRepository.findAll();
    }

    public Optional<Domain> findOneById(int id) {
        return this.domainRepository.findById(id);
    }

    public Domain create(Domain domain) {
        this.domainRepository.save(domain);
        this.domainRepository.flush();
        return domain;
    }

    public void deleteById(int id) {
        this.domainRepository.deleteById(id);
    }
}
