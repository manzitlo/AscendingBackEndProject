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
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
@RequestMapping(value = "/customer")
public class CustomerController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Customer> getCustomers() {
        List<Customer> customers = customerService.getCustomers();
        return customers;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity getCustomerById(@PathVariable(name = "id") Long id){
        Customer customer = customerService.getById(id);
        if (customer == null) {
            logger.info("Customer Not found...");
            return new ResponseEntity<>("Insurance NOT FOUND", HttpStatus.NOT_FOUND);
        }
        logger.info("This is customer controller, get by {}", id);

        return ResponseEntity.status(HttpStatus.OK).body(customer);
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
    public ResponseEntity<String> save(@RequestBody Customer customer) {
        logger.info("Post a new object {}", customer.getCustomerId());
        customerService.save(customer);
        return new ResponseEntity<>("Customer created successfully", HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        logger.info("Delete customer with ID: {}", id);
        Customer customerToDelete = customerService.getById(id);
        if (customerToDelete == null) {
            // If the customer with the given ID doesn't exist, return a not found response.
            return new ResponseEntity<>("Customer not found", HttpStatus.NOT_FOUND);
        }
        customerService.delete(customerToDelete);
        // If the customer is successfully deleted, return a success response.
        return new ResponseEntity<>("Customer deleted successfully", HttpStatus.OK);
    }

}
