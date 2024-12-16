package com.example.erronka;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import  java.sql.*;

public class ConnectDB {
    public class ModifyDB {

    }
    public class InsertDB {

    }
    public class DeleteDB {

    }
    public class QueryDB {

    }
    public static class ConnectionDB {

          Connection connection;

        {
            try {
                connection = DriverManager.getConnection(
                      "jdbc:mysql://localhost:3306/mydb",
                      "root", "1WMG23"
                  );
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


    }
}

