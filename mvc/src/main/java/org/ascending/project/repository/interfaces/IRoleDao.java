package org.ascending.project.repository.interfaces;

import org.ascending.project.model.Role;
import org.ascending.project.model.User;
import org.hibernate.Session;

public interface IRoleDao {

    void assignRoleToUser(User user, Role role, Session session);

    Role getRoleByName(String roleName, Session session);
}
