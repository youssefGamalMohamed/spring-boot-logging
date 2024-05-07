package org.youssef.gamal.springboot.logging.app.config;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConfig {


    public static Connection getConnection() throws SQLException {

         return DriverManager.getConnection("jdbc:mysql://localhost:3306/logging_db", "root", "1234");

    }
}
