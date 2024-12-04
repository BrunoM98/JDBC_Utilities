package fit_zone.dates;

import fit_zone.entity.Client;

import java.util.List;

public class ClientDAO implements iClientDAO{
    @Override
    public List<Client> clientList() {
        return List.of();
    }

    @Override
    public boolean searchClient(Client client) {
        return false;
    }

    @Override
    public boolean insertClient(Client client) {
        return false;
    }

    @Override
    public boolean modifyClient(Client client) {
        return false;
    }

    @Override
    public boolean deleteClient(Client client) {
        return false;
    }
}
