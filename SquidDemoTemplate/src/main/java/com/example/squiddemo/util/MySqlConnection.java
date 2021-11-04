/**
 * @author : 1972008 Adrian Octavius
 */

package com.example.squiddemo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {
    private static final String URL="jdbc:mysql://localhost:3306/squiddb";
    private static final String USERNAME="root";
    private static final String PASSWORD="";

    public   static Connection createConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        connection.setAutoCommit(false);
        return connection;
    }
}
