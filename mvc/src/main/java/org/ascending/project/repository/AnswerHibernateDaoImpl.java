package org.ascending.project.repository;

import org.ascending.project.model.Answer;
import org.ascending.project.repository.exception.DatabaseAccessException;
import org.ascending.project.repository.exception.NotFoundException;
import org.ascending.project.repository.interfaces.IAnswerDao;
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
public class AnswerHibernateDaoImpl implements IAnswerDao {
    static final Logger logger = LoggerFactory.getLogger(AnswerHibernateDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Answer> getAnswers() {
        List<Answer> answers = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Answer";
            Query<Answer> query = session.createQuery(hql, Answer.class);
            answers = query.list();
        } catch (HibernateException e) {
            logger.error("Exception while getting answers", e);
            throw new DatabaseAccessException("Error accessing the database", e);
        }
        return answers;
    }

    @Override
    public void save(Answer answer) {

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.save(answer);
                transaction.commit();
            } catch (HibernateException e) {
                transaction.rollback();
                logger.error("Exception while saving answer", e);
                throw new DatabaseAccessException("Error accessing the database", e);
            }
        }
    }

    @Override
    public Answer getById(Long id) {
        Answer result = null;
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Event e where e.id = :Id";
            Query<Answer> query = session.createQuery(hql, Answer.class);
            query.setParameter("Id", id);
            result = query.uniqueResult();
        } catch (HibernateException e) {
            logger.error("Exception while getting answer by id", e);
            throw new DatabaseAccessException("Error accessing the database", e);
        }

        if (result == null) {
            throw new NotFoundException("Could not find answer with id " + id);
        }

        return result;
    }

    @Override
    public void delete(Answer answer) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.delete(answer);
                transaction.commit();
            } catch (HibernateException e) {
                transaction.rollback();
                logger.error("Exception while deleting answer", e);
                throw new DatabaseAccessException("Error accessing the database", e);
            }
        }
    }

    @Override
    public Answer update(Answer answer) {

        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(answer);
            transaction.commit();

            return getById(answer.getId());
        } catch (HibernateException e) {
            if (transaction != null){
                transaction.rollback();
            }
            logger.error("failed to insert record", e);
            throw new DatabaseAccessException("Error accessing the database", e);
        }
    }

}

