package org.ascending.project.repository;

import org.ascending.project.model.Customer;

import java.util.List;

public interface ICustomerDao {

    // Read
    List<Customer> getCustomers();
    // Create
    void save(Customer customer); //
    //Update = 1. get + 2. save [ps: id is primary key!]
    Customer getById(long customer_id);
    // Delete
    void delete(Customer customer); //

    Customer update(Customer customer);
}
