package bm.contacts.controller;

import bm.contacts.entity.Contact;
import bm.contacts.service.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ContactsController {

    private static final Logger logger = LoggerFactory.getLogger(ContactsController.class);

    private final ContactService contactService;

    public ContactsController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/")
    public String init(ModelMap modelMap){
        List<Contact> contactLis = contactService.contactList();
        contactLis.forEach((contact -> logger.info(contact.toString())));
        modelMap.put("contacts", contactLis);
        return "index"; //index.html
    }
}
