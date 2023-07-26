package org.ascending.project.controller;

import org.ascending.project.model.User;
import org.ascending.project.service.JWTService;
import org.ascending.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

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
//            String digestPassword = DigestUtils.md2Hex(password.trim());
//            User user = userService.getUserByCredentials(userNameOrEmail, digestPassword);
            User u = userService.getUserByCredentials(user.getEmail(), user.getPassword());
            if (u == null) {
                return ResponseEntity.status(HttpServletResponse.SC_UNAUTHORIZED).build();
            }
            return ResponseEntity.ok().body(jwtService.generateToken(u));
        } catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().build();
    }
}
