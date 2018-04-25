package cz.muni.fi.pa036.rest;

import cz.muni.fi.pa036.entity.Author;
import cz.muni.fi.pa036.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {
    private final AuthorService service;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.service = authorService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Author> getAuthors() {
        return service.findAllAuthors();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Author getAuthor(@PathVariable Long id) {
        return service.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Author createAuthor(@RequestBody Author author) {
        return service.create(author);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Author updateAuthor(@PathVariable Long id, @RequestBody Author author) {
        author.setId(id);
        return service.update(author);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteAuthor(@PathVariable Long id) {
        service.delete(id);
    }

}
