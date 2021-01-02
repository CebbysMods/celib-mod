package com.cebbys.celib;

import com.cebbys.celib.testing.TestClientMain;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class CelibClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Testing init
        TestClientMain.init();
    }
}
