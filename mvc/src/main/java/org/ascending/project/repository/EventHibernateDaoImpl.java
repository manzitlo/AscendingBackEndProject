package org.ascending.project.repository;

import org.ascending.project.model.Event;
import org.ascending.project.repository.exception.DatabaseAccessException;
import org.ascending.project.repository.exception.NotFoundException;
import org.ascending.project.repository.interfaces.IEventDao;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EventHibernateDaoImpl implements IEventDao {

    static final Logger logger = LoggerFactory.getLogger(EventHibernateDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Event event) {

        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.save(event);
                transaction.commit();
            } catch (HibernateException e) {
                transaction.rollback();
                logger.error("Exception while saving event", e);
                throw new DatabaseAccessException("Error accessing the database", e);
            }
        }

    }

    @Override
    public List<Event> getEvents() {
        List<Event> events = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Event";
            Query<Event> query = session.createQuery(hql, Event.class);
            events = query.list();
        } catch (HibernateException e) {
            logger.error("Exception while getting events", e);
            throw new DatabaseAccessException("Error accessing the database", e);
        }
        return events;
    }

    @Override
    public Event getById(Long id) {
        Event result = null;
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Event e where e.id = :Id";
            Query<Event> query = session.createQuery(hql, Event.class);
            query.setParameter("Id", id);
            result = query.uniqueResult();
        } catch (HibernateException e) {
            logger.error("Exception while getting event by id", e);
            throw new DatabaseAccessException("Error accessing the database", e);
        }

        if (result == null) {
            throw new NotFoundException("Could not find event with id " + id);
        }

        return result;
    }

    @Override
    public void delete(Event event) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.delete(event);
                transaction.commit();
            } catch (HibernateException e) {
                transaction.rollback();
                logger.error("Exception while deleting event", e);
                throw new DatabaseAccessException("Error accessing the database", e);
            }
        }
    }

    @Override
    public Event update(Event event) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            Event persistedEvent = session.get(Event.class, event.getId());
            if (persistedEvent != null) {
                persistedEvent.setTitle(event.getTitle());
                persistedEvent.setDate(event.getDate());
                persistedEvent.setTime(event.getTime());
                persistedEvent.setLocation(event.getLocation());
                persistedEvent.setDescription(event.getDescription());
            } else {
                throw new NotFoundException("Could not find event with id " + event.getId());
            }

            session.flush();

            transaction.commit();

            session.refresh(persistedEvent);
            return persistedEvent;
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Failed to update event with id: " + event.getId(), e);
            throw new DatabaseAccessException("Error updating the event in the database", e);
        }
    }

}
