package org.ascending.project.controller;

import org.ascending.project.model.User;
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

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity userSignup(@RequestBody User user){

        try {
            User u = userService.getUserByEmail(user.getEmail());
            if (u != null) {
                return new ResponseEntity<>("User already exists", HttpStatus.BAD_REQUEST);
            }
            userService.saveUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok("Registration successful");

    }
}
