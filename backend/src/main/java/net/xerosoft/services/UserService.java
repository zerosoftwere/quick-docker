package net.xerosoft.services;

import javax.enterprise.context.ApplicationScoped;

import net.xerosoft.models.User;

@ApplicationScoped
public class UserService {
    public User create(String name, String email, String password) {
        User user = new User();
        user.email = email;
        user.name = name;
        user.role = "user";
        user.password = password; //TODO: bcrypt the passwrod
        user.persist();
        
        return user;
    }

    public User update(User user, String name) {
        user.name = name;
        user.persist();
        return user;
    }

    public User changePassword(User user, String password) {
        user.password = password;
        user.persist();
        return user;
    }
}
