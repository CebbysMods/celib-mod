package com.cebbys.celib;

import com.cebbys.celib.directories.CelibDirectories;
import com.cebbys.celib.directories.DirectoryHandler;
import com.cebbys.celib.loggers.CelibLogger;
import com.cebbys.celib.testing.TItem;
import com.github.mouse0w0.fastreflection.FastReflection;
import com.github.mouse0w0.fastreflection.FieldAccessor;
import com.github.mouse0w0.fastreflection.MethodAccessor;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.BucketItem;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import sun.misc.Unsafe;

public class Celib implements ModInitializer {
    public static final String MOD_ID;
    // LadderAPI
    public static final Tag<Block> CLIMBABLE = TagRegistry.block(new Identifier("celib", "climbable"));
    // Unsafe
    public static Unsafe unsafe = null;
    // Reflection Fields
    public static FieldAccessor BucketItem_fluid_field;

    @Override
    public void onInitialize() {
        CelibLogger.log(MOD_ID, "Loading Celib - CebbyS Library !");
        if (!CelibDirectories.LIB_DIRECTORY.toFile().exists()) {
            CelibLogger.log(MOD_ID, "Creating `celib` directory in game directory !");
            DirectoryHandler.initDirectory(CelibDirectories.LIB_DIRECTORY);
        }
        CelibLogger.log(MOD_ID, "Celib - CebbyS Library loaded !");
        // Testing
        Registry.register(Registry.ITEM, new Identifier("celib", "titem"), new TItem());
    }

    static {
        MOD_ID = "celib";
        // Reflection init
        try {
            unsafe = (Unsafe) FastReflection.create(Unsafe.class.getDeclaredField("theUnsafe")).get(null);
            BucketItem_fluid_field = FastReflection.create(BucketItem.class.getDeclaredField("fluid"));
        } catch (Throwable t) {
            CelibLogger.error("Reflection init error", t.getMessage());
        }
    }
}
