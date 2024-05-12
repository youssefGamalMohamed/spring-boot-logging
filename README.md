# Implement Logging for Log4j2 with Different Appenders in Spring Boot 

This project demonstrates the implementation of logging using Log4j2 with three different appenders: console, file, and JDBC.

## Branches

- **console-logger**: Implements logging to the console.
- **jdbc-logger**: Implements logging to a JDBC database.
- **file-logger**: Implements logging to a file.

## Configuration

### console-logger Branch

To use the console appender, follow these steps:

1. **Log Pattern Configuration**: Define the `LOG_PATTERN` parameter in the `log4j2.xml` configuration file to determine the pattern of how logs will be formatted in the console.

2. **Console Appender Configuration**: Add the following configuration to the `log4j2.xml` file to set up the console appender:

    ```xml
    <Console name="Console" target="SYSTEM_OUT" follow="true">
        <PatternLayout pattern="${LOG_PATTERN}" />
    </Console>
    ```

    This configuration includes:
    - `target`: Specifies the target for logging output. In this case, it's set to `SYSTEM_OUT` to log to the console.
    - `PatternLayout`: Defines the pattern for formatting log messages in the console.

   Ensure that the `LOG_PATTERN` parameter is defined in the `log4j2.xml` file with the desired logging pattern.

---

### jdbc-logger Branch

To use the JDBC appender, follow these steps:

1. **Log Pattern Configuration**: Define the `LOG_PATTERN` parameter in the `log4j2.xml` configuration file to determine the pattern of how logs will be formatted in the console.

2. **JDBC Connection Configuration**: Ensure that you have configured the database connection in the `application.yml` file. Make sure to provide the necessary database connection details such as URL, username, and password.

3. **Java Classes for Connection Management**: You need to include two important Java classes for connection creation and management using Hikari connection pooling:
   - `JdbcConfig`: This class will handle the configuration for JDBC connections.
   - `JdbcHikariConfig`: This class will manage the Hikari connection pooling.

4. **Schema Definition**: Make sure you have a `schema.sql` file to create the necessary table for logging. The table should be named `EVENT_LOGS` and should include columns for logging relevant information.

    ```sql
    CREATE TABLE EVENT_LOGS (
        ID VARCHAR(36) PRIMARY KEY,
        DATE_TIME TIMESTAMP,
        CLASS VARCHAR(255),
        LEVEL VARCHAR(10),
        MESSAGE VARCHAR(4000),
        EXCEPTION CLOB
    );
    ```

5. **Log4j2 JDBC Appender Configuration**: In the `log4j2.xml` configuration file, add the JDBC appender configuration. Here's an example configuration:

    ```xml
    <JDBC name="JDBCLogger" tableName="EVENT_LOGS">
        <ConnectionFactory class="org.youssef.gamal.springboot.logging.app.config.JdbcConfig" method="getConnection" />
        <Column name="ID" pattern="%u" /> <!-- unique identifier as UUID -->
        <Column name="DATE_TIME" isEventTimestamp="true" />
        <Column name="CLASS" pattern="%logger" /> <!-- full class name with the package -->
        <Column name="LEVEL" pattern="%level" />
        <Column name="MESSAGE" pattern="%message" />
        <Column name="EXCEPTION" pattern="%ex{full}" isClob="true"/>
    </JDBC>
    ```

   Ensure to replace `org.youssef.gamal.springboot.logging.app.config.JdbcConfig` with the correct package and class name for your `JdbcConfig` class.

---

### file-logger Branch

To use the file appender, follow these steps:

1. **Log Pattern Configuration**: Define the `LOG_PATTERN` parameter in the `log4j2.xml` configuration file to determine the pattern of how logs will be formatted in the log files.

2. **Log Path Configuration**: Set the `LOG_PATH` parameter in the `log4j2.xml` configuration file to specify the directory where log files will be saved.

3. **File Appender Configuration**: Add the following configuration to the `log4j2.xml` file to set up the file appender:

    ```xml
    <RollingFile name="FileLogger"
                     fileName="${LOG_PATH}/today_log.log"
                     filePattern="${LOG_PATH}/%d{YYYY-MM}/log.%d{dd-MMM}-%i.log">
        <PatternLayout pattern="${LOG_PATTERN}" />
        <Policies>
            <!-- Policy 1: Size Based Triggering -->
            <SizeBasedTriggeringPolicy size="10MB" /> <!-- if the file size exceeds 10MB, it creates a new file -->
            
            <!-- Policy 2: Time Based Triggering -->
            <TimeBasedTriggeringPolicy interval="1"/> <!-- creates a new folder every day -->
        </Policies>
    </RollingFile>
    ```

    This configuration includes policies for triggering log file rollover based on size and time, listed as separate points. Adjust the values of `size` and `interval` attributes according to your requirements.

## Getting Started

Follow these steps to get started with the project:

1. Clone the repository.
2. Checkout the desired branch (`console-logger`, `jdbc-logger`, or `file-logger`).
3. Review the `log4j2.xml` configuration file in the `resources` folder for branch-specific configurations.
4. Run the project and observe the logging behavior based on the chosen branch.

## Contributions

Contributions are welcome! If you have any ideas for improvement or find any issues, feel free to open an issue or create a pull request.
