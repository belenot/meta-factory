package com.belenot.util.accessor;

import java.lang.reflect.Field;

public interface FieldSetter {
    void set(Object object, Field field, Object value);
}