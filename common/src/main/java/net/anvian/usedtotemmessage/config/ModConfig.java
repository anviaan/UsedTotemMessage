package net.anvian.usedtotemmessage.config;

import net.anvian.anvianslib.config.Config;
import org.slf4j.Logger;

public class ModConfig extends Config<ModConfig.UsedTotemMessageConfig> {
    public ModConfig(Class<UsedTotemMessageConfig> configClass, Logger logger) {
        super(configClass, logger);
    }

    @Override
    protected UsedTotemMessageConfig createDefaultConfig() {
        return new UsedTotemMessageConfig();
    }

    public static class UsedTotemMessageConfig {
        public String message;
        public String chatColor;

        UsedTotemMessageConfig() {
            this.message = "{player} used a Totem of Undying!";
            this.chatColor = "YELLOW";
        }
    }
}

