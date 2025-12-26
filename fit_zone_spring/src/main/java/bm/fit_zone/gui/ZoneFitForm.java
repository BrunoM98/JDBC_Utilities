package bm.fit_zone.gui;

import bm.fit_zone.Service.ClientService;
import bm.fit_zone.Service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
public class ZoneFitForm extends JFrame {
    private JPanel mainPanel;
    private JLabel label1;
    IClientService clientService;

    @Autowired
    public ZoneFitForm(ClientService clientService){
        this.clientService = clientService;
        startForm();
    }

    private void startForm(){
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900,700);
        setLocationRelativeTo(null);
    }

}
