package com.stu.entity;

public class ColumnInfo {
    private String fieldName;
    private String columnName;
    private boolean nullable;
    private int maxLength;
    private String label;
    private boolean isId;

    public ColumnInfo() {}

    public ColumnInfo(String fieldName, String columnName, boolean nullable, int maxLength, String label, boolean isId) {
        this.fieldName = fieldName;
        this.columnName = columnName;
        this.nullable = nullable;
        this.maxLength = maxLength;
        this.label = label;
        this.isId = isId;
    }

    public String getFieldName() { return fieldName; }
    public void setFieldName(String fieldName) { this.fieldName = fieldName; }
    public String getColumnName() { return columnName; }
    public void setColumnName(String columnName) { this.columnName = columnName; }
    public boolean isNullable() { return nullable; }
    public void setNullable(boolean nullable) { this.nullable = nullable; }
    public int getMaxLength() { return maxLength; }
    public void setMaxLength(int maxLength) { this.maxLength = maxLength; }
    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }
    public boolean isId() { return isId; }
    public void setId(boolean id) { isId = id; }
}
