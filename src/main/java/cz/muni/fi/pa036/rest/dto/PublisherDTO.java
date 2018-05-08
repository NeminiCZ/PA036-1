package cz.muni.fi.pa036.rest.dto;

import cz.muni.fi.pa036.entity.Book;
import cz.muni.fi.pa036.entity.Publisher;

import java.util.List;
import java.util.stream.Collectors;

public class PublisherDTO {

    private Long id;

    private String name;

    private List<String> books;

    public PublisherDTO() {
    }

    public PublisherDTO(Long id, String name, List<String> books) {
        this.id = id;
        this.name = name;
        this.books = books;
    }

    public PublisherDTO(Publisher publisher) {
        this.id = publisher.getId();
        this.name = publisher.getName();
        this.books = publisher.getBooks().stream().map(Book::getIsbn).collect(Collectors.toList());
    }

    public Publisher toDO() {
        Publisher publisher = new Publisher();
        publisher.setId(this.id);
        publisher.setName(this.name);
        return publisher;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getBooks() {
        return books;
    }

    public void setBooks(List<String> books) {
        this.books = books;
    }
}
