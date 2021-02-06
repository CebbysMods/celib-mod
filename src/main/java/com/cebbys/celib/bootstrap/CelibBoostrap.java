package com.cebbys.celib.bootstrap;

import com.cebbys.celib.utilities.FastReflection;
import javassist.ClassPool;
import javassist.CtClass;
import org.spongepowered.asm.transformers.TreeTransformer;

public class CelibBoostrap {
    public static ClassLoader loader = CelibBoostrap.class.getClassLoader();
    public static TreeTransformer transformer = null;

    private static boolean initialized;
    public static synchronized void initialize() throws Throwable {
        if (initialized) return;
        initialized = true;
    }
}
