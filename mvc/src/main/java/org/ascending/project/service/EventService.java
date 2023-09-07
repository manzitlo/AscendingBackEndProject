package org.ascending.project.service;

import org.ascending.project.model.Event;
import org.ascending.project.repository.interfaces.IEventDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class EventService {

    private final IEventDao IEventDao;

    @Autowired
    public EventService(IEventDao IEventDao) {
        this.IEventDao = IEventDao;
    }

    public void save(Event event){
        IEventDao.save(event);
    }

    public void delete(Event event){
        IEventDao.delete(event);
    }

    public List<Event> getEvents(){
        return IEventDao.getEvents();
    }

    public Event update(Event event){
        return IEventDao.update(event);
    }

    public Event getById(long id){
        return IEventDao.getById(id);
    }
}
