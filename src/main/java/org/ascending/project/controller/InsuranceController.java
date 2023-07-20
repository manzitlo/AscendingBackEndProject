package org.ascending.project.controller;

import org.ascending.project.model.Insurance;
import org.ascending.project.service.InsuranceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/insurance")
public class InsuranceController {

    private final Logger logger = LoggerFactory.getLogger(CarController.class);

    @Autowired
    private InsuranceService insuranceService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Insurance> getInsurances() {
        List<Insurance> insurances = insuranceService.getInsurances();
        return insurances;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Insurance getInsuranceById(@PathVariable(name = "id") Long id){
        logger.info("This is car controller, get by {}", id.toString());
        return insuranceService.getById(id);
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
    public void create(@RequestBody Insurance insurance) {
        logger.info("Post a new object {}", insurance.getSepcifications());
        insuranceService.save(insurance);
    }
}
