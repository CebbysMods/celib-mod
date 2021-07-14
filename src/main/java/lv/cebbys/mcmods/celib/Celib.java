package lv.cebbys.mcmods.celib;

import lv.cebbys.mcmods.celib.directories.CelibDirectories;
import lv.cebbys.mcmods.celib.directories.DirectoryHandler;
import lv.cebbys.mcmods.celib.loggers.CelibLogger;
import net.fabricmc.api.ModInitializer;

public class Celib implements ModInitializer {
    public static final String MODID;

    @Override
    public void onInitialize() {
        CelibLogger.log(MODID, "Loading Celib - CebbyS Library!");
        if (!CelibDirectories.LIB_DIRECTORY.toFile().exists()) {
            CelibLogger.log(MODID, "Creating `celib` directory in game directory!");
            DirectoryHandler.initDirectory(CelibDirectories.LIB_DIRECTORY);
        }
        CelibLogger.log(MODID, "Celib - CebbyS Library loaded!");
    }

    static {
        MODID = "celib";
    }
}
