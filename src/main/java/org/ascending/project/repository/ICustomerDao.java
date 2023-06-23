package org.ascending.project.repository;

import org.ascending.project.model.Customer;

import java.util.List;

public interface ICustomerDao {
    public List<Customer> getCustomers();
}
