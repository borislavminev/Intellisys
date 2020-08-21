package com.intellisoft.excelreader.domain.entity;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Json{
    private String name;
    private String key;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private String value;

    public Json() {
    }



    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }



    @Override
    public String toString() {
        return "json{ " +
                this.key + " : " +
                this.value +
                '}';
    }
}
