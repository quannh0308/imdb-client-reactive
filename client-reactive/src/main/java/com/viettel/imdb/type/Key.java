package com.viettel.imdb.type;

import com.viettel.imdb.util.IMDBSerializer;

public class Key implements IMDBSerializer {
    public final String value;

    public Key(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Key{" +
                "value='" + value + '\'' +
                '}';
    }
}
