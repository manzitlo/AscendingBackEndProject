package org.ascending.project.repository.interfaces;

import org.ascending.project.model.Event;

import java.util.List;

public interface IEventDao {

    void save(Event event);

    List<Event> getEvents();

    Event getById(Long id);

    void delete(Event event);

    Event update(Event event);

}
