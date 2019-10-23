package com.belenot.util.value;

public class ValueHolderDescriptor {
    private Object object;
    private Object element;

    public ValueHolderDescriptor() {}
    public ValueHolderDescriptor(Object object, Object element) {
        this.object = object;
        this.element = element;
    }

    public Object getObject() { return object; }
    public Object getElement() { return element; }
    public void setObject(Object object) { this.object = object; }
    public void setElement(Object element) { this.element = element; }
}