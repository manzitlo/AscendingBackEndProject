package org.ascending.project.repository;

import org.ascending.project.ApplicationBootstrap;
import org.ascending.project.model.University;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBootstrap.class)
public class UniversityHibernateDaoImplTest {

    @Autowired
    private UniversityHibernateDaoImpl universityHibernateDao;

    @Before
    public void setUp() {

        University u1 = new University();
        u1.setName("George Washington University");
        u1.setDescription("A private university which is located in DC near The White House");
        u1.setRanking(67);
        u1.setLocation("Washington DC");
        u1.setWebsite("www.gwu.edu");
        universityHibernateDao.save(u1);

    }

    @After
    public void tearDown(){

    }

    @Test
    public void getUniversityTest() {
        assertEquals(2, universityHibernateDao.getUniversities().size());
    }


}