package fit_zone.dates;

import fit_zone.entity.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static fit_zone.connection.Connect.getConnect;

public class ClientDAO implements iClientDAO {

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
            while (rs.next()) {
                //It has to be inside the while loop because it creates a new instance in memory every time it iterates.
                Client client = new Client();
                client.setId(rs.getInt("idclient"));
                client.setName(rs.getString("name"));
                client.setSurName(rs.getString("surname"));
                client.setMember(rs.getInt("member"));
                clients.add(client);
            }
        } catch (Exception e) {
            System.out.println("Error to list users " + e.getMessage());
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("Error to close data base " + e.getMessage());
            }
        }
        for (Client elements: clients){
            System.out.println(elements);
        }
        return clients;
    }

    @Override
    public boolean searchClient(Client client) {
        int id;
        PreparedStatement ps;
        ResultSet rs;
        Connection conn = getConnect();
        String query = "SELECT * FROM client WHERE idclient = ?";
        try {
            ps = conn.prepareStatement(query);
            // El valor de cliente id se introduce sobre ? en la query
            ps.setInt(1, searchIDClient());
            rs = ps.executeQuery();
            if (rs.next()) {
                client.setName(rs.getString("name"));
                client.setSurName(rs.getString("surname"));
                client.setMember(rs.getInt("member"));
                System.out.println(client);
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error to query searchClient " + e.getMessage());
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("Error to close data base " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean insertClient(Client client) {
        Result result = getResult(flag);
        PreparedStatement ps;
        Connection conn = getConnect();
        String query = "INSERT INTO client(name,surname,member)" + " VALUES(?,?,?)";
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, result.names());
            ps.setString(2, result.surnames());
            ps.setInt(3, result.members());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error to insert client " + e.getMessage());
        } finally {
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println("Error to close data base " + e.getMessage());
            }

        }
        return false;

    }

    private Result getResult(boolean flag) {
        String surnames;
        int members = 0;
        String names;
        do {
            while (true) {
                try {
                    System.out.println("Insert client name");
                    names = read.nextLine().trim();
                    if (names.length() < 3) {
                        System.out.println("The length of the name cannot be less than 3");
                    } else if (!names.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ]+")) {
                        throw new IllegalArgumentException("\"The text cannot contain numbers or special characters\"");
                    } else {
                        System.out.println("Client name charge");
                        break;
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("The name cannot contain numbers or symbols " + e.getMessage());
                }
            }
            while (true) {
                try {
                    System.out.println("Insert client surname");
                    surnames = read.nextLine().trim();
                    if (surnames.length() < 3) {
                        System.out.println("The length of the name cannot be less than 3");
                    } else if (!surnames.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ]+")) {
                        throw new IllegalArgumentException("\"The text cannot contain numbers or special characters\"");
                    } else {
                        System.out.println("Client surname charge");
                        break;
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("The name cannot contain numbers or symbols " + e.getMessage());
                }
            }
            while (true) {
                try {
                    System.out.println("Insert member client");
                    members = read.nextInt();
                    List<Integer> membership = memberList();
                    if (members < 100) {
                        System.out.println("The number cannot be less than 100");
                    } else if (membership.contains(members)) {
                        System.out.println("The member number must be different from an existing one");
                    }else{
                        flag = true;
                        break;
                    }
                }catch (InputMismatchException e){
                    System.out.println("The number cannot contain numbers or special characters " + e.getMessage());
                    read.nextLine();
                }
            }
        }while (!flag);
        Result result = new Result(names, members, surnames);
        return result;
    }

    private record Result(String names, int members, String surnames) {
    }

    @Override
    public boolean modifyClient(Client client) {
        Result result = getResult(flag);
        PreparedStatement ps;
        Connection conn = getConnect();
        // name 1 surname 2 member 3 id 4
        String query = "UPDATE client SET name=?, surname=?, member=? " + " WHERE idclient = ?";
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, result.names);
            ps.setString(2, result.surnames);
            ps.setInt(3,result.members);
            ps.setInt(4,searchIDClient());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error to modify client " + e.getMessage());
        }
        finally {
            try {

                conn.close();
            } catch (SQLException e) {
                System.out.println("Error to close data base");
            }
        }
        return false;
    }

    @Override
    public boolean deleteClient(Client client) {
        PreparedStatement ps;
        Connection conn = getConnect();
        String query = "DELETE FROM client WHERE idclient = ?";
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1,searchIDClient());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Error to delete client " + e.getMessage());
        }
        finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("Error to lose data base " + e.getMessage());
            }
        }
        return false;
    }

    private List<Integer> memberList() {
        List<Integer> membership = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Connection conn = getConnect();
        String query = "SELECT member FROM client ";
        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()){
                Client client = new Client();
                client.setMember(rs.getInt("member"));
                membership.add(client.getMember());
            }
        } catch (SQLException e) {
            System.out.println("Error to list members " + e.getMessage());
        }
        finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Error to close data base " + e.getMessage());
            }
        }
        return membership;
    }

    private Integer searchIDClient(){
        int id = 0;
        while (true) {
            try {
                System.out.println("Enter the ID of the client");
                id = read.nextInt();
                List<Integer> clientID = listID();
                if (id <= 0) {
                    System.out.println("The id cannot be negative or 0");
                } else if (!clientID.contains(id)) {
                    System.out.println("The client ID does not exist.");
                } else {
                    System.out.println("The process worked successfully.");
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("The number cannot contain numbers or special characters " + e.getMessage());
                read.nextLine();
            }
        }
        return id;
    }
    
    private List<Integer> listID(){
        List<Integer> clientIDS = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Connection conn = getConnect();
        String query = "SELECT idclient FROM client";
        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()){
                Client clients = new Client();
                clients.setId(rs.getInt("idclient"));
                clientIDS.add(clients.getId());
            }
        } catch (SQLException e) {
            System.out.println("Error to list ID");
        }
        finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Error to close data base " +e.getMessage());
            }
        }
        return clientIDS;
    }

    public void goodBye(){
        int rows = 7;
        int cols = 17;

        char[][] matrix = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = ' ';
            }
        }

        for (int i = 0; i < rows; i++) {
            matrix[i][0] = '*';
            if (i == 0 || i == 3 || i == 6) {
                for (int j = 1; j <= 3; j++) {
                    matrix[i][j] = '*';
                }
            }
            if (i == 1 || i == 2 || i == 4 || i == 5) {
                matrix[i][3] = '*';
            }
        }

        for (int i = 0; i < 4; i++) {
            matrix[i][6] = '*';
            matrix[i][10] = '*';
        }
        for (int i = 4; i < rows; i++) {
            matrix[i][8] = '*';
        }

        for (int i = 0; i < rows; i++) {
            matrix[i][13] = '*';
            if (i == 0 || i == 3 || i == 6) {
                for (int j = 14; j <= 16; j++) {
                    matrix[i][j] = '*';
                }
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }
    private static void waitPrint(String s) {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.err.println("The pause was interrupted: " + e.getMessage());
        }
        System.out.print(s);
    }
}


