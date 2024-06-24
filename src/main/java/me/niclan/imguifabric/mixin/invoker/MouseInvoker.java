package me.niclan.imguifabric.mixin.invoker;

import net.minecraft.client.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Mouse.class)
public interface MouseInvoker {
    @Invoker
    void invokeOnMouseButton(long window, int button, int action, int mods);
    @Invoker
    void invokeOnMouseScroll(long window, double horizontal, double vertical);
}
