package org.ascending.project.service;

import org.ascending.project.model.User;
import org.ascending.project.repository.IUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private IUserDao userDao;
    public User getUserByCredentials(String email, String password) throws Exception {
        return userDao.getUserByCredentials(email, password);
    }

    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

}
