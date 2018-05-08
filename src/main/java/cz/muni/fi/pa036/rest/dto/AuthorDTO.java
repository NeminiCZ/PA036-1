package cz.muni.fi.pa036.rest.dto;

import cz.muni.fi.pa036.entity.Author;
import cz.muni.fi.pa036.entity.Book;

import java.util.List;
import java.util.stream.Collectors;

public class AuthorDTO {
    private Long id;

    private String firstName;

    private String surname;

    private List<String> books;

    public AuthorDTO() {
    }

    public AuthorDTO(Long id, String firstName, String surname, List<String> books) {
        this.id = id;
        this.firstName = firstName;
        this.surname = surname;
        this.books = books;
    }

    public AuthorDTO(Author author) {
        this.id = author.getId();
        this.firstName = author.getFirstName();
        this.surname = author.getSurname();
        this.books = author.getBooks().stream().map(Book::getIsbn).collect(Collectors.toList());
    }

    public Author toDO() {
        Author author = new Author();
        author.setId(this.id);
        author.setFirstName(this.firstName);
        author.setSurname(this.surname);
        return author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<String> getBooks() {
        return books;
    }

    public void setBooks(List<String> books) {
        this.books = books;
    }
}
