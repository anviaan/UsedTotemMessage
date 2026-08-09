package net.anvian.usedtotemmessage.mixin;

import net.anvian.usedtotemmessage.CommonMod;
import net.anvian.usedtotemmessage.config.ModConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Locale;

@Mixin(LivingEntity.class)
public class UsedTotemMessageMixin {
    @Unique
    private ModConfig.UsedTotemMessageConfig usedTotemMessage$config = CommonMod.configs.getConfig();

    @Inject(method = "checkTotemDeathProtection", at = @At("HEAD"))
    private void inject(CallbackInfoReturnable<Boolean> cir) {
        LivingEntity entity = (LivingEntity) (Object) this;

        if (entity instanceof ServerPlayer player && entity.getMainHandItem().getItem() == Items.TOTEM_OF_UNDYING) {
            entity.level().getServer().getPlayerList().broadcastSystemMessage(usedTotemMessage$createMessage(player.getName().getString()), false);
        }
    }

    @Unique
    private MutableComponent usedTotemMessage$createMessage(String playerName) {
        String rawMessage = usedTotemMessage$config.message;
        String formatted = rawMessage.replace("{player}", playerName);
        return Component.literal(formatted).withStyle(ChatFormatting.valueOf(usedTotemMessage$config.chatColor.toUpperCase(Locale.ROOT)));
    }
}