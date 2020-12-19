package com.cebbys.celib;

import com.cebbys.celib.directories.CelibDirectories;
import com.cebbys.celib.directories.DirectoryHandler;
import com.cebbys.celib.loggers.CelibLogger;
import net.fabricmc.api.ModInitializer;

public class Celib implements ModInitializer {

    public static final String MOD_ID;

    @Override
    public void onInitialize() {
        CelibLogger.log( MOD_ID, "Loading Celib - CebbyS Library !" );
        if( ! CelibDirectories.LIB_DIRECTORY.toFile().exists() ) {
            CelibLogger.log( MOD_ID, "Creating `celib` directory in game directory !" );
            DirectoryHandler.initDirectory( CelibDirectories.LIB_DIRECTORY );
        }
        CelibLogger.log( MOD_ID, "Celib - CebbyS Library loaded !" );
        
    }

    static {
        MOD_ID = "celib";
    }
}
