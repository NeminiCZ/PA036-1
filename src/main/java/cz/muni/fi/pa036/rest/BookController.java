package cz.muni.fi.pa036.rest;

import cz.muni.fi.pa036.entity.Book;
import cz.muni.fi.pa036.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    private final BookService service;

    @Autowired
    public BookController(BookService authorService) {
        this.service = authorService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Book> getBooks() {
        return service.findAllBooks();
    }

    @RequestMapping(value = "/{isbn}", method = RequestMethod.GET)
    public Book getBook(@PathVariable String isbn) {
        return service.findOne(isbn);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Book createBook(@RequestBody Book author) {
        return service.create(author);
    }

    @RequestMapping(value = "/{isbn}", method = RequestMethod.PUT)
    public Book updateBook(@PathVariable String isbn, @RequestBody Book author) {
        author.setIsbn(isbn);
        return service.update(author);
    }

    @RequestMapping(value = "/{isbn}", method = RequestMethod.DELETE)
    public void deleteBook(@PathVariable String isbn) {
        service.delete(isbn);
    }

}
