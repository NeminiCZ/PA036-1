package cz.muni.fi.pa036.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Entity
public class Book {
    @Id
    @NotNull
    private String isbn;

    @NotNull
    private String title;

    @NotNull
    private Integer pages;

    @ManyToMany
    @JsonIgnoreProperties("books")
    private List<Author> authors;

    @ManyToOne
    @JsonIgnoreProperties("books")
    private Publisher publisher;

    public Book() {
    }

    public Book(String isbn, String title, Integer pages) {
        this.isbn = isbn;
        this.title = title;
        this.pages = pages;
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

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(getIsbn(), book.getIsbn()) &&
                Objects.equals(getTitle(), book.getTitle()) &&
                Objects.equals(getPages(), book.getPages());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIsbn(), getTitle(), getPages());
    }
}
