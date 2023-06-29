package org.ascending.project.repository;

import org.ascending.project.model.Customer;

import java.util.List;

public interface ICustomerDao {

    // Read
    List<Customer> getCustomers();
    // Create
    void saveCustomer(Customer customer);
    //Update = 1. get + 2. save [ps: id is primary key!]
    Customer getById(long customer_id);
    // Delete
    void deleteCustomer(Customer customer);
}
