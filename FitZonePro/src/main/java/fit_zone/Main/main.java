package fit_zone.Main;

import fit_zone.dates.ClientDAO;
import fit_zone.dates.iClientDAO;
import fit_zone.entity.Client;

import java.util.List;

public class main {
    public static void main(String[] args) {
        Client client = new Client(3);
        iClientDAO clientDAO = new ClientDAO();

        System.out.println("List Clients");
        List<Client> cli = clientDAO.clientList();
        cli.forEach(System.out::println);

        System.out.println("Search client for id");
        System.out.println(client);
        clientDAO.searchClient(client);
        boolean found = clientDAO.searchClient(client);
        if (found){
            System.out.println("Client Founded " + client);
        }else{
            System.out.println("Client Not Found " + client.getId());
        }
    }
}
