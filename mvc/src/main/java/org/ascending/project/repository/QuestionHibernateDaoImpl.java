package org.ascending.project.repository;

import org.ascending.project.model.Question;
import org.ascending.project.repository.exception.DatabaseAccessException;
import org.ascending.project.repository.exception.NotFoundException;
import org.ascending.project.repository.interfaces.IQuestionDao;
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
public class QuestionHibernateDaoImpl implements IQuestionDao {

    static final Logger logger = LoggerFactory.getLogger(QuestionHibernateDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Question question) {

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.save(question);
                transaction.commit();
            } catch (HibernateException e) {
                transaction.rollback();
                logger.error("Exception while saving question", e);
                throw new DatabaseAccessException("Error accessing the database", e);
            }
        }

    }

    @Override
    public List<Question> getQuestions() {
        List<Question> questions = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Question";
            Query<Question> query = session.createQuery(hql, Question.class);
            questions = query.list();
        } catch (HibernateException e) {
            logger.error("Exception while getting questions", e);
            throw new DatabaseAccessException("Error accessing the database", e);
        }

        return questions;
    }

    @Override
    public Question getById(Long id) {
        Question result = null;
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Question q where q.id = :Id";
            Query<Question> query = session.createQuery(hql, Question.class);
            query.setParameter("Id", id);
            result = query.uniqueResult();
        } catch (HibernateException e) {
            logger.error("Exception while getting question by id", e);
            throw new DatabaseAccessException("Error accessing the database", e);
        }

        if (result == null) {
            throw new NotFoundException("Could not find question with id " + id);
        }

        return result;
    }

    @Override
    public void delete(Question question) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.delete(question);
                transaction.commit();
            } catch (HibernateException e) {
                transaction.rollback();
                logger.error("Exception while deleting question", e);
                throw new DatabaseAccessException("Error accessing the database", e);
            }
        }
    }

    @Override
    public Question update(Question question) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(question);
            transaction.commit();

            return getById(question.getId());
        } catch (HibernateException e) {
            if (transaction != null){
                transaction.rollback();
            }
            logger.error("failed to insert record", e);
            return null;
        }
    }
}
