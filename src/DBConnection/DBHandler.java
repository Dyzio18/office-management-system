package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHandler extends Configs {

    private Connection dbConnection;

    public Connection getConnection() {

        String connectionString = "jdbc:mysql://" + Configs.dbhost + ":" + Configs.dbport + "/" + Configs.dbname;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            dbConnection = DriverManager.getConnection(connectionString, Configs.dbuser, Configs.dbpassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dbConnection;
    }
}
