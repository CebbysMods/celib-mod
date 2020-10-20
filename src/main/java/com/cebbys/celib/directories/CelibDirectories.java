package com.cebbys.celib.directories;

import com.cebbys.celib.Celib;
import com.cebbys.celib.loggers.CelibLogger;
import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;

public class CelibDirectories {

    public static final Path GAME_DIRECTORY;
    public static final Path LIB_DIRECTORY;

    static {
        GAME_DIRECTORY = FabricLoader.getInstance().getGameDir();
        LIB_DIRECTORY = DirectoryHandler.appendToPath(GAME_DIRECTORY, "celib");
    }

}
