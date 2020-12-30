package com.cebbys.celib.CoreModUtil;

public interface IClassTransformer{
    boolean handlesClass(String name, String transformedName);
    byte[] transformClass(String name, String transformedName, byte[] original);
}
