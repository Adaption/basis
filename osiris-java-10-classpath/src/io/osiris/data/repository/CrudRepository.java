package io.osiris.data.repository;

import io.osiris.data.common.annotation.Column;
import io.osiris.data.common.annotation.Generated;
import io.osiris.data.connection.ConnectionAdapter;
import io.osiris.data.connection.ConnectionFactory;
import io.osiris.data.jpa.Entity;
import io.osiris.data.jpa.binding.EntityBindingsFactory;
import io.osiris.data.jpa.binding.EntityDataBindings;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static io.osiris.data.common.binding.function.DataBindingHandler.setFields;

public abstract class CrudRepository<T extends Entity, R extends Serializable> implements JpaRepository {

    @SuppressWarnings("unchecked")
    private final Class<T> entityClass = (Class<T>) ((ParameterizedType) this.getClass()
            .getGenericSuperclass())
            .getActualTypeArguments()[0];

    private final ConnectionAdapter connectionFactory = new ConnectionFactory();

    private final String table;
    private final List<String> idColumns;
    private final List<String> generatedColumns;
    private final List<String> columns;
    private final List<Field> fields;

    public CrudRepository() {
        EntityBindingsFactory bindingsFactory = new EntityBindingsFactory(entityClass);
        EntityDataBindings dataBindings = bindingsFactory.createDataBindings();

        this.table = dataBindings.table();
        this.idColumns = dataBindings.idColumns();
        this.generatedColumns = dataBindings.generatedColumns();
        this.columns = dataBindings.columns();
        this.fields = dataBindings.fields();
    }

    @Override
    public List<T> findAll() {
        List<T> result = new ArrayList<>();

        String query = "SELECT * FROM " + table;

        try (Connection connection = connectionFactory.openConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                T entity = entityClass.getConstructor().newInstance();
                setFields(entity, fields, resultSet);
                result.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public Optional<T> findById(Serializable... ids) {
        Optional<T> entity = Optional.empty();

        String query = "SELECT * FROM " + table + " WHERE " +
                idColumns.stream()
                        .map(idColumn -> idColumn + " = ?")
                        .collect(Collectors.joining(" AND "));

        try (Connection connection = connectionFactory.openConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            for (int i = 0; i < ids.length; i++) {
                if (ids[i] instanceof Integer) {
                    statement.setInt((i + 1), (Integer) ids[i]);
                } else {
                    statement.setLong((i + 1), (Long) ids[i]);
                }
            }

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                T result = entityClass.getConstructor().newInstance();
                setFields(result, fields, resultSet);
                entity = Optional.of(result);
            }

            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return entity;
    }

    @Override
    public void save(Entity entity) {
        List<Serializable> entityIds = new ArrayList<>(entity.idMap().values());

        String query = findById(entityIds.toArray(new Serializable[entityIds.size()])).isPresent() ?
                update(entity) : insert();

        try (Connection connection = connectionFactory.openConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            int parameterCount = 0;
            for (Field field : fields) {
                Column column = field.getAnnotation(Column.class);
                Generated generated = field.getAnnotation(Generated.class);
                if (column != null && generated == null) {
                    parameterCount++;
                    field.setAccessible(true);
                    Object value = field.get(entity);

                    statement.setString(
                            parameterCount,
                            value == null ? null : String.valueOf(value));
                }
            }

            if (statement.executeUpdate() > 0) {
                System.out.println("Save Successfully");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Serializable... ids) {
        if (!findById(ids).isPresent()) {
            System.out.println("No entity found!");
            return;
        }

        String query = "DELETE FROM " + table + " WHERE " +
                idColumns.stream()
                        .map(idColumn -> idColumn + " = ?")
                        .collect(Collectors.joining(" AND "));

        try (Connection connection = connectionFactory.openConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            for (int i = 0; i < ids.length; i++) {
                if (ids[i] instanceof Integer) {
                    statement.setInt((i + 1), (Integer) ids[i]);
                } else {
                    statement.setLong((i + 1), (Long) ids[i]);
                }
            }

            if (statement.executeUpdate() > 0) {
                System.out.println("Delete Successfully");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String update(Entity entity) {
        String valuesTuple = columns.stream()
                .filter(column -> !generatedColumns.contains(column))
                .map(col -> col + " = ?")
                .collect(Collectors.joining(","));

        Map<String, Serializable> idMap = entity.idMap();

        String idsTuple = idColumns.stream()
                .map(idColumn -> idColumn + " = " + idMap.get(idColumn))
                .collect(Collectors.joining(" AND "));

        return "UPDATE " + table + " SET " + valuesTuple + " WHERE " + idsTuple;
    }

    private String insert() {
        String columns = this.columns
                .stream()
                .filter(column -> !generatedColumns.contains(column))
                .collect(Collectors.joining(","));

        String values = this.columns
                .stream()
                .filter(column -> !generatedColumns.contains(column))
                .map(col -> "?")
                .collect(Collectors.joining(","));

        return "INSERT INTO " + table + "(" + columns + ")" + " VALUES(" + values + ")";
    }
}
