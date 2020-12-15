package net.xerosoft.services;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.NotFoundException;

import net.xerosoft.models.Contact;
import net.xerosoft.models.User;

@ApplicationScoped
public class ContactService {
    public Contact create(User user, String name, String phone, String email) {
        Contact contact = new Contact();
        contact.name = name;
        contact.email = email;
        contact.phone = phone;

        contact.persist();
        return contact;
    }

    public Contact update(User user, Long id, String name, String phone, String email) {
        Contact contact = findById(user, id);
        contact.name = name;
        contact.email = email;
        contact.phone = phone;
        
        contact.persist();
        return contact;
    }

    public void delete(User user, Long id) {
        findById(user, id).delete();
    }

    public Contact findById(User user, Long id) {
        Optional<Contact> contact = Contact.findByIdOptional(id);
        return contact.orElseThrow(NotFoundException::new);
    }
}
