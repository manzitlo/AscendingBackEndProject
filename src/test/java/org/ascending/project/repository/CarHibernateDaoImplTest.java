package org.ascending.project.repository;

import org.ascending.project.model.Car;
import org.ascending.project.model.Customer;
import org.ascending.project.model.Insurance;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;

import static junit.framework.Assert.assertEquals;

public class CarHibernateDaoImplTest {

    private CarHibernateDaoImpl carHibernateDao;
    private CustomerHibernateDaoImpl customerHibernateDao;
    private Car c1;
    private Car c2;
    private Customer cu1;
    private Customer cu2;
    private Customer cu3;

    @Before
    public void setUp(){
        carHibernateDao = new CarHibernateDaoImpl();
        customerHibernateDao = new CustomerHibernateDaoImpl();
        c1 = new Car();
        c1.setBrand("Benz");
        c1.setModel("GL450");
        c1.setYear(2014);
        c1.setColor("black");
        c1.setInsuranceId(60);
        carHibernateDao.save(c1);

        c2 = new Car();
        c2.setBrand("Honda");
        c2.setModel("Passport");
        c2.setYear(2023);
        c2.setColor("grey");
        c2.setInsuranceId(59);
        carHibernateDao.save(c2);

        cu1 = new Customer();
        cu1.setAge(30);
        cu1.setFirstName("Wenzhe");
        cu1.setLastName("Luo");
        cu1.setGender("Male");
        cu1.setTransactionDate(Date.valueOf("2020-02-10"));
        cu1.setCar(c1);
        customerHibernateDao.save(cu1);

        cu2 = new Customer();
        cu2.setAge(28);
        cu2.setFirstName("Yiming");
        cu2.setLastName("Dong");
        cu2.setGender("Female");
        cu2.setTransactionDate(Date.valueOf("2023-02-10"));
        cu2.setCar(c2);
        customerHibernateDao.save(cu2);

        cu3 = new Customer();
        cu3.setAge(3);
        cu3.setFirstName("Tracy");
        cu3.setLastName("DOXIE");
        cu3.setGender("Female");
        cu3.setTransactionDate(Date.valueOf("2023-02-10"));
        cu3.setCar(c2);
        customerHibernateDao.save(cu3);
    }

    @After
    public void teardown() {
//        carHibernateDao.delete(c1);
    }

    @Test
    public void getCarTest() {
        assertEquals(2, carHibernateDao.getCars().size());
    }


}