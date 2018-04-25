package cz.muni.fi.pa036.service;

import cz.muni.fi.pa036.entity.Author;
import cz.muni.fi.pa036.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    final AuthorRepository repository;

    @Autowired
    public AuthorService(AuthorRepository repository) {
        this.repository = repository;
    }

    @Cacheable(value = "author")
    public List<Author> findAllAuthors() {
        return repository.findAll();
    }

    @Cacheable(value = "author")
    public Author findOne(Long id) {
        return repository.findOne(id);
    }

    @CachePut(value = "author", key = "#author.id")
    public Author create(Author author) {
        return repository.save(author);
    }

    @CacheEvict(value = "author", key = "#author.id")
    public Author update(Author author) {
        return create(author);
    }

    @CacheEvict(value = "author")
    public void delete(Long id) {
        repository.delete(id);
    }
}
