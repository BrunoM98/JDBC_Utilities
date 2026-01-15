package bm.contacts.controller;

import bm.contacts.ContactsApplication;
import bm.contacts.entity.Contact;
import bm.contacts.service.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
    @GetMapping("/add")
    public String addContact(){
        return "add";
    }

    @PostMapping("/add")
    public String addC(@ModelAttribute("contactForm") Contact contact){
        logger.info("Contact Add{}", contact);
        contactService.saveContact(contact);
        return "redirect:/"; // redireccionamos al path de inicio
    }
    @GetMapping("/edit/{id}")
    public String editC(@PathVariable(value = "id")int idContact ,ModelMap modelMap){
        Contact contact = contactService.searchContactID(idContact);
        logger.info("Contact Edit{}", contact);
        modelMap.addAttribute("contact",contact);
        return "edit";
    }

}
