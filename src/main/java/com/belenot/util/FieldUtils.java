package com.belenot.util;

import java.lang.reflect.Field;

public class FieldUtils {
    public static void setValue(Object object, Field field, Object value) throws IllegalArgumentException {
        Class<?> type = field.getType();
        boolean accessible = field.isAccessible();
        try {
            field.setAccessible(true);
            if (type.equals(boolean.class)) {
                field.setBoolean(object, value==null?false:((Boolean)value).booleanValue());
            } else if (type.equals(byte.class)) {
                field.setByte(object, value==null?(byte)0:((Byte)value).byteValue());
            } else if (type.equals(short.class)) {
                field.setShort(object, value==null?(short)0:((Short)value).shortValue());
            } else if (type.equals(char.class)) {
                field.setChar(object, value==null?(char)0:((Character)value).charValue());
            } else if (type.equals(int.class)) {
                field.setInt(object, value==null?0:((Integer)value).intValue());
            } else if (type.equals(long.class)) {
                field.setLong(object, value==null?0:((Long)value).longValue());
            } else if (type.equals(float.class)) {
                field.setFloat(object, value==null?0:((Float)value).floatValue());
            } else if (type.equals(double.class)) {
                field.setDouble(object, value==null?0:((Double)value).doubleValue());
            } else {
                field.set(object, value);
            }
        } catch (Exception exc) {
            throw new IllegalArgumentException("Can't set value ["+value+"] to " + object.getClass().getName() + "." + field.getName(), exc);
        } finally {
            field.setAccessible(accessible);
        }
    }
}