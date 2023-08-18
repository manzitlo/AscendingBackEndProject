package org.ascending.project.service;

import org.ascending.project.model.Car;
import org.ascending.project.repository.ICarDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CarService {

    @Autowired
    private ICarDao carDao;

    public void save(Car car){
        carDao.save(car);
    }

    public List<Car> getCars() {
        return carDao.getCars();
    }

    public void delete(Car car){
        carDao.delete(car);
    }

    public Car getById(long id){
        return carDao.getById(id);
    }

    public Car update(Car car) {
        return carDao.update(car);
    }
}
