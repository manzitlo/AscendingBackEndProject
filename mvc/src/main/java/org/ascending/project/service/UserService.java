package org.ascending.project.service;

import org.ascending.project.model.User;
import org.ascending.project.repository.interfaces.IQuestionDao;
import org.ascending.project.repository.interfaces.IRoleDao;
import org.ascending.project.repository.interfaces.IUserDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IUserDao userDao;

    @Autowired
    private SessionFactory sessionFactory; // Inject the SessionFactory

    public User getUserByCredentials(String identifier, String password) throws Exception {
        return userDao.getUserByCredentials(identifier, password);
    }

    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    public User saveUser(User user) {
        User savedUser;
        try {
            savedUser = userDao.saveUser(user);
        } catch (Exception e) {
            logger.error("Error saving user: {}", e.getMessage());
            throw e;
        }
        return savedUser;
    }

    public User update(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        User updatedUser = null;
        try {
            transaction = session.beginTransaction();

            updatedUser = userDao.update(user);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            logger.error("Error updating user: {}", e.getMessage());
            throw e;
        } finally {
            session.close();
        }
        return updatedUser;
    }

    public void delete(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            userDao.delete(user);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            logger.error("Error deleting user: {}", e.getMessage());
            throw e;
        } finally {
            session.close();
        }
    }

    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }


}

