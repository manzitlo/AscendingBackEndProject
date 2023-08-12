package org.ascending.project.repository;

import org.ascending.project.model.Role;
import org.ascending.project.model.User;

public interface IRoleDao {

    void assignRoleToUser(User user, Role role);

    Role getRoleByName(String roleName);

}
