package com.belenot.util.value.convert;

import com.belenot.util.value.ValueWrapper;

public interface Converter {
    Object convert(ValueWrapper valueWrapper);
    boolean support(ValueWrapper valueWrapper);
}