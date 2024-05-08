package org.youssef.gamal.springboot.logging.app.config;


import java.sql.Connection;
import java.sql.SQLException;

public class JdbcConfig {

    public static Connection getConnection() throws SQLException {
        Connection connection = JdbcHikariConfig.getHikariDataSource().getConnection();
        return connection;
    }
}
