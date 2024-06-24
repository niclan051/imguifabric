package me.niclan.imguifabric.events;

import imgui.ImGuiIO;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

public interface ImGuiSetupCallback {
    Event<ImGuiSetupCallback> EVENT = EventFactory.createArrayBacked(ImGuiSetupCallback.class,
            (callbacks) -> (io) -> {
                for (ImGuiSetupCallback callback : callbacks) {
                    callback.setup(io);
                }
            });

    void setup(ImGuiIO io);
}
