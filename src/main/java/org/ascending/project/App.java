package org.ascending.project;

import org.ascending.project.model.Car;
import org.ascending.project.model.Customer;
import org.ascending.project.model.Insurance;
import org.ascending.project.repository.CarDaoImpl;
import org.ascending.project.repository.CustomerDaoImpl;
import org.ascending.project.repository.InsuranceDaoImpl;

import java.util.List;

/**
 * Running code...
 *
 */

public class App 
{
    public static void main( String[] args )
    {
        CustomerDaoImpl customerDaoImpl = new CustomerDaoImpl();
        List<Customer> customers = customerDaoImpl.getCustomers();
        System.out.format("List of customer: %s", customers);

        System.out.println();
        System.out.println("--------------------------");

        CarDaoImpl carDaoImpl = new CarDaoImpl();
        List<Car> cars = carDaoImpl.getCars();
        System.out.format("List of car: %s", cars);

        System.out.println();
        System.out.println("--------------------------");

        InsuranceDaoImpl insuranceDaoImpl = new InsuranceDaoImpl();
        List<Insurance> insurances = insuranceDaoImpl.getInsurances();
        System.out.format("List of insurance: %s", insurances);

        System.out.println();
        System.out.println("--------------------------");

    }
}
