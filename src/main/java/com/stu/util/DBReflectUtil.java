package com.stu.util;

import com.stu.annotation.Column;
import com.stu.annotation.Entity;
import com.stu.annotation.ID;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class DBReflectUtil {

    public static String generateSelectAllSQL(Class<?> clazz) {
        Entity entity = clazz.getAnnotation(Entity.class);
        String tableName = entity.value();
        return "SELECT * FROM " + tableName;
    }

    public static String generateSelectByIdSQL(Class<?> clazz) {
        Entity entity = clazz.getAnnotation(Entity.class);
        String tableName = entity.value();
        String idColumn = getIdColumnName(clazz);
        return "SELECT * FROM " + tableName + " WHERE " + idColumn + " = ?";
    }

    public static String generateInsertSQL(Object obj) {
        Class<?> clazz = obj.getClass();
        Entity entity = clazz.getAnnotation(Entity.class);
        String tableName = entity.value();

        List<String> columns = new ArrayList<>();
        List<String> values = new ArrayList<>();

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(ID.class)) continue;
            if (field.isAnnotationPresent(Column.class)) {
                Column col = field.getAnnotation(Column.class);
                String colName = col.value().isEmpty() ? field.getName() : col.value();
                columns.add(colName);
                values.add("?");
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ").append(tableName).append(" (");
        sb.append(String.join(", ", columns));
        sb.append(") VALUES (");
        sb.append(String.join(", ", values));
        sb.append(")");
        return sb.toString();
    }

    public static Object[] getInsertParams(Object obj) {
        Class<?> clazz = obj.getClass();
        List<Object> params = new ArrayList<>();

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(ID.class)) continue;
            if (field.isAnnotationPresent(Column.class)) {
                field.setAccessible(true);
                try {
                    params.add(field.get(obj));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return params.toArray();
    }

    public static String generateUpdateSQL(Object obj) {
        Class<?> clazz = obj.getClass();
        Entity entity = clazz.getAnnotation(Entity.class);
        String tableName = entity.value();
        String idColumn = getIdColumnName(clazz);

        List<String> setClauses = new ArrayList<>();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(ID.class)) continue;
            if (field.isAnnotationPresent(Column.class)) {
                Column col = field.getAnnotation(Column.class);
                String colName = col.value().isEmpty() ? field.getName() : col.value();
                setClauses.add(colName + " = ?");
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ").append(tableName).append(" SET ");
        sb.append(String.join(", ", setClauses));
        sb.append(" WHERE ").append(idColumn).append(" = ?");
        return sb.toString();
    }

    public static Object[] getUpdateParams(Object obj) {
        Class<?> clazz = obj.getClass();
        List<Object> params = new ArrayList<>();
        Object idValue = null;

        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            try {
                if (field.isAnnotationPresent(ID.class)) {
                    idValue = field.get(obj);
                } else if (field.isAnnotationPresent(Column.class)) {
                    params.add(field.get(obj));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        params.add(idValue);
        return params.toArray();
    }

    public static String generateDeleteSQL(Class<?> clazz) {
        Entity entity = clazz.getAnnotation(Entity.class);
        String tableName = entity.value();
        String idColumn = getIdColumnName(clazz);
        return "DELETE FROM " + tableName + " WHERE " + idColumn + " = ?";
    }

    public static String generateCountSQL(Class<?> clazz) {
        Entity entity = clazz.getAnnotation(Entity.class);
        String tableName = entity.value();
        return "SELECT COUNT(*) FROM " + tableName;
    }

    public static String generatePageSQL(Class<?> clazz) {
        return generateSelectAllSQL(clazz) + " LIMIT ? OFFSET ?";
    }

    private static String getIdColumnName(Class<?> clazz) {
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(ID.class)) {
                if (field.isAnnotationPresent(Column.class)) {
                    Column col = field.getAnnotation(Column.class);
                    return col.value().isEmpty() ? field.getName() : col.value();
                }
                return field.getName();
            }
        }
        return "id";
    }
}
