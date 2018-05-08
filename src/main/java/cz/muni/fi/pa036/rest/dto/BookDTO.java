package cz.muni.fi.pa036.rest.dto;

import cz.muni.fi.pa036.entity.Author;
import cz.muni.fi.pa036.entity.Book;

import java.util.List;
import java.util.stream.Collectors;

public class BookDTO {
    private String isbn;

    private String title;

    private Integer pages;

    private List<Long> authors;

    private Long publisher;

    public BookDTO() {
    }

    public BookDTO(String isbn, String title, Integer pages, List<Long> authors, Long publisher) {
        this.isbn = isbn;
        this.title = title;
        this.pages = pages;
        this.authors = authors;
        this.publisher = publisher;
    }

    public BookDTO(Book book) {
        this.isbn = book.getIsbn();
        this.pages = book.getPages();
        this.title = book.getTitle();
        this.publisher = book.getPublisher().getId();
        this.authors = book.getAuthors().stream().map(Author::getId).collect(Collectors.toList());
    }

    public Book toDO() {
        Book book = new Book();
        book.setIsbn(this.isbn);
        book.setTitle(this.title);
        book.setPages(this.pages);
        return book;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public List<Long> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Long> authors) {
        this.authors = authors;
    }

    public Long getPublisher() {
        return publisher;
    }

    public void setPublisher(Long publisher) {
        this.publisher = publisher;
    }
}
