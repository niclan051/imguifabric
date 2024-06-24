package me.niclan.imguifabric.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import me.niclan.imguifabric.imgui.ImGuiController;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderSystem.class)
public class RenderMixin {
    @Inject(method = "flipFrame", at = @At("HEAD"))
    private static void afterRender(long window, CallbackInfo ci) {
        ImGuiController.renderFrame();
    }
}
