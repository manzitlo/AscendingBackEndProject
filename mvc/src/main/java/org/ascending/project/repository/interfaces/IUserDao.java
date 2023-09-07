package org.ascending.project.repository;

import org.ascending.project.model.User;
public interface IUserDao {

    User getUserByEmail(String email);
    User getUserById(Long id);
    User getUserByCredentials(String email, String name, String password) throws Exception;
    User saveUser(User user);
    User update(User user);
    void delete(User user);
    User getUserByName(String name);
}
