package me.niclan.imguifabric.imgui;

import imgui.*;
import imgui.flag.ImGuiCol;
import imgui.flag.ImGuiConfigFlags;
import imgui.gl3.ImGuiImplGl3;
import imgui.glfw.ImGuiImplGlfw;
import me.niclan.imguifabric.events.ImGuiConfigureFontsCallback;
import me.niclan.imguifabric.events.ImGuiRenderCallback;
import me.niclan.imguifabric.events.ImGuiSetupCallback;

import static org.lwjgl.glfw.GLFW.*;

public class ImGuiController {
    public static final ImGuiImplGlfw IMGUI_GLFW = new ImGuiImplGlfw();
    public static final ImGuiImplGl3 IMGUI_GL3 = new ImGuiImplGl3();

    public static void glfwInit(long handle) {
        ImGui.createContext();

        final ImGuiIO io = ImGui.getIO();

        ImGuiSetupCallback.EVENT.invoker().setup(io);

        final ImFontConfig fontConfig = new ImFontConfig();
        ImGuiConfigureFontsCallback.EVENT.invoker().configure(io.getFonts(), fontConfig);
        fontConfig.destroy();

        if (io.hasConfigFlags(ImGuiConfigFlags.ViewportsEnable)) {
            final ImGuiStyle style = ImGui.getStyle();
            style.setWindowRounding(0.0f);
            style.setColor(ImGuiCol.WindowBg, ImGui.getColorU32(ImGuiCol.WindowBg, 1));
        }

        IMGUI_GLFW.init(handle, false); // later
        IMGUI_GL3.init();
    }

    public static void renderFrame() {
        IMGUI_GLFW.newFrame();
        ImGui.newFrame();

        ImGuiRenderCallback.EVENT.invoker().render();

        ImGui.render();
        IMGUI_GL3.renderDrawData(ImGui.getDrawData());

        if (ImGui.getIO().hasConfigFlags(ImGuiConfigFlags.ViewportsEnable)) {
            final long backupWindowPtr = glfwGetCurrentContext();
            ImGui.updatePlatformWindows();
            ImGui.renderPlatformWindowsDefault();
            glfwMakeContextCurrent(backupWindowPtr);
        }
    }
}
