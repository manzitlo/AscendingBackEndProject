package org.ascending.project.repository;

import org.ascending.project.model.University;
import org.ascending.project.repository.exception.DatabaseAccessException;
import org.ascending.project.repository.exception.NotFoundException;
import org.ascending.project.repository.interfaces.IUniversityDao;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UniversityHibernateDaoImpl implements IUniversityDao {
    static final Logger logger = LoggerFactory.getLogger(UniversityHibernateDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public void save(University university) {

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.save(university);
                transaction.commit();
            } catch (HibernateException e) {
                transaction.rollback();
                logger.error("Exception while saving question", e);
                throw new DatabaseAccessException("Error accessing the database", e);
            }
        }

    }

    @Override
    public List<University> getUniversities() {
        List<University> universities;
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM University ";
            Query<University> query = session.createQuery(hql, University.class);
            universities = query.list();
        } catch (HibernateException e) {
            logger.error("Exception while getting questions", e);
            throw new DatabaseAccessException("Error accessing the database", e);
        }
        return universities;
    }

    @Override
    public University getById(long id) {
        University result = null;
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM University u where u.id = :Id";
            Query<University> query = session.createQuery(hql, University.class);
            query.setParameter("Id", id);
            result = query.uniqueResult();
        } catch (HibernateException e) {
            logger.error("Exception while getting university by id", e);
            throw new DatabaseAccessException("Error accessing the database", e);
        }

        if (result == null) {
            throw new NotFoundException("Could not find university with id " + id);
        }

        return result;
    }

    @Override
    public void delete(University university) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.delete(university);
                transaction.commit();
            } catch (HibernateException e) {
                transaction.rollback();
                logger.error("Exception while deleting question", e);
                throw new DatabaseAccessException("Error accessing the database", e);
            }
        }
    }

    @Override
    public University update(University university) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(university);
            transaction.commit();

            return getById(university.getId());
        } catch (HibernateException e) {
            if (transaction != null){
                transaction.rollback();
            }
            logger.error("failed to insert record", e);
            return null;
        }
    }
}
