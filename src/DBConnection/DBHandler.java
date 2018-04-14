package DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHandler extends Configs {

    Connection dbconnection;

    public Connection getDbconnection() throws ClassNotFoundException {

        String connectionString = "jdbc:mysql://" + Configs.dbhost + ":" + Configs.dbport + "/" + Configs.dbname + "?autoReconnect=true&useSSL=false";
        Class.forName("com.jdbc.mysql.Driver");

        try {
            dbconnection = DriverManager.getConnection(connectionString, Configs.dbuser, Configs.dbpassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dbconnection;
    }
}
