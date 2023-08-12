package org.ascending.project.repository;

import org.ascending.project.model.User;
import org.ascending.project.repository.exception.UserNotFoundException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
@Transactional
public class UserDaoHibernateImpl implements IUserDao{

    @Autowired
    private SessionFactory sessionFactory;
    private static final Logger logger = LoggerFactory.getLogger(UserDaoHibernateImpl.class);

    @Override
    public User getUserByEmail(String email) {
        return null;
    }

    @Override
    public User getUserById(Long id) {
        logger.info("Retrieving user by id: {}", id);
        Session s = sessionFactory.getCurrentSession();
        String hql = "FROM User d where id= :Id";
        Query<User> query = s.createQuery(hql);
        query.setParameter("Id", id);
        User user = query.uniqueResult();
        if(user == null) {
            logger.warn("No user found with id: {}", id);
        }
        return user;
    }

    @Override
    public User getUserByCredentials(String identifier, String password) throws UserNotFoundException {
        logger.info("Retrieving user by identifier: {}", identifier);

        String hql = "FROM User as u where (lower(u.email) = :identifier OR lower(u.name) = :identifier) and u.password = :password";
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            Query<User> query = session.createQuery(hql);
            query.setParameter("identifier", identifier.toLowerCase().trim());
            query.setParameter("password", password);
            User user = query.uniqueResult();

            if(user == null) {
                logger.warn("No user found with identifier: {} and given password.", identifier);
                throw new UserNotFoundException("No user found with identifier: " + identifier);
            }
            return user;
        } catch (HibernateException e) {
            logger.error("Error retrieving user with identifier: {}. Error: {}", identifier, e.getMessage());
            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }


    @Override
    public User saveUser(User user) {
        logger.info("Saving user: {}", user);
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(user);
        logger.info("User saved successfully with id: {}", user.getId());
        return user;
    }

    @Override
    public User update(User user) {
        logger.info("Updating user with id: {}", user.getId());
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
        logger.info("User with id: {} updated successfully", user.getId());
        return getUserById(user.getId());
    }

    @Override
    public void delete(User user) {
        logger.info("Deleting user with id: {}", user.getId());
        Session session = sessionFactory.getCurrentSession();
        session.delete(user);
        logger.info("User with id: {} deleted successfully", user.getId());
    }

}
