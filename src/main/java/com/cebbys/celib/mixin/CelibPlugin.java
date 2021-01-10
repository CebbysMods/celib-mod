package com.cebbys.celib.mixin;

import com.cebbys.celib.loggers.CelibLogger;
import com.cebbys.celib.utilities.Action;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CelibPlugin implements IMixinConfigPlugin {
    // Boostrap
    public static final ArrayList<Action> boostrapInit = new ArrayList<>();

    static {
        // BoostrapTest
        boostrapInit.add(() -> CelibLogger.log("Celib", "Boostrap loading! UwU"));

        try {
            boostrapInit.forEach(Action::call);
        } catch (Throwable t) {
            CelibLogger.error("MixinBoostrapError", t.toString());
        }
    }

    @Override
    public void onLoad(String mixinPackage) {

    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        return false;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {

    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }
}
