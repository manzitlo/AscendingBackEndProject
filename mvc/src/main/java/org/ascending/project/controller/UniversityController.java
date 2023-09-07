package org.ascending.project.controller;

import org.ascending.project.model.University;
import org.ascending.project.service.UniversityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/university")
public class UniversityController {

    private final Logger logger = LoggerFactory.getLogger(UniversityController.class);

    @Autowired
    private UniversityService universityService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<University> getUniversities() {
        List<University> universities = universityService.getUniversities();
        return universities;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity getUniversityById(@PathVariable(name = "id") long id){
        University university = universityService.getById(id);
        if ( university == null) {
            logger.info("University Not found...");
            return new ResponseEntity<>("University NOT FOUND", HttpStatus.NOT_FOUND);
        }
        logger.info("This is customer controller, get by id: {}", id);
        return ResponseEntity.status(HttpStatus.OK).body(university);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH, params = {"ranking"})
    public University updateUniversityRanking(@PathVariable("id") Long id, @RequestParam("ranking") long ranking){
        logger.info("pass in variable id: {} and ranking: {}", id.toString(), ranking);
        University u = universityService.getById(id);
        u.setRanking(ranking);
        u = universityService.update(u);
        return u;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<String> save(@RequestBody University university) {
        logger.info("Post a new object {}", university.getId());
        universityService.save(university);
        return new ResponseEntity<>("University created successfully", HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        logger.info("Delete university with ID: {}", id);
        University universityToDelete = universityService.getById(id);
        if (universityToDelete == null) {
            // If the university with the given ID doesn't exist, return a not found response.
            return new ResponseEntity<>("University not found", HttpStatus.NOT_FOUND);
        }
        universityService.delete(universityToDelete);
        // If the university is successfully deleted, return a success response.
        return new ResponseEntity<>("University deleted successfully", HttpStatus.OK);
    }

}
