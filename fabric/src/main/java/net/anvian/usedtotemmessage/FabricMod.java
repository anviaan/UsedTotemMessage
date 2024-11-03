package net.anvian.usedtotemmessage;

import net.fabricmc.api.ModInitializer;

public class FabricMod implements ModInitializer {
    
    @Override
    public void onInitialize() {
        Constants.LOG.info("Hello from UsedTotemMessage (Fabric)!");
        CommonMod.init();
    }
}
