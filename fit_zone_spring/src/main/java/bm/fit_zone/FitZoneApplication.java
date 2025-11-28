package bm.fit_zone;

import bm.fit_zone.menu.Menu;
import bm.fit_zone.model.Client;
import org.slf4j.Logger;
import bm.fit_zone.Service.IClientService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class FitZoneApplication implements CommandLineRunner {


    @Autowired
    private IClientService clientService;

    private static final Logger logger = LoggerFactory.getLogger(FitZoneApplication.class);

    String nl = System.lineSeparator();

    public static void main(String[] args) {
        Menu menu = new Menu();

        logger.info("Initializing project");
        SpringApplication.run(FitZoneApplication.class, args);
        logger.info("Finish Application");

    }
    @Override
    public void run(String... args) throws Exception {
        zonafitAPP();
    }
    private void zonafitAPP(){
        boolean out = false;
        Scanner read = new Scanner(System.in);
        while (!out){
            int option = showMenu(read);
            out = executeOption(read, option);
            logger.info(nl);

        }
    }
    private int showMenu(Scanner read){
        logger.info(nl + """
            Fit Zone Application >D
            1: Client List
            2: Search Client
            3: Save Client
            4: Modify Client
            5: Delete Client;
            6: Exit
            Choose One Option:\s""");

        return Integer.parseInt(read.nextLine());
    }

    private boolean executeOption(Scanner read, int option){
        boolean out = false;
        switch (option){
            case 1:
                logger.info(nl + "Client List " + nl);

                List<Client> clients = clientService.clientList();

                clients.forEach(client -> logger.info("{}{}", client.toString(), nl));
        }
        return out;
    }

    }

