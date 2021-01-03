package com.cebbys.celib.utilities;

import com.github.mouse0w0.fastreflection.FieldAccessor;

import java.lang.reflect.Field;

public class FastReflection {
    public static FieldAccessor create(Field field) throws Exception {
        return com.github.mouse0w0.fastreflection.FastReflection.create(field);
    }
}
