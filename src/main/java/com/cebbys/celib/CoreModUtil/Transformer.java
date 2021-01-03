package com.cebbys.celib.CoreModUtil;

import java.util.HashSet;
import java.util.Set;

@AsmTarget
public class Transformer{
    private static final Set<IClassTransformer> TRANSFORMERS = new HashSet<>();

    @AsmTarget
    public static byte[] transform(String name, String transformedName, byte[] basicClass){
        Set<IClassTransformer> transformers = new HashSet<>();
        for(IClassTransformer transformer : TRANSFORMERS){
            if(transformer.handlesClass(name, transformedName)){
                transformers.add(transformer);
            }
        }
        byte[] modifiedClass = basicClass;
        for(IClassTransformer transformer : transformers){
            modifiedClass = transformer.transformClass(name, transformedName, modifiedClass);
        }
        return modifiedClass;
    }

    public static void registerTransformer(IClassTransformer transformer){
        TRANSFORMERS.add(transformer);
    }
}
