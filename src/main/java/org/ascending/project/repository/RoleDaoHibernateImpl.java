package org.ascending.project.repository;

import org.ascending.project.model.Role;
import org.ascending.project.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.hibernate.query.Query;

import javax.transaction.Transactional;

@Repository
public class RoleDaoHibernateImpl implements IRoleDao {

    @Autowired
    private SessionFactory sessionFactory;

    private static final Logger logger = LoggerFactory.getLogger(RoleDaoHibernateImpl.class);

    @Override
    @Transactional
    public void assignRoleToUser(User user, Role role) {
        try {
            Session session = sessionFactory.getCurrentSession();

            user.getRoles().add(role);
            role.getUsers().add(user);

            session.saveOrUpdate(user);
        } catch (HibernateException e) {
            logger.error("Error while assigning role to user: {}", e.getMessage());
            throw e;
        }
    }



    @Override
    public Role getRoleByName(String roleName) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            String hql = "FROM Role r WHERE r.name = :roleName";
            Query<Role> query = session.createQuery(hql, Role.class);
            query.setParameter("roleName", roleName);

            Role result = query.uniqueResult();

            transaction.commit();
            return result;
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Error while fetching role by name: {}", e.getMessage());
            throw e; // Consider throwing the exception or return null based on your use-case
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
