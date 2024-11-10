package ca.seed.client.window;

import ca.seed.client.render.GameRenderer;
import org.lwjgl.glfw.GLFW;

public final class KeyboardListener {
    private static KeyboardListener instance;

    private final boolean[] keys = new boolean[350];
    private final GameRenderer gameRenderer;

    public static KeyboardListener getInstance(GameRenderer gameRenderer) {
        if (instance == null) {
            return instance = new KeyboardListener(gameRenderer);
        }
        return instance;
    }

    private KeyboardListener(GameRenderer gameRenderer) {
        this.gameRenderer = gameRenderer;
    }

    public boolean isKeyDown(int keyCode) {
        return this.keys[keyCode];
    }

    public void keyPressedCallback(long windowId, int keyCode, int scanCode, int action, int mods) {
        if (action == GLFW.GLFW_PRESS || action == GLFW.GLFW_REPEAT) {
            this.keys[keyCode] = true;
            this.gameRenderer.keyPressed(keyCode, scanCode, action, mods);
        } else if (action == GLFW.GLFW_RELEASE) {
            this.keys[keyCode] = false;
            this.gameRenderer.keyReleased(keyCode, scanCode, action, mods);
        }
    }

    public void charModifiedCallback(long windowId, int codePoint, int mods) {
    }
}
