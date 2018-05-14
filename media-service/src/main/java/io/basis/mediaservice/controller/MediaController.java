package io.basis.mediaservice.controller;

import io.basis.mediaservice.model.Media;
import io.basis.mediaservice.service.MediaService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MediaController {

    private final MediaService mediaService;

    public MediaController(MediaService mediaService) {
        this.mediaService = mediaService;
    }


    @GetMapping("/media/{id}")
    public Media getById(@PathVariable("id") int id) {
        return this.mediaService.getById(id);
    }

    @GetMapping("/media")
    public List<Media> getAll() {
        return this.mediaService.getAll();
    }

    @PostMapping(value = "/media", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody Media media) {
        if (this.mediaService.getById(media.getMediaId()) == null){
            this.mediaService.create(media);
        }
    }

    @DeleteMapping(value = "/media/{id}")
    public void delete(@PathVariable("id") int id) {
        if (this.mediaService.getById(id) != null) {
            this.mediaService.delete(id);
        }
    }

    @PutMapping(value = "/media/{id}/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Media media){
        if (this.mediaService.getById(media.getMediaId()) != null) {
            this.mediaService.update(media);
        }
    }
}
