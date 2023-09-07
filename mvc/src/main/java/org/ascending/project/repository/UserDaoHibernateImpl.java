package org.ascending.project.repository;

import org.apache.commons.codec.digest.DigestUtils;
import org.ascending.project.model.User;
import org.ascending.project.repository.exception.NotFoundException;
import org.ascending.project.repository.interfaces.IUserDao;
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

    private static final Logger logger = LoggerFactory.getLogger(UserDaoHibernateImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User getUserByEmail(String email) {

        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            String hql = "FROM User u where u.email= :email";
            Query<User> query = session.createQuery(hql, User.class);
            query.setParameter("email", email);
            User user = query.uniqueResult();
            transaction.commit();

            if (user == null) {
                throw new NotFoundException("No user found with email: " + email, email);
            }
            return user;

        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            logger.error("Error retrieving user by email: {}", email, e);
            throw new NotFoundException("Error retrieving user by email: " + email, email);
        }
    }

    @Override
    public User getUserByUsername(String username) {

        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            String hql = "FROM User u where u.username= :username";
            Query<User> query = session.createQuery(hql, User.class);
            query.setParameter("username", username);
            User user = query.uniqueResult();
            transaction.commit();

            if (user == null) {
                throw new NotFoundException("No user found with username: " + username, username);
            }
            return user;

        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            logger.error("Error retrieving user by email: {}", username, e);
            throw new NotFoundException("Error retrieving user by username: " + username, username);
        }
    }


    @Override
    public User getUserById(Long id) {

        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            String hql = "FROM User u where u.id= :id";
            Query<User> query = session.createQuery(hql, User.class);
            query.setParameter("id", id);
            User user = query.uniqueResult();
            transaction.commit();

            if (user == null) {
                throw new NotFoundException("No user found with id: " + id, id.toString());
            }
            return user;

        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            logger.error("Error retrieving user by email: {}", id, e);
            throw new NotFoundException("Error retrieving user by email: " + id, id.toString());
        }
    }


    public User getUserByCredentials(String identifier, String password) throws NotFoundException {

        if (password == null) {
            throw new IllegalArgumentException("Password cannot be null");
        }

        String hql = "FROM User as u where (lower(u.email) = :identifier OR lower(u.username) = :identifier) and u.password = :password";

        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery(hql);
            query.setParameter("identifier", identifier.toLowerCase().trim());
            query.setParameter("password", password);
            return query.uniqueResult();
        } catch (Exception e) {
            logger.error("error {}", e.getMessage());
            throw new NotFoundException("can't find user record with email=" + identifier + ", password=" + password, identifier);
        }
    }

    @Override
    public User saveUser(User user) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(user);
            transaction.commit();
            logger.info("User saved successfully with id: {}", user.getId());
            return user;
        } catch (RuntimeException e) {
            if (transaction != null) transaction.rollback();
            throw new HibernateException("Error saving user.", e);
        }
    }

    @Override
    public User update(User user) {

        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
            logger.info("User with id: {} updated successfully", user.getId());
            return getUserById(user.getId());
        } catch (RuntimeException e) {
            if (transaction != null) transaction.rollback();
            throw new HibernateException("Error updating user.", e);
        }
    }

    @Override
    public void delete(User user) {

        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(user);
            transaction.commit();
            logger.info("User with id: {} deleted successfully", user.getId());
        } catch (RuntimeException e) {
            if (transaction != null) transaction.rollback();
            throw new HibernateException("Error deleting user.", e);
        }

    }




}
