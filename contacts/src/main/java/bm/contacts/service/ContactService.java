package bm.contacts.service;

import bm.contacts.entity.Contact;
import bm.contacts.repository.IRepositoryContact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ContactService implements IContactService{

    @Autowired
    private IRepositoryContact iRepositoryContact;

    @Override
    public List<Contact> contactList() {
        return iRepositoryContact.findAll();
    }

    @Override
    public Contact searchContactID(Integer idContact) {

        return iRepositoryContact.findById(idContact).orElse(null);

    }

    @Override
    public void saveContact(Contact contact) {
        iRepositoryContact.save(contact);
    }

    @Override
    public void deleteContact(Contact contact) {
        iRepositoryContact.delete(contact);
    }
}
