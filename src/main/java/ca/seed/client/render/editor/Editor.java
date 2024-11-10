package ca.seed.client.render.editor;

import ca.seed.client.render.renderer.Renderer;

public abstract class Editor {
    public abstract String getTexture();

    public void render3d(Renderer renderer) {
        renderer.render("cube", this.getTexture(), 0.0F, -1.0F, 0.0F);
    }
}
