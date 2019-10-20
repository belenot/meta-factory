package com.belenot.util.pojo;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

public class Info {
    private Place place;
    private Annotation annotation;
    private Class<?> type;
    private Map<String, Object> attributes = new HashMap<>();

    private Info() { }
    public Place getPlace() { return place; }
    public Map<String, Object> getAttributes() { return new HashMap<>(attributes); }
    public Annotation getAnnotation() { return annotation; }
    public Class<?> getType() { return type; }

    public static Builder builder() {
        return new Info().new Builder();
    }

    
    public class Builder {
        private Builder() { }
        public Place getPlace() { return place; }
        public Annotation getAnnotation() { return annotation; }
        public Class<?> getType() { return type; }
        public Object getAttr(String name) { return attributes.get(name); }
        public Map<String, Object> getAttrs() { return new HashMap<>(attributes); }
        
        public Builder place(Place place) { Info.this.place = Place.of(place); return this; }
        public Builder annotation(Annotation annotation) { Info.this.annotation = annotation; return this; }
        public Builder type(Class<?> type) { Info.this.type = type; return this; }
        public Builder attrs(Map<String, Object> attributes) {
            Info.this.attributes.putAll(attributes);
            return this;
        }
        public Builder attrs(Object... attributes)  {
            for (Object attribute : attributes) {
                Info.this.attributes.put(attribute.getClass().getName(), attribute);
            }
            return this;
        }
        public Builder attr(String name, Object attribute) {
            Info.this.attributes.put(name, attribute);
            return this;
        }
        public Builder removeAttr(String name) {
            Info.this.attributes.remove(name);
            return this;
        }

        public Builder from(Info info) {
            if (info.getPlace() != null) { place = Place.of(info.getPlace()); }
            if (info.getAnnotation() != null) { annotation = info.getAnnotation(); }
            if (info.getType() != null) { type = info.getType(); }
            if (info.getAttributes() != null) { attributes.putAll(info.getAttributes()); }
            return this;
        }
        public Info build() { return Info.this; }

    }
    
}