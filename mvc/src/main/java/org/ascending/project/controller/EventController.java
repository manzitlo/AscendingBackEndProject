package org.ascending.project.controller;

import org.ascending.project.model.Event;
import org.ascending.project.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/event")
public class EventController {

    private final Logger logger = LoggerFactory.getLogger(EventController.class);

    @Autowired
    private EventService eventService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Event> getEvents() {
        return eventService.getEvents();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity getEventById(@PathVariable(name = "id") long id){
        Event event = eventService.getById(id);
        if ( event == null) {
            logger.info("Event Not found...");
            return new ResponseEntity<>("Event NOT FOUND", HttpStatus.NOT_FOUND);
        }
        logger.info("This is Event controller, get by {}", id);
        return ResponseEntity.status(HttpStatus.OK).body(event);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<Event> updateEvent(@PathVariable("id") Long id,
                                             @RequestParam(value = "date", required = false) Date date,
                                             @RequestParam(value = "title", required = false) String title,
                                             @RequestParam(value = "time", required = false) String time,
                                             @RequestParam(value = "location", required = false) String location,
                                             @RequestParam(value = "description", required = false) String description) {

        List<String> logParts = new ArrayList<>();
        if (date != null) {
            logParts.add("date: " + date);
        }
        if (title != null) {
            logParts.add("title: " + title);
        }
        if (time != null) {
            logParts.add("time: " + time);
        }
        if (location != null) {
            logParts.add("location: " + location);
        }
        if (description != null) {
            logParts.add("description: " + description);
        }

        String logMessage = "Update request for ID: " + id + " with " + String.join(", ", logParts);
        logger.info(logMessage);

        try {
            Event e = eventService.getById(id);
            if (e == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            if (date != null) {
                e.setDate(date);
            }
            if (title != null) {
                e.setTitle(title);
            }
            if (time != null) {
                e.setTime(time);
            }
            if (location != null) {
                e.setLocation(location);
            }
            if (description != null) {
                e.setDescription(description);
            }
            e = eventService.update(e);
            return new ResponseEntity<>(e, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error updating the event", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<String> save(@RequestBody Event event) {
        logger.info("Post a new object {}", event.getId());
        eventService.save(event);
        return new ResponseEntity<>("Event created successfully", HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        logger.info("Delete question with ID: {}", id);
        Event eventToDelete = eventService.getById(id);
        if (eventToDelete == null) {
            return new ResponseEntity<>("Event not found", HttpStatus.NOT_FOUND);
        }
        eventService.delete(eventToDelete);
        return new ResponseEntity<>("Event deleted successfully", HttpStatus.OK);
    }


}
