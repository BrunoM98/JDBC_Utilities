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
        return clients;
    }

    @Override
    public boolean searchClient(Client client) {
        PreparedStatement ps;
        ResultSet rs;
        Connection conn = getConnect();
        String query = "SELECT * FROM client WHERE idclient = ?";
        try {
            ps = conn.prepareStatement(query);
            // El valor de cliente id se introduce sobre ? en la query
            ps.setInt(1, client.getId());
            rs = ps.executeQuery();
            if (rs.next()) {
                client.setName(rs.getString("name"));
                client.setSurName(rs.getString("surname"));
                client.setMember(rs.getInt("member"));
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
        String names = "";
        boolean flag = false;
        int members;
        String surnames = "";
        Result result = getResult(flag,client);
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

    private Result getResult(boolean flag, Client client) {
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
                    if (members < 100) {
                        System.out.println("The number cannot be less than 100");
                    } else if (members != client.getMember()) {
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
        PreparedStatement ps;
        Connection conn = getConnect();
        // name 1 surname 2 member 3 id 4
        String query = "UPDATE client SET name=?, surname=?, member=? " + " WHERE idclient = ?";
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, client.getName());
            ps.setString(2, client.getSurName());
            ps.setInt(3,client.getMember());
            ps.setInt(4,client.getId());
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
            ps.setInt(1,client.getId());
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

}
