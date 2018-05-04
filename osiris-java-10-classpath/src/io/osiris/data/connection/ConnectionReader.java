package io.osiris.data.connection;

public interface ConnectionReader {

    String getName();

    String getDriverClass();

    String getUrl();

    String getUsername();

    String getPassword();
}
