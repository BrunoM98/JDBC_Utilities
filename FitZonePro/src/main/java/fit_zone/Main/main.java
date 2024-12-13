package fit_zone.Main;

import fit_zone.dates.ClientDAO;
import fit_zone.dates.iClientDAO;
import fit_zone.entity.Client;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Client client = new Client();
        iClientDAO clientDAO = new ClientDAO();
        Scanner read= new Scanner(System.in);
        int option = 0;

        do {
            try {
                System.out.println("Zone Fit Menu");
                System.out.println("Options");
                System.out.println("1: List all client");
                System.out.println("2: Search one client for id");
                System.out.println("3: Insert new client");
                System.out.println("4: Modify client for id");
                System.out.println("5: Delete client");
                System.out.println("6: Exit");

                String optionSTR = read.nextLine().trim();

                if (!optionSTR.matches("\\d+")) {
                    throw new IllegalArgumentException("The input must be a valid number without spaces or special characters.");
                }
                option = Integer.parseInt(optionSTR);

                switch (option) {
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
                    case 5:
                        clientDAO.deleteClient(client);
                        break;
                    case 6:
                        System.out.println("Exit application");
                        break;
                    default:
                        System.out.println("The number is not an option");
                        break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("The option cannot contain special characters or letters.");
            }
        }while (option != 6);
    }
}
