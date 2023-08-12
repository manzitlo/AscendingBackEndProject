package org.ascending.project.controller;

import org.ascending.project.model.Car;
import org.ascending.project.service.CarService;
import org.ascending.project.service.InsuranceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/car")
public class CarController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CarService carService;

    @Autowired
    private InsuranceService insuranceService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Car> getCars() {
        logger.info("This is Car controller");
        List<Car> cars = carService.getCars();
        return cars;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity getCarById(@PathVariable(name = "id") Long id){
        Car car = carService.getById(id);
        if (car == null) {
            logger.info("Car Not found...");
            return new ResponseEntity<>("Insurance NOT FOUND", HttpStatus.NOT_FOUND);
        }
        logger.info("This is car controller, get by {}", id);

        return ResponseEntity.status(HttpStatus.OK).body(car);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public Car updateCar(@PathVariable("id") Long id, @RequestParam(value = "brand", required=false) String brand, @RequestParam(value = "model", required=false) String model){
        logger.info("pass in variable id: {} and brand(model): {}({})", id.toString(), brand, model);
        Car ca = carService.getById(id);
        if (brand != null) {
            ca.setBrand(brand);
        }
        if (model != null) {
            ca.setModel(model);
        }
        ca = carService.update(ca);
        return ca;
    }


    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<String> create(@RequestBody Car car) {
        logger.info("Post a new object {}", car.getId());
        carService.save(car);
        return new ResponseEntity<>("Car created successfully", HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        logger.info("Delete car with ID: {}", id);

        Car carToDelete = carService.getById(id);
        if (carToDelete == null) {
            // If the car with the given ID doesn't exist, return a not found response.
            return new ResponseEntity<>("Car not found", HttpStatus.NOT_FOUND);
        }

        carService.delete(carToDelete);
        // If the car is successfully deleted, return a success response.
        return new ResponseEntity<>("Car deleted successfully", HttpStatus.OK);
    }


}
