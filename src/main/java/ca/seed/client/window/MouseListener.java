package ca.seed.client.window;

import ca.seed.client.render.GameRenderer;
import org.lwjgl.glfw.GLFW;

public final class MouseListener {
    private static MouseListener instance;

    private final boolean[] mouseButtonPressed = new boolean[3];
    private final GameRenderer gameRenderer;
    private double xPos;
    private double yPos;
    private double lastXPos;
    private double lastYPos;
    private double scrollX;
    private double scrollY;
    private boolean isDragging;

    public static MouseListener getInstance(GameRenderer gameRenderer) {
        if (instance == null) {
            return instance = new MouseListener(gameRenderer);
        }
        return instance;
    }

    private MouseListener(GameRenderer gameRenderer) {
        this.gameRenderer = gameRenderer;
        this.scrollX = 0.0D;
        this.scrollY = 0.0D;
        this.xPos = 0.0D;
        this.yPos = 0.0D;
        this.lastXPos = 0.0D;
        this.lastYPos = 0.0D;
    }

    public double getXPos() {
        return this.xPos;
    }

    public double getYPos() {
        return this.yPos;
    }

    public double getDXPos() {
        return this.lastXPos - this.xPos;
    }

    public double getDYPos() {
        return this.lastYPos - this.yPos;
    }

    public void mousePosCallback(long windowId, double xPos, double yPos) {
        this.lastXPos = this.xPos;
        this.lastYPos = this.yPos;
        this.xPos = xPos;
        this.yPos = yPos;
        this.isDragging = this.mouseButtonPressed[0] || this.mouseButtonPressed[1] || this.mouseButtonPressed[2];
    }

    public void mouseButtonCallback(long windowId, int button, int action, int modifiers) {
        if (action == GLFW.GLFW_PRESS) {
            if (button < this.mouseButtonPressed.length) {
                this.mouseButtonPressed[button] = true;
                this.gameRenderer.mouseClicked((int) this.getXPos(), (int) this.getYPos(), button, action, modifiers);
            }
        } else if (action == GLFW.GLFW_RELEASE) {
            if (button < this.mouseButtonPressed.length) {
                this.mouseButtonPressed[button] = false;
                this.isDragging = false;
            }
        }
    }

    public void mouseScrollCallback(long windowId, double xOffset, double yOffset) {
        this.scrollX = xOffset;
        this.scrollY = yOffset;
    }
}
