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

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBootstrap.class)
public class CustomerHibernateDaoImplTest {

    @Autowired
    private CustomerHibernateDaoImpl customerHibernateDao;

    private Customer cu1;

    @Before
    public void setup() {

    }

    @After
    public void teardown() {

    }

    @Test
    public void getCustomerTest() {
        assertEquals(3, customerHibernateDao.getCustomers().size());
    }
}