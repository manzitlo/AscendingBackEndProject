package org.ascending.project.controller;

import org.ascending.project.model.Car;
import org.ascending.project.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/car")
public class CarController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CarService carService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Car> getCars() {
        List<Car> cars = carService.getCars();
        return cars;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Car getCarById(@PathVariable(name = "id") Long id){
        logger.info("This is car controller, get by {}", id.toString());
        return carService.getById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH, params = {"brand"})
    public Car updateCarBrand(@PathVariable("id") Long id, @RequestParam("brand") String brand){
        logger.info("pass in variable id: {} and age: {}", id.toString(), brand);
        Car ca = carService.getById(id);
        ca.setBrand(brand);
        ca = carService.update(ca);
        return ca;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public void create(@RequestBody Car car) {
        logger.info("Post a new object {}", car.getBrand());
        carService.save(car);
    }
}
