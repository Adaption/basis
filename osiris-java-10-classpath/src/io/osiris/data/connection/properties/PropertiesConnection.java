package io.osiris.data.connection.properties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PropertiesConnection {
    private static final PropertiesReader CONNECTION_PROPERTIES = PropertiesReader.INSTANCE;

    public static Connection openConnection() throws ClassNotFoundException, SQLException {

        Class.forName(CONNECTION_PROPERTIES.getDriverClass());

        return DriverManager.getConnection(
                CONNECTION_PROPERTIES.getUrl(),
                CONNECTION_PROPERTIES.getUsername(),
                CONNECTION_PROPERTIES.getPassword()
        );
    }
}
