package fit_zone.dates;

import fit_zone.connection.Connect;
import fit_zone.entity.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static fit_zone.connection.Connect.getConnect;

public class ClientDAO implements iClientDAO{

    Scanner read = new Scanner(System.in);
    boolean flag = false;

    @Override
    public List<Client> clientList() {
        List<Client> clients = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Connection conn = getConnect();
        String query = "SELECT * FROM client ORDER BY idclient";
        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()){
                //It has to be inside the while loop because it creates a new instance in memory every time it iterates.
                Client client = new Client();
                client.setId(rs.getInt("idclient"));
                client.setName(rs.getString("name"));
                client.setSurName(rs.getString("surname"));
                client.setMemberNumber(rs.getInt("member"));
                clients.add(client);
            }
        } catch (Exception e) {
            System.out.println("Error to list users " + e.getMessage());
        }
        finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("Error to close data base " + e.getMessage());
            }
        }
        return clients;
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
