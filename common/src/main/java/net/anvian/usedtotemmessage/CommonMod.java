package net.anvian.usedtotemmessage;

import net.anvian.anvianslib.platform.Services;
import net.anvian.anvianslib.util.LibUtil;
import net.anvian.usedtotemmessage.config.ModConfig;

public class CommonMod {
    public static ModConfig configs = new ModConfig(ModConfig.UsedTotemMessageConfig.class, Constants.LOG);

    public static void init() {
        Constants.LOG.info("Hello from UsedTotemMessage in {}!", LibUtil.getMinecraftVersion());

        configs.initialize(Services.PLATFORM.getConfigPath().resolve(Constants.MOD_ID).toFile(), Constants.MOD_ID);

        LibUtil.setupTelemetry(Constants.MOD_ID, "1.0");
    }
}