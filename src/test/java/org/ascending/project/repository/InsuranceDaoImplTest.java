package org.ascending.project.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class InsuranceDaoImplTest {

    InsuranceDaoImpl insuranceDaoImpl;

    @Before
    public void setup() {
        insuranceDaoImpl = new InsuranceDaoImpl();
    }

    @After
    public void teardown(){
        insuranceDaoImpl = null;
    }

    @Test
    public void getInsurancesTest() {
        assertEquals(0, insuranceDaoImpl.getInsurances().size());

    }
}
