package ca.seed.client.window;

import ca.seed.client.options.ClientOptions;
import ca.seed.client.render.GameRenderer;
import org.lwjgl.glfw.Callbacks;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

import java.nio.IntBuffer;

public final class Window {
    private static Window instance;

    private long windowId;

    public static Window getInstance() {
        if (instance == null) {
            return instance = new Window();
        }
        return instance;
    }

    public void create(GameRenderer gameRenderer) {
        if (!GLFW.glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        GLFW.glfwDefaultWindowHints();
        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE);
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_TRUE);

        this.windowId = GLFW.glfwCreateWindow(ClientOptions.WIDTH, ClientOptions.HEIGHT, "SEED", MemoryUtil.NULL, MemoryUtil.NULL);
        if (this.windowId == MemoryUtil.NULL) {
            throw new RuntimeException("Failed to create the GLFW window");
        }

        try (MemoryStack memoryStack = MemoryStack.stackPush()) {
            IntBuffer width = memoryStack.mallocInt(1);
            IntBuffer height = memoryStack.mallocInt(1);

            GLFW.glfwGetWindowSize(this.windowId, width, height);

            GLFWVidMode vidMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());

            GLFW.glfwSetWindowPos(this.windowId, (vidMode.width() - width.get(0)) / 2, (vidMode.height() - height.get(0)) / 2);
        }

        GLFW.glfwMakeContextCurrent(this.windowId);
        GLFW.glfwSwapInterval(1);

        GLFW.glfwShowWindow(this.windowId);

        GL.createCapabilities();

        MouseListener mouseListener = MouseListener.getInstance(gameRenderer);
        GLFW.glfwSetCursorPosCallback(this.windowId, mouseListener::mousePosCallback);
        GLFW.glfwSetMouseButtonCallback(this.windowId, mouseListener::mouseButtonCallback);
        GLFW.glfwSetScrollCallback(this.windowId, mouseListener::mouseScrollCallback);

        KeyboardListener keyboardListener = KeyboardListener.getInstance(gameRenderer);
        GLFW.glfwSetKeyCallback(this.windowId, keyboardListener::keyPressedCallback);
        GLFW.glfwSetCharModsCallback(this.windowId, keyboardListener::charModifiedCallback);

        GL30.glEnable(GL30.GL_BLEND);
        GL30.glBlendFunc(GL30.GL_ONE, GL30.GL_ONE_MINUS_SRC_ALPHA);

        // GL30.glPolygonMode(GL30.GL_FRONT_AND_BACK, GL30.GL_LINE);
    }

    public boolean shouldClose() {
        return GLFW.glfwWindowShouldClose(this.windowId);
    }

    public void updateDisplay(GameRenderer gameRenderer) {
        GLFW.glfwPollEvents();

        GL11.glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

        gameRenderer.render();

        GLFW.glfwSwapBuffers(this.windowId);
    }

    public void destroy() {
        Callbacks.glfwFreeCallbacks(this.windowId);
        GLFW.glfwDestroyWindow(this.windowId);

        GLFW.glfwTerminate();
    }
}
