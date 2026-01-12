package bm.contacts.service;

import bm.contacts.entity.Contact;
import bm.contacts.repository.IRepositoryContact;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ContactService implements IContactService{


    //“El uso de @Autowired sobre interfaces es correcto,
    // pero se recomienda constructor injection para mayor testabilidad y claridad de dependencias.”

    private final IRepositoryContact iRepositoryContact;

    public ContactService(IRepositoryContact iRepositoryContact) {
        this.iRepositoryContact = iRepositoryContact;
    }

    @Override
    public List<Contact> contactList() {
        return iRepositoryContact.findAll();
    }

    @Override
    public Contact searchContactID(Integer idContact) {

        return iRepositoryContact.findById(idContact).
                orElseThrow(() -> new RuntimeException("Contact not Exist"));

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
