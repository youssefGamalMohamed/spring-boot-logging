package org.youssef.gamal.springboot.logging.app.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class JdbcHikariConfig {

    private static HikariConfig hikariConfig;
    private static HikariDataSource hikariDataSource;

    public static HikariConfig getHikariConfig() {
        if(hikariConfig == null) {
            hikariConfig = new HikariConfig();
            hikariConfig.setJdbcUrl( "jdbc:mysql://localhost:3306/logging_db" );
            hikariConfig.setUsername( "root" );
            hikariConfig.setPassword( "1234" );
            hikariConfig.setDriverClassName( "com.mysql.cj.jdbc.Driver" );
            hikariConfig.setMaximumPoolSize(5);
        }
        return hikariConfig;
    }

    public static HikariDataSource getHikariDataSource() {
        hikariConfig = getHikariConfig();
        if(hikariDataSource == null) {
            hikariDataSource = new HikariDataSource(hikariConfig);
        }
        return hikariDataSource;
    }
}
