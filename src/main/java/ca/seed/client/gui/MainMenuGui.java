package ca.seed.client.gui;

import ca.seed.client.gui.widget.button.OptionsButton;
import ca.seed.client.options.ClientOptions;
import ca.seed.client.render.renderer.Renderer;
import ca.seed.client.render.renderer.special.galaxy.GalaxyRenderer;

public class MainMenuGui extends Gui {
    @Override
    public void init() {
        this.addWidget(new OptionsButton(0xFF0000, 0x880000, 0, ClientOptions.HEIGHT - 50, 50, 50, () -> this.seed.getGuiManager().addGui(1, Guis.EXIT_GUI)));
        this.addWidget(new OptionsButton(0x0000FF, 0x000088, 75, ClientOptions.HEIGHT - 25, 25, 25, () -> this.seed.getGuiManager().addGui(1, Guis.EXIT_GUI)));
        this.addWidget(new OptionsButton(0x0000FF, 0x000088, 125, ClientOptions.HEIGHT - 25, 50, 25, () -> this.seed.getGuiManager().addGui(1, Guis.EXIT_GUI)));

        this.addSpecialRenderer(new GalaxyRenderer(0.0F, 0.0F, 0.0F));
    }

    @Override
    public void render(Renderer renderer, int mouseX, int mouseY) {
        renderer.render(0x000000, 0, 0, ClientOptions.WIDTH, ClientOptions.HEIGHT);

        this.renderSpecials(renderer);

        renderer.render(0x222222, 0, 0, 150, 300);
        renderer.drawCenteredSeedy("SEED", 150, 5, 3);

        renderer.render(0x222222, 0, ClientOptions.HEIGHT - 25, 200, ClientOptions.HEIGHT);

        this.renderWidgets(renderer, mouseX, mouseY);
    }
}
