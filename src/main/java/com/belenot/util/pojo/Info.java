package com.belenot.util.pojo;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

public class Info {
    private Place place;
    private Annotation annotation;
    private Map<String, Object> attributes = new HashMap<>();

    public Info() { }
    public Info(Place place, Annotation annotation) {
        this.place = place;
        this.annotation = annotation;
    }
    public Info(Place place, Annotation annotation, Map<String, Object> attributes) {
        this.place = place;
        this.annotation = annotation;
        this.attributes = attributes;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public Annotation getAnnotation() {
        return annotation;
    }

    public void setAnnotation(Annotation annotation) {
        this.annotation = annotation;
    }
    
}