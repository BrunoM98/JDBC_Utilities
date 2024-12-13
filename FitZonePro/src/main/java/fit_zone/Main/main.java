package fit_zone.Main;

import fit_zone.dates.ClientDAO;
import fit_zone.dates.iClientDAO;
import fit_zone.entity.Client;

import java.util.List;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Client client = new Client();
        iClientDAO clientDAO = new ClientDAO();
        Scanner read= new Scanner(System.in);
        int option;

        do {
            System.out.println("Zone Fit Menu");
            System.out.println("Options");
            System.out.println("1: List all client");
            System.out.println("2: Search one client for id");
            System.out.println("3: Insert new client");
            System.out.println("4: Modify client for id");
            System.out.println("5: Delete client");
            System.out.println("6: Exit");
            option = Integer.parseInt(read.nextLine().trim());

            switch (option){
                case 1:
                    clientDAO.clientList();
                    break;
                case 2:
                    clientDAO.searchClient(client);
                    break;
                case 3:
                    clientDAO.insertClient(client);
                    break;
                case 4:
                    clientDAO.modifyClient(client);
                case 5 :
                    clientDAO.deleteClient(client);
                    break;
                case 6:
                    System.out.println("Exit application");
                    break;
                default:
                    System.out.println("Incorrect number");
                    break;
            }
        }while (option != 6);

//        boolean adde = clientDAO.deleteClient(client3);
//        if (adde){
//            System.out.println("Delete client " + client3);
//        }else{
//            System.out.println("Wasn’t delete client " + client3);
//        }

//        System.out.println("List Clients");
//        List<Client> cli = clientDAO.clientList();
//        cli.forEach(System.out::println);

        System.out.println("Search client for id");
        System.out.println(client);
//        clientDAO.searchClient(client);
        boolean found = clientDAO.searchClient(client);
        if (found){
            System.out.println("Client Founded " + client);
        }else{
            System.out.println("Client Not Found " + client.getId());
        }

//       Boolean added = clientDAO.insertClient(client1);
//        if(added){
//            System.out.println("Client inserted successfully " + client1);
//        }else{
//            System.out.println("Client wasn’t added. " + client1);
//        }
//        boolean addeds = clientDAO.modifyClient(client2);
//        if (addeds){
//            System.out.println("Client modified" + client2);
//        }else{
//            System.out.println("Client not modify: " + client2);
//        }



    }
}
