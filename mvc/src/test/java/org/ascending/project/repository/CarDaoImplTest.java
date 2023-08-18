package org.ascending.project.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class CarDaoImplTest {

    CarDaoImpl carDaoImpl;

    @Before
    public void setup() {
        carDaoImpl = new CarDaoImpl();
    }

    @After
    public void teardown(){
        carDaoImpl = null;
    }

    @Test
    public void getCarsTest() {
        assertEquals(0, carDaoImpl.getCars().size());

    }
}


