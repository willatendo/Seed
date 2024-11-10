package ca.seed.client.render.editor;

import ca.seed.client.render.renderer.Renderer;

public enum Editors {
    CAKE("cake", new CakeEditor());

    private final String name;
    private final Editor editor;

    Editors(String name, Editor editor) {
        this.name = name;
        this.editor = editor;
    }

    public void render(Renderer renderer) {
        this.editor.render3d(renderer);
    }
}
