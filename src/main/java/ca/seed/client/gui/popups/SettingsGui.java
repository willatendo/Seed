package ca.seed.client.gui.popups;

import ca.seed.client.gui.Gui;
import ca.seed.client.render.renderer.Renderer;

public class SettingsGui extends Gui {
    @Override
    public void render(Renderer renderer, int mouseX, int mouseY) {
        int windowWidth = renderer.getWindowWidth();
        int windowHeight = renderer.getWindowHeight();
        int widthCenter = (windowWidth / 2);
        int x = widthCenter - (350 / 2);
        int y = (windowHeight / 2) - (350 / 2);
        renderer.render(0x222222, x, y, 350, 350);
        String title = "Settings";
        int textSize = 2;
        renderer.drawSeedy(title, widthCenter - renderer.getSeedyWidth(title, textSize), y + 10, textSize);
        this.renderWidgets(renderer, mouseX, mouseY);
    }
}
