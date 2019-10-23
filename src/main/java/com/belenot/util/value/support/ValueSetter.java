package com.belenot.util.value.support;

import com.belenot.util.value.ValueWrapper;

public interface ValueSetter<T> {
    void setValue(ValueWrapper valueWrapper);
}