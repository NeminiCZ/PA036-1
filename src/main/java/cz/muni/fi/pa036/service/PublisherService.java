package cz.muni.fi.pa036.service;

import cz.muni.fi.pa036.entity.Publisher;
import cz.muni.fi.pa036.repository.PublisherRepository;
import cz.muni.fi.pa036.rest.dto.PublisherDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublisherService {
    final PublisherRepository repository;

    @Autowired
    public PublisherService(PublisherRepository repository) {
        this.repository = repository;
    }

    @Cacheable(value = "publisher")
    public List<PublisherDTO> findAllPublishers() {
        return repository.findAll().stream().map(PublisherDTO::new).collect(Collectors.toList());
    }

    @Cacheable(value = "publisher")
    public PublisherDTO findOne(Long id) {
        return new PublisherDTO(repository.findOne(id));
    }

    @CachePut(value = "publisher", key = "#publisher.id")
    public PublisherDTO create(PublisherDTO dto) {
        Publisher publisher = dto.toDO();
        return new PublisherDTO(repository.save(publisher));
    }

    @CacheEvict(value = "publisher", key = "#publisher.id")
    public PublisherDTO update(PublisherDTO dto) {
        return create(dto);
    }

    @CacheEvict(value = "publisher")
    public void delete(Long id) {
        repository.delete(id);
    }
}
