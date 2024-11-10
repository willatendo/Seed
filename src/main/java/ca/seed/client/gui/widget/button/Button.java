package ca.seed.client.gui.widget.button;

import ca.seed.client.gui.widget.Widget;
import ca.seed.client.render.renderer.Renderer;
import org.apache.logging.log4j.LogManager;


public class Button extends Widget {
    private final String label;
    private final int x;
    private final int y;
    private final int width;
    private final int height;
    private final OnClick onClick;

    public Button(String label, int x, int y, int width, int height, OnClick onClick) {
        this.label = label;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.onClick = onClick;
    }

    @Override
    public void render(Renderer renderer, int mouseX, int mouseY) {
        int color = this.hovered(mouseX, mouseY) ? 0x444444 : 0x999999;
        renderer.render(color, this.x, this.y, this.width, this.height);
        int textSize = 1;
        renderer.drawSeedy(this.label, this.x + (this.width / 2) - (renderer.getSeedyWidth(this.label, textSize) / 2), this.y + (this.height / 2) - (renderer.getHeight(textSize) / 2), textSize);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int button, int action, int modifiers) {
        if (this.hovered(mouseX, mouseY)) {
            this.onClick.click();
        }
    }

    private boolean hovered(int mouseX, int mouseY) {
        return (mouseX >= this.x && mouseX <= this.x + this.width) && (mouseY >= this.y && mouseY <= this.y + this.height);
    }

    @FunctionalInterface
    public interface OnClick {
        void click();
    }
}
