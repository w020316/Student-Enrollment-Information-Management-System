package com.stu.util;

import com.stu.annotation.Column;
import com.stu.annotation.Entity;
import com.stu.annotation.ID;
import com.stu.entity.ColumnInfo;
import com.stu.entity.EntityInfo;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class EntityInfoHelper {

    public static EntityInfo getEntityInfo(Class<?> clazz) {
        EntityInfo info = new EntityInfo();
        Entity entityAnno = clazz.getAnnotation(Entity.class);
        if (entityAnno != null) {
            info.setTableName(entityAnno.value());
        }
        info.setClassName(clazz.getName());

        List<ColumnInfo> columns = new ArrayList<>();
        for (Field field : clazz.getDeclaredFields()) {
            ColumnInfo colInfo = new ColumnInfo();
            colInfo.setFieldName(field.getName());

            if (field.isAnnotationPresent(ID.class)) {
                colInfo.setId(true);
            }

            if (field.isAnnotationPresent(Column.class)) {
                Column col = field.getAnnotation(Column.class);
                colInfo.setColumnName(col.value().isEmpty() ? field.getName() : col.value());
                colInfo.setNullable(col.nullable());
                colInfo.setMaxLength(col.maxLength());
                colInfo.setLabel(col.label().isEmpty() ? field.getName() : col.label());
            } else {
                colInfo.setColumnName(field.getName());
                colInfo.setLabel(field.getName());
            }

            columns.add(colInfo);
        }
        info.setColumns(columns);
        return info;
    }

    public static Object getEntityId(Object entity) {
        Class<?> clazz = entity.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(ID.class)) {
                field.setAccessible(true);
                try {
                    return field.get(entity);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static Object getEntityFieldValue(Field field, Object entity) {
        field.setAccessible(true);
        try {
            return field.get(entity);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
