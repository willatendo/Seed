package ca.seed.client.gui.widget.button;

import ca.seed.client.gui.widget.Widget;
import ca.seed.client.render.renderer.Renderer;
import org.apache.logging.log4j.LogManager;

public class OptionsButton extends Widget {
    private final int color;
    private final int hoveredColor;
    private final int x;
    private final int y;
    private final int width;
    private final int height;
    private final Button.OnClick onClick;

    public OptionsButton(int color, int hoveredColor, int x, int y, int width, int height, Button.OnClick onClick) {
        this.color = color;
        this.hoveredColor = hoveredColor;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.onClick = onClick;
    }

    @Override
    public void render(Renderer renderer, int mouseX, int mouseY) {
        renderer.render(this.hovered(mouseX, mouseY) ? this.hoveredColor : color, this.x, this.y, this.width, this.height);
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
