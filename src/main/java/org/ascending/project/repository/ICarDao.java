package org.ascending.project.repository;

import org.ascending.project.model.Car;

import java.util.List;

public interface ICarDao {

    // Read
    List<Car> getCars();
    // Create
    void save(Car car);
    //Update = 1. get + 2. save [ps: id is primary key!]
    Car getById(long id);
    // Delete
    void delete(Car car);
}
