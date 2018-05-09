package io.basis.mediaservice.service;

import io.basis.mediaservice.model.Media;
import io.basis.mediaservice.repository.MediaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MediaService {

    public final MediaRepository mediaRepository;

    public MediaService(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

    public Media getById(int id) {
        return this.mediaRepository.findByMediaId(id);
    }

    public List<Media> getAll() {
        return this.mediaRepository.findAll();
    }

    public void create(Media media) {
        this.mediaRepository.save(media);
    }

    public void delete(int id) {
        this.mediaRepository.deleteById(id);
    }

    public void update(Media media) {
        if (this.mediaRepository.getOne(media.getMediaId()) != null) {
            this.mediaRepository.save(media);
        }

    }
}
