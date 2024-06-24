package me.niclan.imguifabric;

import imgui.ImGui;
import imgui.ImGuiIO;
import me.niclan.imguifabric.imgui.ImGuiController;
import me.niclan.imguifabric.mixin.invoker.KeyboardInvoker;
import me.niclan.imguifabric.mixin.invoker.MouseInvoker;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.minecraft.client.MinecraftClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.lwjgl.glfw.GLFW.*;

public class ImGuiFabric implements ClientModInitializer {
    private static final Logger LOGGER = LogManager.getLogger("ImGui Fabric");

    @Override
    public void onInitializeClient() {
        LOGGER.info("ImGui for Fabric initialized");
        ClientLifecycleEvents.CLIENT_STARTED.register(client -> {
            setMouseCallbacks(client);
            setKeyboardCallbacks(client);
        });
    }

    private void setMouseCallbacks(MinecraftClient client) {
        final ImGuiIO io = ImGui.getIO();

        glfwSetMouseButtonCallback(client.getWindow().getHandle(), (window, button, action, mods) -> {
            ImGuiController.IMGUI_GLFW.mouseButtonCallback(window, button, action, mods);
            if (!io.getWantCaptureMouse()) {
                ((MouseInvoker) client.mouse).invokeOnMouseButton(window, button, action, mods);
            }
        });
        glfwSetScrollCallback(client.getWindow().getHandle(), (window, horizontal, vertical) -> {
            ImGuiController.IMGUI_GLFW.scrollCallback(window, horizontal, vertical);
            if (!io.getWantCaptureMouse()) {
                ((MouseInvoker) client.mouse).invokeOnMouseScroll(window, horizontal, vertical);
            }
        });
    }

    private void setKeyboardCallbacks(MinecraftClient client) {
        final ImGuiIO io = ImGui.getIO();

        glfwSetKeyCallback(client.getWindow().getHandle(), (window, key, scancode, action, modifiers) -> {
            ImGuiController.IMGUI_GLFW.keyCallback(window, key, scancode, action, modifiers);
            if (!io.getWantCaptureKeyboard()) {
                client.keyboard.onKey(window, key, scancode, action, modifiers);
            }
        });
        glfwSetCharModsCallback(client.getWindow().getHandle(), (window, codepoint, modifiers) -> {
            ImGuiController.IMGUI_GLFW.charCallback(window, codepoint);
            if (!io.getWantTextInput()) {
                ((KeyboardInvoker) client.keyboard).invokeOnChar(window, codepoint, modifiers);
            }
        });
    }
}
