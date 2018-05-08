package cz.muni.fi.pa036.service;

import cz.muni.fi.pa036.entity.Author;
import cz.muni.fi.pa036.repository.AuthorRepository;
import cz.muni.fi.pa036.rest.dto.AuthorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    final AuthorRepository repository;

    @Autowired
    public AuthorService(AuthorRepository repository) {
        this.repository = repository;
    }

    @Cacheable(value = "author")
    public List<AuthorDTO> findAllAuthors() {
        return repository.findAll().stream().map(AuthorDTO::new).collect(Collectors.toList());
    }

    @Cacheable(value = "author")
    public AuthorDTO findOne(Long id) {
        return new AuthorDTO(repository.findOne(id));
    }

    @CachePut(value = "author", key = "#author.id")
    public AuthorDTO create(AuthorDTO dto) {
        Author author = dto.toDO();
        return new AuthorDTO(repository.save(author));
    }

    @CacheEvict(value = "author", key = "#author.id")
    public AuthorDTO update(AuthorDTO dto) {
        return create(dto);
    }

    @CacheEvict(value = "author")
    public void delete(Long id) {
        repository.delete(id);
    }
}
