package fit_zone.dates;

import fit_zone.entity.Client;

import java.util.List;

public interface iClientDAO {

    List<Client> clientList();
    boolean searchClient(Client client);
    boolean insertClient(Client client);
    boolean modifyClient(Client client);
    boolean deleteClient(Client client);
}
