package org.ascending.project.controller;

import org.ascending.project.model.Insurance;
import org.ascending.project.service.InsuranceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/insurance")
public class InsuranceController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private InsuranceService insuranceService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Insurance> getInsurances() {
        logger.info("This is insurance controller...");
        List<Insurance> insurances = insuranceService.getInsurances();
        return insurances;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity getInsuranceById(@PathVariable(name = "id") Long id){
        Insurance insurance = insuranceService.getById(id);
        if (insurance == null) {
            logger.info("Insurance Not found...");
            return new ResponseEntity<>("Insurance NOT FOUND", HttpStatus.NOT_FOUND);
        }
        logger.info("This is insurance controller, get by {}", id);

        return ResponseEntity.status(HttpStatus.OK).body(insurance);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH, params = {"id"})
    public Insurance updateCarBrand(@PathVariable("id") Long id, @RequestParam("id") String brand){
        logger.info("pass in variable id: {} and age: {}", id.toString(), brand);
        Insurance i = insuranceService.getById(id);
        i.setId(id);
        i = insuranceService.update(i);
        return i;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<String> save(@RequestBody Insurance insurance) {
        logger.info("Post a new object {}", insurance.getId());
        insuranceService.save(insurance);
        return new ResponseEntity<>("Insurance created", HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        logger.info("Delete insurance with ID: {}", id);
        Insurance insurance = insuranceService.getById(id);
        if (insurance == null) {
            // If the car with the given ID doesn't exist, return a not found response.
            return new ResponseEntity<>("Insurance not found", HttpStatus.NOT_FOUND);
        }
        insuranceService.delete(insurance);
        // If the car is successfully deleted, return a success response.
        return new ResponseEntity<>("Insurance deleted successfully", HttpStatus.OK);
    }
}
