package me.niclan.imguifabric.events;

import imgui.ImFontAtlas;
import imgui.ImFontConfig;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

public interface ImGuiConfigureFontsCallback {
    /**
     * Callback for configuring fonts with Dear ImGui
     */
    Event<ImGuiConfigureFontsCallback> EVENT = EventFactory.createArrayBacked(ImGuiConfigureFontsCallback.class,
            (callbacks) -> (fontAtlas, fontConfig) -> {
                for (ImGuiConfigureFontsCallback callback : callbacks) {
                    callback.configure(fontAtlas, fontConfig);
                }
            });

    void configure(ImFontAtlas fontAtlas, ImFontConfig fontConfig);
}
