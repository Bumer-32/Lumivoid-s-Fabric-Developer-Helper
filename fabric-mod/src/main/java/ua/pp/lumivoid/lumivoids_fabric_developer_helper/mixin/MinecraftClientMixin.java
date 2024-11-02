package ua.pp.lumivoid.lumivoids_fabric_developer_helper.mixin;

import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ua.pp.lumivoid.lumivoids_fabric_developer_helper.Options;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Inject(method = "setScreen", at = @At("HEAD"))
    public void setScreen(net.minecraft.client.gui.screen.Screen screen, CallbackInfo ci) {
        Options.INSTANCE.setCurrentMinecraftScreen(screen);
    }
}