package fit_zone.connection;
import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {
    public static Connection getConnect(){
        Connection connec = null;
        String dataBase = "zone_fit_db";
        String url = "jdbc:mysql://localhost:3306/" + dataBase;
        String user = "root";
        String password = "admin";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connec = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println("Error upload data base " + e.getMessage());
        }
        return connec;
    }

    public static void main(String[] args) {
        var connect = Connect.getConnect();
        if (connect != null){
            System.out.println("Connection whit data base " + connect);
        }else{
            System.out.println("Error to connect whit data base");
        }
    }
}
