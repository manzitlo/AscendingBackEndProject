package org.ascending.project.service;

import org.ascending.project.model.Role;
import org.ascending.project.model.User;
import org.ascending.project.repository.IRoleDao;
import org.ascending.project.repository.IUserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {

    public final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IUserDao userDao;
    @Autowired
    private IRoleDao roleDao;

    public User getUserByCredentials(String email, String password) throws Exception {
        return userDao.getUserByCredentials(email, password);
    }
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }
    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }
    @Transactional
    public User saveUser(User user) {

        User savedUser;
        try {
            savedUser = userDao.saveUser(user);
            Role defaultRole = roleDao.getRoleByName("user");
            if (defaultRole != null) {
                roleDao.assignRoleToUser(savedUser, defaultRole);
                savedUser.getRoles().add(defaultRole);
                userDao.saveUser(savedUser);
            } else {
                logger.error("Default 'user' role does not exist.");
            }
        } catch (Exception e) {
            logger.error("Error saving user: {}", e.getMessage());
            throw e;
        }
        return savedUser;
    }

    public User update(User user){
        return userDao.update(user);
    }
    public void delete(User user){
        userDao.delete(user);
    }

}
