package org.ascending.project.controller;

import org.ascending.project.model.Role;
import org.ascending.project.model.User;
import org.ascending.project.service.RoleService;
import org.ascending.project.service.UserService;
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

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity userSignup(@RequestBody User user){

        try {
            User existingUser = userService.getUserByEmail(user.getEmail());
            if (existingUser != null) {
                return new ResponseEntity<>("User already exists", HttpStatus.BAD_REQUEST);
            }
            User savedUser = userService.saveUser(user);

            // Get the default "user" role and assign it to the newly registered user
            // This assumes you have a method getRoleByName in your RoleService
            Role defaultUserRole = roleService.getRoleByName("user");
            if (defaultUserRole != null) {
                roleService.assignRoleToUser(savedUser, defaultUserRole);
            } else {
                return new ResponseEntity<>("User role not found", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error occurred during registration", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok("Registration successful");

    }
}
