package com.cebbys.celib.directories;

import java.nio.file.Path;

import net.fabricmc.loader.api.FabricLoader;

public class CelibDirectories {

    public static final Path GAME_DIRECTORY;
    public static final Path LIB_DIRECTORY;

    static {
        GAME_DIRECTORY = FabricLoader.getInstance().getGameDir();
        LIB_DIRECTORY = DirectoryHandler.appendToPath(GAME_DIRECTORY, "celib");
    }

}
