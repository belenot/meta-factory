package com.belenot.util.value.annotation.processor.support;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.belenot.util.value.ValueHolderDescriptor;
import com.belenot.util.value.ValueWrapper;
import com.belenot.util.value.annotation.Generated;
import com.belenot.util.value.annotation.processor.AbstractGeneratedElementProcessor;
import com.belenot.util.value.generator.ValueGenerator;

public class GeneratedFieldProcessor extends AbstractGeneratedElementProcessor<Field> {

    // List<GeneratedFieldProcessor> processors 

    @Override
    protected ValueWrapper generateValue(Field field, Object object) {
        Generated annotation = field.getDeclaredAnnotation(Generated.class);
        Map<String, Object> info = new HashMap<>();
        if (annotation == null) {
            Annotation metaAnnotation = Arrays.stream(field.getAnnotations()).filter(a->a.annotationType().isAnnotationPresent(Generated.class)).findFirst().get();
            annotation = metaAnnotation.annotationType().getDeclaredAnnotation(Generated.class);
            info.put("meta", metaAnnotation);
        }
        if (annotation == null) throw new IllegalArgumentException("Field should be annotated or meta-annotated via @Generated");
        try {
            ValueGenerator generator = annotation.generator().getConstructor(new Class<?>[0]).newInstance(new Object[0]);
            Object value = generator.generate(info);
            ValueWrapper valueWrapper = new FieldValueWrapper(value, object, field);
            return valueWrapper;
        } catch (Exception exc) {
            System.err.println(exc);
        }
        return new FieldValueWrapper(null, object, field);
    }

    @Override
    protected List<Field> getGeneratedElements(Object object) {
        List<Field> fields = Arrays.stream(object.getClass().getDeclaredFields()).filter(this::hasGeneratedAnnotation)
                .collect(Collectors.toList());
        return fields;
    }

    @Override
    protected void setValue(Object value, Object object, Field field) throws Exception {
        boolean accessible = field.isAccessible();
        field.setAccessible(true);
        Class<?> type = field.getType();
        if (type.equals(boolean.class) && value instanceof Boolean) {
            field.setBoolean(object, ((Boolean) value).booleanValue());
        } else if (type.equals(byte.class) && value instanceof Byte) {
            field.setByte(object, ((Byte) value).byteValue());
        } else if (type.equals(short.class) && value instanceof Short) {
            field.setShort(object, ((Short) value).shortValue());
        } else if (type.equals(char.class) && value instanceof Character) {
            field.setChar(object, ((Character) value).charValue());
        } else if (type.equals(int.class) && value instanceof Integer) {
            field.setInt(object, ((Integer) value).intValue());
        } else if (type.equals(long.class) && value instanceof Long) {
            field.setLong(object, ((Long) value).longValue());
        } else if (type.equals(float.class) && value instanceof Float) {
            field.setFloat(object, ((Float) value).floatValue());
        } else if (type.equals(double.class) && value instanceof Double) {
            field.setDouble(object, ((Double) value).doubleValue());
        } else {
            field.set(object, value);
        }
        field.setAccessible(accessible);
    }

    private boolean hasGeneratedAnnotation(Field field) {
        Annotation[] annotations = field.getDeclaredAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation.annotationType().equals(Generated.class)) {
                return true;
            } else if (annotation.annotationType().isAnnotationPresent(Generated.class)) {
                return true;
            }
        }
        return false;
    }

    protected class FieldValueWrapper implements ValueWrapper {
        private Object value;
        private Object object;
        private Field field;

        public FieldValueWrapper(Object value, Object object, Field field) {
            this.value = value;
            this.object = object;
            this.field = field;
        }

        @Override
        public ValueHolderDescriptor getValueHolderDescriptor() {
            return new ValueHolderDescriptor(object, field);
        }

        @Override
        public Class<?> getType() {
            return value.getClass();
        }

        @Override
        public Object getValue() {
            return value;
        }

        @Override
        public boolean isNull() {
            return value != null;
        }
        
    }
}