package io.basic.website.service;

import io.basic.website.model.Page;
import io.basic.website.repository.PageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PageService {
    private final PageRepository pageRepository;

    public PageService(PageRepository pageRepository) {
        this.pageRepository = pageRepository;
    }

    public List<Page> findAll() {
        return this.pageRepository.findAll();
    }

    public Page create(Page page) {
        this.pageRepository.save(page);
        this.pageRepository.flush();
        return page;
    }

    public Optional<Page> findOneById(int id) {
        return this.pageRepository.findById(id);
    }

    public void deleteById(int id) {
        this.pageRepository.deleteById(id);
    }
}
