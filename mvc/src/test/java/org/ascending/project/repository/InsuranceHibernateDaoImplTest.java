package org.ascending.project.repository;

import org.ascending.project.ApplicationBootstrap;
import org.ascending.project.model.Insurance;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBootstrap.class)
public class InsuranceHibernateDaoImplTest {

    @Autowired
    private InsuranceHibernateDaoImpl insuranceHibernateDao;
    private Insurance d1;

    @Before
    public void setUp() {

        d1 = new Insurance();
        d1.setId(20200210);
        d1.setCompanyName("Progressive");
        d1.setSepcifications("Medical Expenses 3k");
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