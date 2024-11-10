package ca.seed.client.gui.popups;

import ca.seed.client.options.ClientOptions;
import ca.seed.client.gui.Gui;
import ca.seed.client.gui.GuiManager;
import ca.seed.client.gui.Guis;
import ca.seed.client.gui.widget.button.Button;
import ca.seed.client.render.renderer.Renderer;
import ca.seed.client.resource.LanguageManager;
import org.apache.logging.log4j.LogManager;

public class OptionsGui extends Gui {
    @Override
    public void init() {
        int windowWidth = ClientOptions.WIDTH;
        int windowHeight = ClientOptions.HEIGHT;
        int x = (windowWidth / 2) - (150 / 2);
        int y = (windowHeight / 2) - (200 / 2);
        GuiManager guiManager = this.seed.getGuiManager();
        this.addWidget(new Button(LanguageManager.getTranslation("gui.options.button.seed_guide"), x, y + 60, 150, 25, () -> LogManager.getLogger().info("Click")));
        this.addWidget(new Button(LanguageManager.getTranslation("gui.options.button.seed_website"), x, y + 90, 150, 25, () -> LogManager.getLogger().info("Click")));
        this.addWidget(new Button(LanguageManager.getTranslation("gui.options.button.settings"), x, y + 120, 150, 25, () -> guiManager.addGui(1, Guis.SETTINGS_GUI)));
        this.addWidget(new Button(LanguageManager.getTranslation("gui.options.button.quit_game"), x, y + 150, 150, 25, this.seed::stop));
    }

    @Override
    public void render(Renderer renderer, int mouseX, int mouseY) {
        int windowWidth = renderer.getWindowWidth();
        int windowHeight = renderer.getWindowHeight();
        int x = (windowWidth / 2) - (200 / 2);
        int y = (windowHeight / 2) - (200 / 2);
        renderer.render(0x222222, x, y, 200, 200);
        renderer.drawCenteredSeedy(LanguageManager.getTranslation("gui.options"), windowWidth, y + 10, 2);
        this.renderWidgets(renderer, mouseX, mouseY);
    }
}
