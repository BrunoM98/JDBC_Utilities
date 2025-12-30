package bm.fit_zone.gui;

import bm.fit_zone.Service.ClientService;
import bm.fit_zone.Service.IClientService;
import bm.fit_zone.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

@Component
public class ZoneFitForm extends JFrame {

    private JPanel mainPanel;
    private JLabel label1;
    private JTable clientTable;
    private JTextField textName;
    private JTextField surNameText;
    private JTextField memberText;
    private JButton saveButton;
    private JButton deleteButton;
    private JButton clearButton;
    private DefaultTableModel clintTableModel;
    IClientService clientService;

    @Autowired
    public ZoneFitForm(ClientService clientService){
        this.clientService = clientService;
        startForm();
        saveButton.addActionListener(e -> saveClient());

    }

    private void startForm(){
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900,700);
        setLocationRelativeTo(null);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        this.clintTableModel = new DefaultTableModel(0, 4);
        String[] headLine = {"Id" , "Name" , "Surname" , "Membership"};
        this.clintTableModel.setColumnIdentifiers(headLine);
        this.clientTable = new JTable(clintTableModel);
        // Charge Client List
        clientList();
    }

    private void clientList(){
        this.clintTableModel.setRowCount(0);
        List<Client> clients = this.clientService.clientList();
        clients.forEach(client -> {
            Object[] clientRong = {
                    client.getIdclient(),
                    client.getName(),
                    client.getSurname(),
                    client.getMember()
            };
            this.clintTableModel.addRow(clientRong);
        });
    }

    private void saveClient(){
        if(textName.getText().equals("")){
            wacthMassage("Please provide a name.");
            textName.requestFocusInWindow();
            return;
        }
    }

    private void wacthMassage(String message){
        JOptionPane.showMessageDialog(this, message);
    }
}
