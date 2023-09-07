package org.ascending.project.repository;

import org.ascending.project.model.Role;
import org.ascending.project.model.User;
import org.ascending.project.repository.interfaces.IRoleDao;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoHibernateImpl implements IRoleDao {

    private static final Logger logger = LoggerFactory.getLogger(RoleDaoHibernateImpl.class);


    @Override
    public void assignRoleToUser(User user, Role role, Session session) {


//        Transaction transaction;

        try {

//            transaction = session.beginTransaction();

            user.getRoles().add(role);
            role.getUsers().add(user);
            session.saveOrUpdate(user);
//            transaction.commit();

        } catch (HibernateException e) {

            logger.error("Error while assigning role to user: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public Role getRoleByName(String roleName, Session session) {

        try {

            String hql = "FROM Role r WHERE r.name = :roleName";
            Query<Role> query = session.createQuery(hql);
            query.setParameter("roleName", roleName);
            Role result = query.uniqueResult();

            return result;
        } catch (HibernateException e) {

            logger.error("Error while fetching role by name: {}", e.getMessage());
            throw e;
        }
    }
}
