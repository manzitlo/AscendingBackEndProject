package org.ascending.project.service;

import org.ascending.project.model.Role;
import org.ascending.project.model.User;
import org.ascending.project.repository.IRoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private IRoleDao RoleDao;

    public void assignRoleToUser(User user, Role role){
        RoleDao.assignRoleToUser(user, role);
    }
    public Role getRoleByName(String roleName) {
        return RoleDao.getRoleByName(roleName);
    }


}
