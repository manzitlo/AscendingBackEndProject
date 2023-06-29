package org.ascending.project.repository;


import org.ascending.project.model.Customer;
import org.checkerframework.checker.units.qual.A;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;

import static org.junit.Assert.assertEquals;

public class CustomerHibernateDaoImplTest {

    private CustomerHibernateDaoImpl customerHibernateDao;

    private Customer cu1;

    @Before
    public void setup() {
        customerHibernateDao = new CustomerHibernateDaoImpl();

        cu1 = new Customer();
//        cu1.setCustomerId(2);
        cu1.setLastName("Luo");
        cu1.setFirstName("Wenzhe");
        cu1.setGender("male");
        cu1.setAge(Integer.valueOf("29"));
        cu1.setCarId(12);
        cu1.setTransactionDate(Date.valueOf("2020-02-19"));
        customerHibernateDao.saveCustomer(cu1);
    }

    @After
    public void teardown() {

    }

    @Test
    public void getCustomerTest() {
        assertEquals(2, customerHibernateDao.getCustomers().size());
    }
}