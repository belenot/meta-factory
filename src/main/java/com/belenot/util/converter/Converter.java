package com.belenot.util.converter;

@FunctionalInterface
public interface Converter {
    public Object convert(Object value);
}