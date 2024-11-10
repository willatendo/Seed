package ca.seed.client.gui.widget;

import ca.seed.client.render.renderer.Renderer;

public abstract class Widget {
    public abstract void render(Renderer renderer, int mouseX, int mouseY);

    public void mouseClicked(int mouseX, int mouseY, int button, int action, int modifiers) {
    }
}
