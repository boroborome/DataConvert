package com.happy3w.util.convert.primitive;

public class PrimitiveUtil {
    public static String dropPointValue(String value) {
        int pos = value.indexOf('.');
        return pos < 0 ? value : value.substring(0, pos);
    }
}
