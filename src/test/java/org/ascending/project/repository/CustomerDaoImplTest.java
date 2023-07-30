package org.ascending.project.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CustomerDaoImplTest {

        CustomerDaoImpl customerDaoImpl;

        @Before
        public void setup() {
            customerDaoImpl = new CustomerDaoImpl();
        }

        @After
        public void teardown(){
            customerDaoImpl = null;
        }

        @Test
        public void getCustomersTest() {
            assertEquals(0, customerDaoImpl.getCustomers().size());

        }
    }
