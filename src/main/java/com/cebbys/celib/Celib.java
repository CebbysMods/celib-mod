package com.cebbys.celib;

import com.cebbys.celib.loggers.CelibLogger;
import net.fabricmc.api.ModInitializer;

public class Celib implements ModInitializer {

    public static final String MOD_ID;

    @Override
    public void onInitialize() {
        CelibLogger.log( MOD_ID, "CebbyS library loaded !" );
    }

    static {
        MOD_ID = "celib";
    }
}
