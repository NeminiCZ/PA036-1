package cz.muni.fi.pa036.repository;

import cz.muni.fi.pa036.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findAuthorsByIdIn(List<Long> ids);
}
