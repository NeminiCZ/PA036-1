package cz.muni.fi.pa036.rest;

import cz.muni.fi.pa036.entity.Book;
import cz.muni.fi.pa036.rest.dto.BookDTO;
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
    public List<BookDTO> getBooks() {
        return service.findAllBooks();
    }

    @RequestMapping(value = "/{isbn}", method = RequestMethod.GET)
    public BookDTO getBook(@PathVariable String isbn) {
        return service.findOne(isbn);
    }

    @RequestMapping(method = RequestMethod.POST)
    public BookDTO createBook(@RequestBody BookDTO book) {
        return service.create(book);
    }

    @RequestMapping(value = "/{isbn}", method = RequestMethod.PUT)
    public BookDTO updateBook(@PathVariable String isbn, @RequestBody BookDTO book) {
        book.setIsbn(isbn);
        return service.update(book);
    }

    @RequestMapping(value = "/{isbn}", method = RequestMethod.DELETE)
    public void deleteBook(@PathVariable String isbn) {
        service.delete(isbn);
    }

}
