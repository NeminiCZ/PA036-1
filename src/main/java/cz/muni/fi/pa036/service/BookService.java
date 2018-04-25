package cz.muni.fi.pa036.service;

import cz.muni.fi.pa036.entity.Book;
import cz.muni.fi.pa036.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    final BookRepository repository;

    @Autowired
    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    @Cacheable(value = "book")
    public List<Book> findAllBooks() {
        return repository.findAll();
    }

    @Cacheable(value = "book")
    public Book findOne(String isbn) {
        return repository.findOne(isbn);
    }

    @CachePut(value = "book", key = "#book.isbn")
    public Book create(Book book) {
        return repository.save(book);
    }

    @CacheEvict(value = "book", key = "#book.isbn")
    public Book update(Book book) {
        return create(book);
    }

    @CacheEvict(value = "book")
    public void delete(String isbn) {
        repository.delete(isbn);
    }
}
