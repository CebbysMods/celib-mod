package com.cebbys.celib.utilities;

import com.github.mouse0w0.fastreflection.FieldAccessor;
import com.github.mouse0w0.fastreflection.MethodAccessor;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class FastReflection {
    public static FieldAccessor create(Field field) throws Exception {
        return com.github.mouse0w0.fastreflection.FastReflection.create(field);
    }
    public static MethodAccessor create(Method field) throws Exception {
        return com.github.mouse0w0.fastreflection.FastReflection.create(field);
    }
}
