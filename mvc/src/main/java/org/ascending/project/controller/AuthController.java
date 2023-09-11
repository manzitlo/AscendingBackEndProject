package org.ascending.project.controller;

import org.ascending.project.model.User;
import org.ascending.project.service.JWTService;
import org.ascending.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = {"/auth"})
public class AuthController {

    @Autowired
    private JWTService jwtService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity userLogin(@RequestBody User user){


        // check which one is not null and use it.
        String identifier = (user.getEmail() != null && !user.getEmail().isEmpty()) ? user.getEmail() : user.getUsername();

        try{
            User u = userService.getUserByCredentials(identifier, user.getPassword());
            if (u == null) {
                return new ResponseEntity<>("401 Unauthorized", HttpStatus.UNAUTHORIZED);
            }
            return ResponseEntity.ok().body(jwtService.generateToken(u));
        } catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().build();
    }
}
