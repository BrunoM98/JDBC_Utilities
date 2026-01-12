package bm.contacts.service;

import bm.contacts.entity.Contact;

import java.util.List;

public interface IContactService {

    public List<Contact> contactList();

    public Contact searchContactID(Integer idContact);

    public void saveContact(Contact contact);

    public void deleteContact(Contact contact);
}
