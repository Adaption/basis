package io.basis.postservice.service;

import io.basis.postservice.model.Post;
import io.basis.postservice.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    public final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post getById(int id) {
        return this.postRepository.findByPostId(id);
    }

    public List<Post> getAll() {
        return this.postRepository.findAll();
    }

    public void create(Post post) {
        this.postRepository.save(post);
    }

    public void delete(int id) {
        this.postRepository.deleteById(id);
    }

    public void update(Post post) {
        if (this.postRepository.getOne(post.getPostId()) != null) {
            this.postRepository.save(post);
        }

    }
}
