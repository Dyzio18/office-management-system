package sample;

import java.sql.*;

public class LoginModel
{
    Connection connection;
    public LoginModel()
    {
        connection = SqliteConnection.Connector();
        if(connection==null)
            System.exit(1);
    }

    public boolean isDbConnected()
    {
        try {
            return !connection.isClosed();
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
