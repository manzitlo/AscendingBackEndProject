package org.ascending.project.controller;

import org.ascending.project.model.Role;
import org.ascending.project.model.User;
import org.ascending.project.service.RoleService;
import org.ascending.project.service.UserService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/signup"})
public class SignupController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private SessionFactory sessionFactory;

    private static final Logger logger = LoggerFactory.getLogger(SignupController.class);

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity userSignup(@RequestBody User user) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            User existingUser = userService.getUserByEmail(user.getEmail());
            if (existingUser != null) {
                return new ResponseEntity<>("User already exists", HttpStatus.BAD_REQUEST);
            }

            User savedUser = userService.saveUser(user, session);

            Role defaultUserRole = roleService.getRoleByName("user", session);
            if (defaultUserRole != null) {
                roleService.assignRoleToUser(savedUser, defaultUserRole, session);
            } else {
                return new ResponseEntity<>("User role not found", HttpStatus.INTERNAL_SERVER_ERROR);
            }

            transaction.commit();
            return ResponseEntity.ok("Registration successful");
        } catch (Exception e) {
            logger.error("Error during user signup: ", e);
            return new ResponseEntity<>("Error occurred during registration", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
