package org.ascending.project.repository;

import org.ascending.project.ApplicationBootstrap;
import org.ascending.project.model.Answer;
import org.ascending.project.model.Question;
import org.ascending.project.model.User;
import org.ascending.project.repository.interfaces.IAnswerDao;
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
public class AnswerHibernateDaoImplTest {

    @Autowired
    private org.ascending.project.repository.interfaces.IAnswerDao IAnswerDao;

    @Before
    public void setUp(){

        User user = new User();
        long userId = 1L;
        user.setId(userId);
        Question question = new Question();
        long questionId = 1L;
        question.setId(questionId);

        Answer a1 = new Answer();
        a1.setUser(user);
        a1.setQuestion(question);
        a1.setContent("I guess it will be the end of August");
        a1.setCreatedAt(Timestamp.valueOf("2023-09-04 10:32:00"));
        a1.setUpdatedAt(null);
        IAnswerDao.save(a1);

    }

    @After
    public void tearDown(){

    }

    @Test
    public void getAnswerTest(){
        assertEquals(1, IAnswerDao.getAnswers().size());
    }

}