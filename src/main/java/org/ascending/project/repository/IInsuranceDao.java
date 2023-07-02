package org.ascending.project.repository;

import org.ascending.project.model.Insurance;

import java.util.List;

public interface IInsuranceDao {

    // Read
    List<Insurance> getInsurances();
    // Create
    void save(Insurance insurance); //
    //Update = 1. get + 2. save [ps: id is primary key!]
    Insurance getById(long id);
    // Delete
    void delete(Insurance insurance); //
}
