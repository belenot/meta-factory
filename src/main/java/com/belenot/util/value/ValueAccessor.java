package com.belenot.util.value;

public interface ValueAccessor {
    Object getValue();
    boolean isNull();
    Class<?> getType();
}