package io.basic.website.service;

import io.basic.website.exception.BusinessTypeNotFoundException;
import io.basic.website.model.BusinessType;
import io.basic.website.repository.BusinessTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusinessTypeService {
    private final BusinessTypeRepository businessTypeRepository;

    public BusinessTypeService(BusinessTypeRepository businessTypeRepository) {
        this.businessTypeRepository = businessTypeRepository;
    }

    public List<BusinessType> findAll() {
        return this.businessTypeRepository.findAll();
    }

    public Optional<BusinessType> findOneById(int id) {
        return this.businessTypeRepository.findById(id);
    }

    public BusinessType create(BusinessType businessType) {
        this.businessTypeRepository.save(businessType);
        this.businessTypeRepository.flush();
        return businessType;
    }

    public void deleteById(int id) {
        this.businessTypeRepository.deleteById(id);
    }
}
