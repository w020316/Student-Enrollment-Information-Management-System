package com.stu.entity;

import java.util.List;

public class EntityInfo {
    private String tableName;
    private String className;
    private List<ColumnInfo> columns;

    public String getTableName() { return tableName; }
    public void setTableName(String tableName) { this.tableName = tableName; }
    public String getClassName() { return className; }
    public void setClassName(String className) { this.className = className; }
    public List<ColumnInfo> getColumns() { return columns; }
    public void setColumns(List<ColumnInfo> columns) { this.columns = columns; }

    public ColumnInfo getIdColumn() {
        for (ColumnInfo col : columns) {
            if (col.isId()) return col;
        }
        return null;
    }
}
