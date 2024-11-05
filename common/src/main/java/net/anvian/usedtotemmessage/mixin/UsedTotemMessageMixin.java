package net.anvian.usedtotemmessage.mixin;

import net.anvian.usedtotemmessage.Constants;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class UsedTotemMessageMixin {
    @Inject(method = "checkTotemDeathProtection", at = @At("HEAD"))
    private void inject(CallbackInfoReturnable<Boolean> cir) {
        LivingEntity entity = (LivingEntity) (Object) this;
        Level world = entity.getCommandSenderWorld();

        if (entity instanceof ServerPlayer player && entity.getMainHandItem().getItem() == Items.TOTEM_OF_UNDYING) {
            for (Player onlinePlayer : world.players()) {
                onlinePlayer.sendSystemMessage(
                        Component.literal(player.getName().getString())
                                .append(" ")
                                .append(Component.translatable(Constants.MOD_ID + ".usedtotem"))
                                .withStyle(ChatFormatting.GOLD)
                );
            }
        }
    }
}