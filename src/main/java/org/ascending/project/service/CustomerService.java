package org.ascending.project.service;

import org.ascending.project.model.Customer;
import org.ascending.project.repository.ICustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private ICustomerDao customerDao;

    public void save(Customer customer){
        customerDao.save(customer);
    }

    public List<Customer> getCustomers() {
        return customerDao.getCustomers();
    }

    public void delete(Customer customer){
        customerDao.delete(customer);
    }

    public Customer getById(long customer_id){
        return customerDao.getById(customer_id);
    }


    public Customer update(Customer customer) {
        return customerDao.update(customer);
    }
}
