package me.niclan.imguifabric.mixin;

import imgui.ImGui;
import net.minecraft.client.gui.widget.ClickableWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClickableWidget.class)
public class BlockMouseMixin {
    @Inject(method = "isHovered", at = @At("HEAD"), cancellable = true)
    private void blockMousePos(CallbackInfoReturnable<Boolean> cir) {
        if (ImGui.getIO().getWantCaptureMouse()) cir.setReturnValue(false);
    }
}
