package cz.muni.fi.pa036.service;

import cz.muni.fi.pa036.entity.Book;
import cz.muni.fi.pa036.repository.AuthorRepository;
import cz.muni.fi.pa036.repository.BookRepository;
import cz.muni.fi.pa036.repository.PublisherRepository;
import cz.muni.fi.pa036.rest.dto.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    final BookRepository bookRepository;
    final AuthorRepository authorRepository;
    final PublisherRepository publisherRepository;

    @Autowired
    public BookService(BookRepository bookRepository, AuthorRepository authorRepository, PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
    }

    @Cacheable(value = "book")
    public List<BookDTO> findAllBooks() {
        return bookRepository.findAll().stream().map(BookDTO::new).collect(Collectors.toList());
    }

    @Cacheable(value = "book")
    public BookDTO findOne(String isbn) {
        return new BookDTO(bookRepository.findOne(isbn));
    }


    @Caching(put = {@CachePut(value = "book", key = "#dto.isbn")},
            evict = {@CacheEvict(value = "publisher", key = "#dto.publisher"),
                    @CacheEvict(value = "author", allEntries = true)})
    public BookDTO create(BookDTO dto) {
        Book book = dto.toDO();
        book.setPublisher(publisherRepository.findOne(dto.getPublisher()));
        book.setAuthors(authorRepository.findAuthorsByIdIn(dto.getAuthors()));
        return new BookDTO(bookRepository.save(book));
    }

    @CacheEvict(value = "book", key = "#dto.isbn")
    public BookDTO update(BookDTO dto) {
        return create(dto);
    }

    @CacheEvict(value = "book")
    public void delete(String isbn) {
        bookRepository.delete(isbn);
    }
}
