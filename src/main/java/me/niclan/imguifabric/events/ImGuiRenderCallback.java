package me.niclan.imguifabric.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

public interface ImGuiRenderCallback {
    /**
     * Callback for rendering with Dear ImGui
     */
    Event<ImGuiRenderCallback> EVENT = EventFactory.createArrayBacked(ImGuiRenderCallback.class,
            (callbacks) -> () -> {
                for (ImGuiRenderCallback callback : callbacks) {
                    callback.render();
                }
            });

    void render();
}
