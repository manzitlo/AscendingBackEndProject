package org.ascending.project.repository;

import org.ascending.project.ApplicationBootstrap;
import org.ascending.project.model.Question;
import org.ascending.project.model.University;
import org.ascending.project.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.sql.Timestamp;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBootstrap.class)
public class QuestionHibernateDaoImplTest {

    @Autowired
    private QuestionHibernateDaoImpl questionHibernateDao;

    @Before
    public void setUp() {

        questionHibernateDao = new QuestionHibernateDaoImpl();

        User user = new User();
        University university = new University();
        long userId = 1L;
        user.setId(userId);
        long universityId = 3L;
        university.setId(universityId);

        Question q1 = new Question();
        q1.setUser(user);
        q1.setUniversity(university);
        q1.setContent("May I ask when is the date for our orientation?");
        q1.setCreatedAt(Timestamp.valueOf("2023-09-03 19:32:00"));
        q1.setUpdatedAt(null    );

        questionHibernateDao.save(q1);

    }

    @After
    public void tearDown() {

    }

    @Test
    public void getQuestionTest() {
        assertEquals(2, questionHibernateDao.getQuestions().size());
    }

}