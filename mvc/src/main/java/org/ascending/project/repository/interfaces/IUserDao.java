package org.ascending.project.repository.interfaces;

import org.ascending.project.model.User;
public interface IUserDao {

    User getUserByEmail(String email);
    User getUserById(Long id);
    User getUserByCredentials(String identifier, String password) throws Exception;
    User saveUser(User user);
    User update(User user);
    void delete(User user);
    User getUserByUsername(String username);
}
