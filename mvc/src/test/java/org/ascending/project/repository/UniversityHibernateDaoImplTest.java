package org.ascending.project.repository;

import org.ascending.project.ApplicationBootstrap;
import org.ascending.project.model.Event;
import org.ascending.project.model.University;
import org.ascending.project.repository.interfaces.IUniversityDao;
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
    private IUniversityDao universityDao;

    private University u1;

    private boolean isU1Saved = false;

    @Before
    public void setUp() {

        u1 = new University();

        u1.setName("George Mason University");
        u1.setDescription("A public research university in Fairfax County, Virginia");
        u1.setRanking(121);
        u1.setLocation("Fairfax, Virginia");
        u1.setWebsite("https://www.gmu.edu");
        universityDao.save(u1);

        isU1Saved = true;

    }

    @After
    public void tearDown(){

        if (isU1Saved == true){
            University university = universityDao.getById(u1.getId());
            universityDao.delete(university);
        }
    }

    @Test
    public void getUniversityTest() {
        assertEquals(2, universityDao.getUniversities().size());
    }


}