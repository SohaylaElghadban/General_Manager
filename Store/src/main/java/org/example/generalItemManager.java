package org.example;

import java.lang.reflect.*;
import java.sql.*;
import java.util.*;

public class generalItemManager<T> {
    private final Connection connection;

    public generalItemManager(Connection connection) { this.connection = connection; }

    //------------------------------------------------------------------------------------------------------------------
    public T insert(item obj) throws SQLException, IllegalAccessException {
        Class<?> clazz = obj.getClass();
        String tableName = clazz.getSimpleName();
        Field[] fields = clazz.getDeclaredFields();

        StringBuilder columns = new StringBuilder();
        StringBuilder placeholders = new StringBuilder();

        for (Field field : fields) {
            field.setAccessible(true);
            columns.append(field.getName()).append(", ");
            placeholders.append("?, ");
        }

        if (columns.length() > 0) {
            columns.setLength(columns.length() - 2);
            placeholders.setLength(placeholders.length() - 2);
        }

        String sql = "INSERT INTO " + tableName + " (" + columns + ") VALUES (" + placeholders + ")";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            int index = 1;
            for (Field field : fields) {
                field.setAccessible(true);
                statement.setObject(index++, field.get(obj));
            }

            statement.executeUpdate();
        }

        return (T) obj;
    }

    //----------------------------------------------------------------------------------------------------------------------
    public List<T> select(T obj) throws SQLException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        Class<?> clazz = obj.getClass();
        String tableName = clazz.getSimpleName();
        Field[] fields = clazz.getDeclaredFields();

        StringBuilder whereClause = new StringBuilder(" WHERE 1=1");
        List<Object> values = new ArrayList<>();


        for (Field field : fields) {
            field.setAccessible(true);
            Object value = field.get(obj);
            if (value != null) {
                whereClause.append(" AND ").append(field.getName()).append(" = ?");
                values.add(value);
            }
        }

        String sql = "SELECT * FROM " + tableName + whereClause;

        List<T> results = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (int i = 0; i < values.size(); i++) {
                statement.setObject(i + 1, values.get(i));
            }

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    T instance = (T) clazz.getDeclaredConstructor().newInstance();
                    for (Field field : fields) {
                        field.setAccessible(true);
                        Object value = resultSet.getObject(field.getName());
                        field.set(instance, value);
                    }
                    results.add(instance);
                }
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }

        return results;
    }
}
