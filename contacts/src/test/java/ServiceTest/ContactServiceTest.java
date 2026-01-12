package ServiceTest;

import bm.contacts.entity.Contact;
import bm.contacts.repository.IRepositoryContact;
import bm.contacts.service.ContactService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

    @ExtendWith(MockitoExtension.class)
    class ContactServiceTest {

        @Mock
        private IRepositoryContact repository;

        @InjectMocks
        private ContactService contactService;

        @Test
        void shouldReturnContactList() {
            when(repository.findAll()).thenReturn(List.of(new Contact()));

            List<Contact> result = contactService.contactList();

            assertEquals(1, result.size());
        }
    }
}
