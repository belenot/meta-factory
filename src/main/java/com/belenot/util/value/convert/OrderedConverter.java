package com.belenot.util.value.convert;

import com.belenot.util.value.Ordered;

public interface OrderedConverter extends Converter, Ordered {
    default int getOrder() {
        return 0;
    }
}