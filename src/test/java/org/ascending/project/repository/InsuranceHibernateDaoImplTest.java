package org.ascending.project.repository;

import org.ascending.project.model.Insurance;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class InsuranceHibernateDaoImplTest {
    private InsuranceHibernateDaoImpl insuranceHibernateDao;
    private Insurance d1;

    @Before
    public void setUp() {
        insuranceHibernateDao = new InsuranceHibernateDaoImpl();
        d1 = new Insurance();
//        d1.setId((long) (Math.random()*(100L - 1L)));
        d1.setCompanyName("ABC");
        d1.setSepcifications("half year term");
        insuranceHibernateDao.save(d1);
    }

    @After
    public void tearDown(){
//        insuranceHibernateDao.delete(d1);
    }

    @Test
    public void getInsurancesTest() {
        assertEquals(2, insuranceHibernateDao.getInsurances().size());
    }
}