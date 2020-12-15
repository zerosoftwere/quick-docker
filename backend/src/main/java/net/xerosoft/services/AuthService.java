package net.xerosoft.services;

import java.util.Collections;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;

import net.xerosoft.models.User;
import org.eclipse.microprofile.jwt.JsonWebToken;

import io.smallrye.jwt.build.Jwt;

@RequestScoped
public class AuthService {
    
    @Inject
    JsonWebToken jwt;

    @Inject
    UserService userService;

    public User user() {
        Long id = Long.parseLong(jwt.getSubject());
        User user = User.findById(id);
        return user;
    }

    public String authenticate(String email, String password) {
        User user = User.findByEmail(email);
        if (user == null || !password.equals(user.password))
            throw new BadRequestException();
        return sign(user);
    }

    public String register(String name, String email, String phone, String password) {
        //TODO: Check that the email is not registered;
        User user = userService.create(name, email, password);
        return sign(user);
    }

    private String sign(User user) {
        String token = Jwt.issuer("http://todo.xerosoft.net/issuer") 
                            .subject(user.id.toString())
                            .upn(user.email)
                            .groups(Collections.singleton(user.role))
                            .sign();
        return token;
    }
}
