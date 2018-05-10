package io.basis.postservice.controller;

import io.basis.postservice.model.Post;
import io.basis.postservice.service.PostService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/post/{id}")
    public Post getById(@PathVariable("id") int id) {
        return this.postService.getById(id);
    }

    @GetMapping("/post")
    public List<Post> getAll() {
        return this.postService.getAll();
    }

    @PostMapping(value = "/post", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody Post post) {
        if (this.postService.getById(post.getPostId()) == null){
            this.postService.create(post);
        }
    }

    @DeleteMapping(value = "/post/{id}")
    public void delete(@PathVariable("id") int id) {
        if (this.postService.getById(id) != null) {
            this.postService.delete(id);
        }
    }

    @PutMapping(value = "/media/{id}/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Post post){
        if (this.postService.getById(post.getPostId()) != null) {
            this.postService.update(post);
        }
    }
}
