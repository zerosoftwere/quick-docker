package net.xerosoft.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
public class Admin extends PanacheEntityBase {

    @Id
    public String username;

    @JsonIgnore
    @Column(nullable = false)
    public String password;

    @Column(nullable = false)
    public String role;

    public static Admin findByUsername(String username) {
        return find("username", username).firstResult();
    }
}
