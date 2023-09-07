package org.ascending.project.service;

import org.ascending.project.model.Role;
import org.ascending.project.model.User;
import org.ascending.project.repository.interfaces.IRoleDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private IRoleDao RoleDao;

    @Autowired
    private SessionFactory sessionFactory;

    public void assignRoleToUser(User user, Role role, Session session){
        RoleDao.assignRoleToUser(user, role, session);
    }
    public Role getRoleByName(String roleName, Session session) {
        return RoleDao.getRoleByName(roleName, session);
    }


}
