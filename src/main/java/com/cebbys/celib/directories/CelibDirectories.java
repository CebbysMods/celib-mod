package com.cebbys.celib.directories;

import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;

public class CelibDirectories {

    public static final Path GAME_DIRECTORY;
    public static final Path LIB_DIRECTORY;

    public static final Path MOD_DIRECTORY;
    public static final Path TEMPLATE_DIRECTORY;
    public static final Path RECIPE_DIRECTORY;

    public static final Path RESOURCE_DIRECTORY;
    public static final Path DATA_DIRECTORY;


    static {
        GAME_DIRECTORY = FabricLoader.getInstance().getGameDir().getParent();
        LIB_DIRECTORY = DirectoryHandler.appendToPath(GAME_DIRECTORY, "cebbyslib");

        MOD_DIRECTORY = DirectoryHandler.appendToPath(LIB_DIRECTORY, "mods");
        TEMPLATE_DIRECTORY = DirectoryHandler.appendToPath(LIB_DIRECTORY, "templates");
        RECIPE_DIRECTORY = DirectoryHandler.appendToPath(LIB_DIRECTORY, "recipes");

        RESOURCE_DIRECTORY = DirectoryHandler.appendToPath(GAME_DIRECTORY, "resourcepacks");
        DATA_DIRECTORY = DirectoryHandler.appendToPath(GAME_DIRECTORY, "resourcepacks");
    }

}
