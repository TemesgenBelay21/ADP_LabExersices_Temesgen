package Server;

import java.sql.*;

public class DatabaseManager {

    private static final String URL =
            "jdbc:mysql://localhost:3306/chatapp";

    private static final String USER = "root";
    private static final String PASSWORD = "";

    private Connection connection;

    public DatabaseManager() {
        connect();
    }

    private void connect() {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(
                    URL,
                    USER,
                    PASSWORD
            );

            System.out.println("Database Connected");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean authenticate(String username, String password) {

        try {

            String sql =
                    "SELECT * FROM users WHERE username=? AND password=?";

            PreparedStatement ps =
                    connection.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}