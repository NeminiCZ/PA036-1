package cz.muni.fi.pa036.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String surname;

    @ManyToMany
    private List<Book> books;

    public Author() {
    }

    public Author(String firstName, String surname) {
        this.firstName = firstName;
        this.surname = surname;
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

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(getId(), author.getId()) &&
                Objects.equals(getFirstName(), author.getFirstName()) &&
                Objects.equals(getSurname(), author.getSurname());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getSurname());
    }
}
