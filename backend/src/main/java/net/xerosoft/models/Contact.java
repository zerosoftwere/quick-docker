package net.xerosoft.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Contact extends PanacheEntity {
    @Column(nullable = false)
    public String name;

    @Column(nullable = false)
    public String phone;

    @Column
    public String email;

    @ManyToOne
    @JsonIgnore
    public User user;

    public static Contact findById(User user, Long id) {
        return find("user = ?1 and id = ?2", user, id).firstResult();
    }

    public static List<Contact> findByName(User user, String name) {
        return find("user = ?1 and name like concat('%', lower(?2), '%')", user, name).list();
    }
}
