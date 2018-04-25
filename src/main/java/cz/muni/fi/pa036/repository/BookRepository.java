package cz.muni.fi.pa036.repository;

import cz.muni.fi.pa036.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends JpaRepository<Book, String> {
}
