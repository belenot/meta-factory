package com.belenot.util.value.convert;

import com.belenot.util.value.ValueWrapper;

public class DefaultConverter implements OrderedConverter {

    @Override
    public Object convert(ValueWrapper valueWrapper) {
        return valueWrapper.getValue();
    }

    @Override
    public boolean support(ValueWrapper valueWrapper) {
        return true;
    }

    @Override
    public int order() {
        return Integer.MAX_VALUE;
    }

}