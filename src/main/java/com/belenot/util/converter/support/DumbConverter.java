package com.belenot.util.converter.support;

import com.belenot.util.converter.Converter;

public class DumbConverter implements Converter {

    public Object convert(Object value) {
        return value;
    }
    
}