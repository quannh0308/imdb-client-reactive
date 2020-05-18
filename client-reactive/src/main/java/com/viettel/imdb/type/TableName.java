package com.viettel.imdb.type;

import com.viettel.imdb.util.IMDBSerializer;

public class TableName implements IMDBSerializer {
    private final String value;

    public TableName(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "TableName{" +
                "value='" + value + '\'' +
                '}';
    }
}
