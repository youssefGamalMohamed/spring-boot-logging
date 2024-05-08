package org.youssef.gamal.springboot.logging.app.config;



import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcConfig {


    public static int count = 0;
    private static HikariConfig hikariConfig;
    private static HikariDataSource hikariDataSource;

    public static Connection getConnection() throws SQLException {
        if(hikariConfig == null) {
            hikariConfig = new HikariConfig();
            hikariConfig.setJdbcUrl( "jdbc:mysql://localhost:3306/logging_db" );
            hikariConfig.setUsername( "root" );
            hikariConfig.setPassword( "1234" );
            hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
        }
        if(hikariDataSource == null) {
            hikariDataSource = new HikariDataSource(hikariConfig);
            count++;
            System.out.println("A new Connection Created..... | count = " + count);
        }
        else {
            System.out.println("A Connection Reused..... | " + count);
        }
        return hikariDataSource.getConnection();
    }
}
