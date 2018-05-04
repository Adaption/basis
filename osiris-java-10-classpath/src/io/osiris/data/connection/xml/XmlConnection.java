package io.osiris.data.connection.xml;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class XmlConnection {

    private static final XmlReader CONNECTION_PROPERTIES = XmlReader.INSTANCE;

    public static Connection openConnection() throws ClassNotFoundException, SQLException {

        Class.forName(CONNECTION_PROPERTIES.getDriverClass());

        return DriverManager.getConnection(
                CONNECTION_PROPERTIES.getUrl(),
                CONNECTION_PROPERTIES.getUsername(),
                CONNECTION_PROPERTIES.getPassword()
        );
    }
}
