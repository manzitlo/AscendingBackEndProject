package org.ascending.project.controller;

import org.ascending.project.model.Customer;
import org.ascending.project.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {
    private final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Customer> getCustomers() {
        List<Customer> customers = customerService.getCustomers();
        return customers;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Customer getCustomerById(@PathVariable(name = "id") Long id){
        logger.info("This is customer controller, get by {}", id);
        return customerService.getById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH, params = {"age"})
    public Customer updateCustomerAge(@PathVariable("id") Long id, @RequestParam("age") Integer age){
        logger.info("pass in variable id: {} and age: {}", id.toString(), age);
        Customer c = customerService.getById(id);
        c.setAge(age);
        c = customerService.update(c);
        return c;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Customer> create(@RequestBody Customer customer) {

        if (customer == null || customer.getFirst_name() == null || customer.getLast_name() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        try {
            logger.info("Add one more new customer：{}", customer.getFirst_name());

            customerService.save(customer);

            return ResponseEntity.status(HttpStatus.CREATED).body(customer);

        } catch (Exception e) {

            logger.error("There is an error：{}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
