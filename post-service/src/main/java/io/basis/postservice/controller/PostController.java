package io.basis.postservice.controller;

import io.basis.postservice.model.Post;
import io.basis.postservice.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.ResponseEntity.*;

@RestController
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<Post> getById(@PathVariable("id") int id) {
        return this.postService.getById(id) != null ?
                status(OK).body(postService.getById(id)) :
                status(NOT_FOUND).build();
    }

    @GetMapping("/post")
    public ResponseEntity<List<Post>> getAll() {
        return this.postService.getAll().isEmpty() ?
                status(NO_CONTENT).build():
                status(OK).body(postService.getAll());

    }

    @PostMapping(value = "/post", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(@RequestBody Post post) {
        if (this.postService.getById(post.getPostId()) == null){
            this.postService.create(post);
            return status(CREATED).build();
        }
        return status(BAD_REQUEST).build();
    }

    @DeleteMapping(value = "/post/{id}")
    public ResponseEntity delete(@PathVariable("id") int id) {
        if (this.postService.getById(id) != null) {
            this.postService.delete(id);
            return status(OK).build();
        }
        return status(BAD_REQUEST).build();
    }

    @PutMapping(value = "/media/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity update(@RequestBody Post post){
        if (this.postService.getById(post.getPostId()) != null) {
            this.postService.update(post);
            return status(OK).build();
        }
        return status(BAD_REQUEST).build();
    }
}
