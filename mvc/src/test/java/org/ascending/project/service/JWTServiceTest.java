package org.ascending.project.service;

import io.jsonwebtoken.Claims;
import org.ascending.project.ApplicationBootstrap;
import org.ascending.project.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBootstrap.class)
public class JWTServiceTest {

    @Autowired
    private JWTService jwtService;

    @Test
    public void testGenerateToken(){

        User u = new User();
        u.setId(1);
        u.setName("Wenzhe");


        String token = jwtService.generateToken(u);
        String[] array = token.split("\\.");
        boolean bool = array.length == 3 ? true:false;
        assertTrue(bool);
    }

    @Test
    public void decryptTokenTest(){

        User u = new User();
        u.setId(1L);
        u.setName("Wenzhe");
        String token = jwtService.generateToken(u);
        Claims c = jwtService.decryptToken(token);
        String userName = c.getSubject();

        assertEquals(u.getName(), userName);
    }

}
