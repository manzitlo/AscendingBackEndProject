package org.ascending.project.repository;

import org.ascending.project.model.Car;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CarHibernateDaoImplTest {

    private CarHibernateDaoImpl carHibernateDao;

    private Car c1;

    @Before
    public void setUp(){
        carHibernateDao = new CarHibernateDaoImpl();
        c1 = new Car();
//        c1.setId(20140101);
        c1.setBrand("Benz");
        c1.setModel("GL450");
        c1.setYear(2014);
        c1.setColor("black");
        c1.setInsuranceId(25);
        carHibernateDao.saveCar(c1);
    }

    @After
    public void teardown() {
//        carHibernateDao.deleteCar(c1);
    }

    @Test
    public void getCarTest() {
        assertEquals(1, carHibernateDao.getCars().size());
    }


}