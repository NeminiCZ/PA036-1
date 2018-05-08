package cz.muni.fi.pa036.rest;

import cz.muni.fi.pa036.entity.Publisher;
import cz.muni.fi.pa036.rest.dto.PublisherDTO;
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
    public List<PublisherDTO> getPublishers() {
        return service.findAllPublishers();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public PublisherDTO getPublisher(@PathVariable Long id) {
        return service.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public PublisherDTO createPublisher(@RequestBody PublisherDTO publisher) {
        return service.create(publisher);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public PublisherDTO updatePublisher(@PathVariable Long id, @RequestBody PublisherDTO publisher) {
        publisher.setId(id);
        return service.update(publisher);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deletePublisher(@PathVariable Long id) {
        service.delete(id);
    }

}
