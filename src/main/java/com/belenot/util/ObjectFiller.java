package com.belenot.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.belenot.util.accessor.FieldSetter;
import com.belenot.util.accessor.support.ConfigurableFieldSetter;
import com.belenot.util.annotation.Set;

public class ObjectFiller {
    FieldSetter setter = new ConfigurableFieldSetter();
    public void fill(Object object) {
        for (Field field : object.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Set.class)) {
                setter.set(object, field, null);
            }
        }
    }    
}
