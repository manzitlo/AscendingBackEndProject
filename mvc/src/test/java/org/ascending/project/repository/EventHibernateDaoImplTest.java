package org.ascending.project.repository;

import org.ascending.project.ApplicationBootstrap;
import org.ascending.project.model.Event;
import org.ascending.project.model.User;
import org.ascending.project.repository.interfaces.IEventDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBootstrap.class)
public class EventHibernateDaoImplTest {

    @Autowired
    private IEventDao IEventDao;
    private Event e1;

    private boolean isE1Saved = false;

    @Before
    public void setUp() throws ParseException {

        User organizer = new User();
        long organizerId = 1L;
        organizer.setId(organizerId);

        e1 = new Event();
        e1.setTitle("Orientation");
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        e1.setDate(dateFormatter.parse("2023-09-01"));
        e1.setDescription("Welcome to GMU");
        e1.setLocation("Horizon Hall");
        e1.setTime("2023-09-01 14:00");
        e1.setUser(organizer);
        IEventDao.save(e1);
        isE1Saved = true;

    }

    @After
    public void tearDown(){
        if (isE1Saved) {
            Event event = IEventDao.getById(e1.getId());
            IEventDao.delete(event);
        }
    }

    @Test
    public void getEventTest() {
        assertEquals(1, IEventDao.getEvents().size());
    }
}