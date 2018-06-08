package com.happy3w.util.convert.primitive;

public interface IPrimitiveConverter<T> {
    Class<T> dataType();
    Class primitiveType();
    String convertToString(T value);
    T convertFromString(String str);
}
