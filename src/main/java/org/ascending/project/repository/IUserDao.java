package org.ascending.project.repository;
import org.ascending.project.model.User;
public interface IUserDao {

    boolean save(User user);
    User getUserByEmail(String email);
    User getUserById(Long id);
    User getUserByCredentials(String email, String password) throws Exception;
}
