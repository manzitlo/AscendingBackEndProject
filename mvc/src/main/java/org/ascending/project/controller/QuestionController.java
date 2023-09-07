package org.ascending.project.controller;

import org.ascending.project.model.Question;
import org.ascending.project.service.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping(value = "/question")
public class QuestionController {

    private final Logger logger = LoggerFactory.getLogger(QuestionController.class);

    @Autowired
    private QuestionService questionService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Question> getQuestions() {
        List<Question> questions = questionService.getQuestions();
        return questions;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity getQuestionById(@PathVariable(name = "id") long id){
        Question question = questionService.getById(id);
        if (question == null) {
            logger.info("Question Not found...");
            return new ResponseEntity<>("Question NOT FOUND", HttpStatus.NOT_FOUND);
        }


        logger.info("This is question controller, get by {}", id);
        return ResponseEntity.status(HttpStatus.OK).body(question);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH, params = {"updatedAt"})
    public Question updateQuestionRanking(@PathVariable("id") Long id, @RequestParam("updatedAt") Timestamp updatedAt){
        logger.info("pass in variable id: {} and update Time: {}", id.toString(), updatedAt);
        Question q = questionService.getById(id);
        q.setUpdatedAt(updatedAt);
        q = questionService.update(q);
        return q;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<String> save(@RequestBody Question question) {
        logger.info("Post a new object {}", question.getId());
        questionService.save(question);
        return new ResponseEntity<>("Question created successfully", HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        logger.info("Delete question with ID: {}", id);
        Question questionToDelete = questionService.getById(id);
        if (questionToDelete == null) {
            return new ResponseEntity<>("Question not found", HttpStatus.NOT_FOUND);
        }
        questionService.delete(questionToDelete);
        return new ResponseEntity<>("Question deleted successfully", HttpStatus.OK);
    }

}
