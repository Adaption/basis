package io.basis.postservice.repository;

import io.basis.postservice.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {

    Post findByPostId(int id);
}
