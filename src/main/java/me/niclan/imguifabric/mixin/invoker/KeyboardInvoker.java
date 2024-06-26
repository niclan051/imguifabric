package me.niclan.imguifabric.mixin.invoker;

import net.minecraft.client.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Keyboard.class)
public interface KeyboardInvoker {
    @Invoker
    void invokeOnChar(long window, int codePoint, int modifiers);
}
