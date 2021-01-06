package com.cebbys.celib.mixin;

import com.cebbys.celib.Celib;
import com.cebbys.celib.loggers.CelibLogger;
import net.minecraft.Bootstrap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Bootstrap.class)
public class BoostrapMixin {
    @Inject(method = "initialize", at = @At(value = "HEAD"), require = 1, allow = 1)
    private static void afterInitialize(CallbackInfo ci) {
        Celib.boostrapInit.forEach(f -> {
            try {
                f.call();
            } catch (Exception e) {
                CelibLogger.error("BoostrapInitError", e.getMessage());
            }
        });
    }
}
