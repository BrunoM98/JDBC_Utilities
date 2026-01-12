package bm.contacts.repository;

import bm.contacts.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepositoryContact extends JpaRepository<Contact, Integer> {


}
