package com.belenot.util.value.convert;

import com.belenot.util.value.ValueWrapper;

public class NumberConverter implements OrderedConverter {

    @Override
    public Object convert(ValueWrapper valueWrapper) {
        //aaa hard
        throw new IllegalStateException("I can't do this yet");
    }

    @Override
    public boolean support(ValueWrapper valueWrapper) {
        Class<?> type = valueWrapper.getType();
        if (type.equals(byte.class) || type.equals(Byte.class) ||
            type.equals(short.class) || type.equals(Short.class) ||
            type.equals(char.class) || type.equals(Character.class) ||
            type.equals(int.class) || type.equals(Integer.class) ||
            type.equals(long.class) || type.equals(Long.class) ||
            type.equals(float.class) || type.equals(Float.class) ||
            type.equals(double.class) || type.equals(Double.class)) {
                return true;
        }
        return false;
    }

    @Override
    public int order() {
        return 0;
    }
    
}