package org.ascending.project.controller;

import org.ascending.project.model.User;
import org.ascending.project.service.JWTService;
import org.ascending.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/auth"})
public class AuthController {

    @Autowired
    private JWTService jwtService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity userLogin(@RequestBody User user){

        try{
            User u = userService.getUserByCredentials(user.getEmail(), user.getPassword());
            if (u == null) {

//    one way:  return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).build();
                // another way:
                return new ResponseEntity<>("401 Unauthorized", HttpStatus.UNAUTHORIZED);
            }
            return ResponseEntity.ok().body(jwtService.generateToken(u));
        } catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().build();
    }
}
