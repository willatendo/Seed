package ca.seed.client.gui.popups;

import ca.seed.client.gui.Gui;
import ca.seed.client.render.renderer.Renderer;

public class ConsoleGui extends Gui {
    @Override
    public void render(Renderer renderer, int mouseX, int mouseY) {
        renderer.render(0xAAAAAA, 10, 10, 300, 200);
        renderer.render(0x555555, 20, 180, 280, 20);
        renderer.drawSeedy("/", 23, 182, 1);
    }
}
