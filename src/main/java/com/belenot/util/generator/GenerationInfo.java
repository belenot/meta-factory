package com.belenot.util.generator;

import java.util.Map;

public class GenerationInfo {
    private Map<String, Object> attributes;
    private Object payload;
    
    public Class<?> getPayloadType() {
        return payload.getClass();
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }
    
}