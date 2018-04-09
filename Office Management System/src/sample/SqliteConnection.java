package sample;

import java.sql.*;

public class SqliteConnection
{
    public static Connection Connector()
    {
        try
        {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:EmployeeDb.sqlite");
            // albo bez ścieżki i wrzucić baze do projektu
            return conn;
        }
        catch (Exception e)
        {
            System.out.println(e);
            return null;
        }
    }
}
