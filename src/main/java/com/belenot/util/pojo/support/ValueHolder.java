package com.belenot.util.pojo.support;

public class ValueHolder {
    private Object objectValue;
    private int intValue;
    private double doubleValue;
    private long longValue;
    private Class<?> valueType;
    private boolean empty = false;

    private ValueHolder() {
        empty = true;
    }

    public ValueHolder(Object value) {
        this.objectValue = value;
        if (value.getClass().equals(Integer.class)) {
            intValue = ((Integer)value).intValue();
        }
        if (value.getClass().equals(Double.class)) {
            doubleValue = ((Double)value).doubleValue();
        }
        if (value.getClass().equals(Long.class)) {
            longValue = ((Long)value).longValue();
        }
        valueType = value.getClass();
    }

    public ValueHolder(int value) {
        this.intValue = value;
        this.objectValue = Integer.valueOf(value);
        valueType = int.class;
    }
    public ValueHolder(double value) {
        this.doubleValue = value;
        this.objectValue = Double.valueOf(value);
        valueType = double.class;
    }
    public ValueHolder(long value) {
        this.longValue = value;
        this.objectValue = Long.valueOf(value);
        valueType = long.class;
    }

    public Object getObjectValue() { return objectValue; }
    public int getIntValue() { return intValue; }
    public double getDoubleValue() { return doubleValue; }
    public long getLongValue() { return longValue; }
    public Class<?> getValueType() { return valueType; }
    public boolean isEmpty() { return empty;}
    public static ValueHolder empty() { return new ValueHolder(); }

}