package io.basis.mediaservice.repository;

import io.basis.mediaservice.model.Media;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<Media, Integer> {

    Media findByMediaId(int id);
}
