package com.cebbys.celib.loaders;

import groovy.lang.GroovyClassLoader;

import java.io.File;

public class GroovyLoader {
    public static Class parseFile(ClassLoader parent, String path) throws Throwable {
        return new GroovyClassLoader(parent).parseClass(new File(path));
    }
    public static Class parseFile(ClassLoader parent, File file) throws Throwable {
        return new GroovyClassLoader(parent).parseClass(file);
    }

    public static Class parseString(ClassLoader parent, String code) throws Throwable {
        return new GroovyClassLoader(parent).parseClass(code);
    }
}
