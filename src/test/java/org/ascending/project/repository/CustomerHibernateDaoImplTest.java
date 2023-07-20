package org.ascending.project.repository;

import org.ascending.project.ApplicationBootstrap;
import org.ascending.project.model.Customer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBootstrap.class)
public class CustomerHibernateDaoImplTest {

    @Autowired
    private CustomerHibernateDaoImpl customerHibernateDao;

    private Customer cu1;

    @Before
    public void setup() {

        cu1 = new Customer();
//        cu1.setCustomerId(2);
        cu1.setLastName("Luo");
        cu1.setFirstName("Wenzhe");
        cu1.setGender("male");
        cu1.setAge(Integer.valueOf("29"));
//        cu1.setCarId(15);
        cu1.setTransactionDate(Date.valueOf("2020-02-19"));
        customerHibernateDao.save(cu1);
    }

    @After
    public void teardown() {

    }

    @Test
    public void getCustomerTest() {
        assertEquals(0, customerHibernateDao.getCustomers().size());
    }
}