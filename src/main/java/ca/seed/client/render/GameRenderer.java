package ca.seed.client.render;

import ca.seed.client.Seed;
import ca.seed.client.gui.GuiManager;
import ca.seed.client.gui.Guis;
import ca.seed.client.render.entity.EntityRender;
import ca.seed.client.render.renderer.Renderer;
import ca.seed.client.render.renderer.SimpleRenderer;
import ca.seed.client.window.KeyboardListener;
import ca.seed.client.window.MouseListener;
import ca.seed.server.entity.Entity;
import com.google.common.collect.Lists;
import org.apache.logging.log4j.LogManager;
import org.lwjgl.glfw.GLFW;

import java.util.List;

public final class GameRenderer {
    private final KeyboardListener keyboardListener = KeyboardListener.getInstance(this);
    private final MouseListener mouseListener = MouseListener.getInstance(this);
    private final List<Entity> entities = Lists.newArrayList();
    private final Seed seed = Seed.getInstance();
    private final GuiManager guiManager = this.seed.getGuiManager();

    public void create() {
        this.entities.add(new Entity());
    }

    public void keyPressed(int keyCode, int scanCode, int action, int mods) {
    }

    public void keyReleased(int keyCode, int scanCode, int action, int mods) {
        if (keyCode == GLFW.GLFW_KEY_LEFT_SHIFT) {
            if (this.guiManager.hasGui(Guis.CONSOLE_GUI)) {
                this.guiManager.removeGui(Guis.CONSOLE_GUI);
            } else {
                this.guiManager.addGui(2, Guis.CONSOLE_GUI);
            }
        }
        if (keyCode == GLFW.GLFW_KEY_ESCAPE) {
            if (this.guiManager.hasGui(Guis.EXIT_GUI)) {
                this.guiManager.removeGui(Guis.EXIT_GUI);
            } else {
                this.guiManager.addGui(1, Guis.EXIT_GUI);
            }
        }
    }

    public void mouseClicked(int mouseX, int mouseY, int button, int action, int modifiers) {
        this.guiManager.mouseClicked(mouseX, mouseY, button, action, modifiers);
    }

    public void render() {
        SimpleRenderer simpleRenderer = new SimpleRenderer(this);
        if (this.keyboardListener.isKeyDown(GLFW.GLFW_KEY_W)) {
            Renderer.CAMERA.movePosition(0.0F, 0.0F, -0.05F);
        }
        if (this.keyboardListener.isKeyDown(GLFW.GLFW_KEY_S)) {
            Renderer.CAMERA.movePosition(0.0F, 0.0F, 0.05F);
        }
        if (this.keyboardListener.isKeyDown(GLFW.GLFW_KEY_D)) {
            Renderer.CAMERA.movePosition(0.05F, 0.0F, 0.0f);
        }
        if (this.keyboardListener.isKeyDown(GLFW.GLFW_KEY_A)) {
            Renderer.CAMERA.movePosition(-0.05F, 0.0F, 0.0F);
        }
        if (this.keyboardListener.isKeyDown(GLFW.GLFW_KEY_Q)) {
            Renderer.CAMERA.moveRotation(0.0F, -2.5F, 0.0F);
        }
        if (this.keyboardListener.isKeyDown(GLFW.GLFW_KEY_E)) {
            Renderer.CAMERA.moveRotation(0.0F, 2.5F, 0.0F);
        }

        this.entities.forEach(entity -> {
            EntityRender entityRender = new EntityRender(entity, "missing");
            entityRender.render(simpleRenderer);
        });

        this.guiManager.render(simpleRenderer, (int) this.mouseListener.getXPos(), (int) this.mouseListener.getYPos());

        simpleRenderer.drawSeedy("v0.0.1", 0, 0, 0xFF0000, 1);
    }
}
