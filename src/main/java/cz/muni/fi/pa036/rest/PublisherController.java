package cz.muni.fi.pa036.rest;

import cz.muni.fi.pa036.entity.Publisher;
import cz.muni.fi.pa036.service.PublisherService;
import cz.muni.fi.pa036.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publisher")
public class PublisherController {
    private final PublisherService service;

    @Autowired
    public PublisherController(PublisherService authorService) {
        this.service = authorService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Publisher> getPublishers() {
        return service.findAllPublishers();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Publisher getPublisher(@PathVariable Long id) {
        return service.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Publisher createPublisher(@RequestBody Publisher author) {
        return service.create(author);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Publisher updatePublisher(@PathVariable Long id, @RequestBody Publisher author) {
        author.setId(id);
        return service.update(author);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deletePublisher(@PathVariable Long id) {
        service.delete(id);
    }

}
