package org.ascending.project.controller;

import io.jsonwebtoken.Claims;
import org.ascending.project.model.User;
import org.ascending.project.service.JWTService;
import org.ascending.project.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JWTService jwtService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id,
                                           @RequestParam("password") String password,
                                           @RequestParam(value = "firstName", required=false) String firstName,
                                           @RequestParam(value = "lastName", required=false) String lastName,
                                           @RequestHeader("Authorization") String token){

        Claims claims;
        try {
            claims = jwtService.decryptToken(token);
        } catch (Exception e) {
            logger.error("Error decrypting token.", e);
            return new ResponseEntity<>("Invalid token.", HttpStatus.UNAUTHORIZED);
        }

        Long currentUserId = Long.parseLong(claims.getId());

        if (!currentUserId.equals(id)) {
            logger.warn("User {} tried to update user {}. Forbidden.", currentUserId, id);
            return new ResponseEntity<>("Forbidden. Users can only update their own account.", HttpStatus.FORBIDDEN);
        }

        logger.info("Updating user with ID: {} and details(password, firstName, lastName): {},{},{}", id, password, firstName, lastName);
        User u = userService.getUserById(id);

        if (u == null) {
            logger.warn("User with ID {} not found", id);
            return new ResponseEntity<>("User with ID: " + id + " not found", HttpStatus.NOT_FOUND);
        }

        logger.info("Updating user with ID: {}. Changing first name to: {}, last name to: {}", id, firstName, lastName);

        u.setPassword(password);
        if (firstName != null) u.setFirstName(firstName);
        if (lastName != null) u.setLastName(lastName);

        try {
            u = userService.update(u);
        } catch (Exception e) {
            logger.error("Error updating user with ID: {}", id, e);
            return new ResponseEntity<>("Error updating user.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable("id") Long id,
                                         @RequestHeader("Authorization") String token) {

        Claims claims;
        try {
            claims = jwtService.decryptToken(token);
        } catch (Exception e) {
            logger.error("Error decrypting token.", e);
            return new ResponseEntity<>("Invalid token.", HttpStatus.UNAUTHORIZED);
        }

        Long currentUserId = Long.parseLong(claims.getId());

        if (!currentUserId.equals(id)) {
            logger.warn("User {} tried to delete user {}. Forbidden.", currentUserId, id);
            return new ResponseEntity<>("Forbidden. Users can only delete their own account.", HttpStatus.FORBIDDEN);
        }

        User user = userService.getUserById(id);
        if (user == null) {
            logger.warn("User with ID {} not found", id);
            return new ResponseEntity<>("User with ID: " + id + " not found", HttpStatus.NOT_FOUND);
        }

        try {
            userService.delete(user);
        } catch (Exception e) {
            logger.error("Error deleting user with ID: {}", id, e);
            return new ResponseEntity<>("Error deleting user.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }

}
