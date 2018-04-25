package cz.muni.fi.pa036.service;

import cz.muni.fi.pa036.entity.Publisher;
import cz.muni.fi.pa036.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {
    final PublisherRepository repository;

    @Autowired
    public PublisherService(PublisherRepository repository) {
        this.repository = repository;
    }

    @Cacheable(value = "publisher")
    public List<Publisher> findAllPublishers() {
        return repository.findAll();
    }

    @Cacheable(value = "publisher")
    public Publisher findOne(Long id) {
        return repository.findOne(id);
    }

    @CachePut(value = "publisher", key = "#publisher.id")
    public Publisher create(Publisher publisher) {
        return repository.save(publisher);
    }

    @CacheEvict(value = "publisher", key = "#publisher.id")
    public Publisher update(Publisher publisher) {
        return create(publisher);
    }

    @CacheEvict(value = "publisher")
    public void delete(Long id) {
        repository.delete(id);
    }
}
