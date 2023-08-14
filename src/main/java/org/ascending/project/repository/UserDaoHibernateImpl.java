package org.ascending.project.repository;

import org.ascending.project.model.User;
import org.ascending.project.repository.exception.UserNotFoundException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoHibernateImpl implements IUserDao {

    @Autowired
    private SessionFactory sessionFactory;
    private static final Logger logger = LoggerFactory.getLogger(UserDaoHibernateImpl.class);

    @Override
    public User getUserByEmail(String email) {

        Session s = sessionFactory.getCurrentSession();
        Transaction tx = null;
        try {
            tx = s.beginTransaction();
            String hql = "FROM User d where email= :email";
            Query<User> query = s.createQuery(hql, User.class);
            query.setParameter("email", email);
            User user = query.uniqueResult();
            tx.commit();
            return user;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new HibernateException("Error retrieving user by email.", e);
        }
    }

    @Override
    public User getUserById(Long id) {
        Session s = sessionFactory.getCurrentSession();
        Transaction tx = null;
        try {
            tx = s.beginTransaction();
            String hql = "FROM User d where id= :id";
            Query<User> query = s.createQuery(hql, User.class);
            query.setParameter("id", id);
            User user = query.uniqueResult();
            tx.commit();
            return user;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new HibernateException("Error retrieving user by id.", e);
        }
    }

    @Override
    public User saveUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(user);
            tx.commit();
            logger.info("User saved successfully with id: {}", user.getId());
            return user;
        } catch (RuntimeException e) {
            if (tx != null) tx.rollback();
            throw new HibernateException("Error saving user.", e);
        }
    }

    @Override
    public User update(User user) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(user);
            tx.commit();
            logger.info("User with id: {} updated successfully", user.getId());
            return getUserById(user.getId());
        } catch (RuntimeException e) {
            if (tx != null) tx.rollback();
            throw new HibernateException("Error updating user.", e);
        }
    }

    @Override
    public void delete(User user) {
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(user);
            tx.commit();
            logger.info("User with id: {} deleted successfully", user.getId());
        } catch (RuntimeException e) {
            if (tx != null) tx.rollback();
            throw new HibernateException("Error deleting user.", e);
        }
    }

    @Override
    public User getUserByCredentials(String identifier, String password) throws UserNotFoundException {
        logger.info("Retrieving user by identifier: {}", identifier);

        String hql = "FROM User as u where (lower(u.email) = :identifier OR lower(u.name) = :identifier) and u.password = :password";
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query<User> query = session.createQuery(hql, User.class);
            query.setParameter("identifier", identifier.toLowerCase().trim());
            query.setParameter("password", password);
            User user = query.uniqueResult();
            tx.commit();
            return user;
        } catch (RuntimeException e) {
            if (tx != null) tx.rollback();
            throw new HibernateException("Error retrieving user by credentials.", e);
        }
    }

}
