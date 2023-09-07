package org.ascending.project.controller;

import org.ascending.project.model.Answer;
import org.ascending.project.service.AnswerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "/answer")
public class AnswerController {

    private final Logger logger = LoggerFactory.getLogger(EventController.class);

    @Autowired
    private AnswerService answerService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Answer> getAnswers() {
        return answerService.getAnswers();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity getAnswerById(@PathVariable(name = "id") long id){
        Answer answer = answerService.getById(id);
        if ( answer == null) {
            logger.info("Answer Not found...");
            return new ResponseEntity<>("Answer NOT FOUND", HttpStatus.NOT_FOUND);
        }
        logger.info("This is Answer controller, get by {}", id);
        return ResponseEntity.status(HttpStatus.OK).body(answer);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<Answer> updateEvent(@PathVariable("id") Long id,
                                             @RequestParam(value = "content", required = false) String content) {

        List<String> logParts = new ArrayList<>();

        if (content != null) {
            logParts.add("date: " + content);
        }

        String logMessage = "Update request for ID: " + id + " with " + String.join(", ", logParts);
        logger.info(logMessage);

        try {
            Answer a = answerService.getById(id);
            if (a == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            if (content != null) {
                a.setContent(content);
            }
            a = answerService.update(a);
            return new ResponseEntity<>(a, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error updating the event", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<String> save(@RequestBody Answer answer) {
        logger.info("Post a new object {}", answer.getId());
        answerService.save(answer);
        return new ResponseEntity<>("Event created successfully", HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        logger.info("Delete answer with ID: {}", id);
        Answer answerToDelete = answerService.getById(id);
        if (answerToDelete == null) {
            return new ResponseEntity<>("Answer not found", HttpStatus.NOT_FOUND);
        }
        answerService.delete(answerToDelete);
        return new ResponseEntity<>("Answer deleted successfully", HttpStatus.OK);
    }
}
