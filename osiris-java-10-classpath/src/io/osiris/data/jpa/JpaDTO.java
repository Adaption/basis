package io.osiris.data.jpa;

import io.osiris.data.common.dto.DTO;

import java.util.List;
import java.util.Optional;

public abstract class JpaDTO implements DTO {

    protected abstract Optional<? extends JpaDTO> manyToOne();

    protected abstract List<? extends JpaDTO> oneToMany();
}