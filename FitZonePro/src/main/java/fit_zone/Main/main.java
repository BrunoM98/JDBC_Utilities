package fit_zone.Main;

import fit_zone.dates.ClientDAO;
import fit_zone.dates.iClientDAO;
import fit_zone.entity.Client;

import java.util.List;

public class main {
    public static void main(String[] args) {
        Client client = new Client();
        Client client1 = new Client();
        Client client2 = new Client();
        Client client3 = new Client();
        iClientDAO clientDAO = new ClientDAO();


        System.out.println("Search client for id");
        System.out.println(client);
        clientDAO.searchClient(client);
        boolean found = clientDAO.searchClient(client);
        if (found){
            System.out.println("Client Founded " + client);
        }else{
            System.out.println("Client Not Found " + client.getId());
        }

       Boolean added = clientDAO.insertClient(client1);
        if(added){
            System.out.println("Client inserted successfully " + client1);
        }else{
            System.out.println("Client wasn’t added. " + client1);
        }
        boolean addeds = clientDAO.modifyClient(client2);
        if (addeds){
            System.out.println("Client modified" + client2);
        }else{
            System.out.println("Client not modify: " + client2);
        }

        boolean adde = clientDAO.deleteClient(client3);
        if (adde){
            System.out.println("Delete client " + client3);
        }else{
            System.out.println("Wasn’t delete client " + client3);
        }


        System.out.println("List Clients");
        List<Client> cli = clientDAO.clientList();
        cli.forEach(System.out::println);

    }
}
