package io.osiris.query.common;

import io.osiris.data.connection.ConnectionFactory;
import io.osiris.query.common.builder.MySqlBuilder;
import io.osiris.query.common.builder.QueryBuilder;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class Query {

    private static QueryBuilder injectBuilder() {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        String database = connectionFactory.databaseName();
        try {
            return (QueryBuilder)
                    BuilderImplementation.IMPLEMENTATION_MAP
                            .get(database.toLowerCase())
                            .getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return new MySqlBuilder();
    }

    public static QueryBuilder build() {
        return injectBuilder();
    }
}


class BuilderImplementation {

    static final Map<String, Class<?>> IMPLEMENTATION_MAP =
            Map.of(
                    "mysql", io.osiris.query.common.builder.MySqlBuilder.class,
                    "mssql-server", io.osiris.query.common.builder.SqlServerBuilder.class
            );
}
