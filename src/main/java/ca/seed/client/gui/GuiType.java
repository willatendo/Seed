package ca.seed.client.gui;

import ca.seed.client.render.renderer.Renderer;
import com.google.common.collect.Maps;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public final class GuiType<T> {
    private static final Logger LOGGER = LogManager.getLogger(GuiType.class);
    private static final Map<String, GuiType<?>> GUI_TYPES = Maps.newHashMap();

    private final String name;
    private final Gui gui;

    public static <T> GuiType<T> create(String name, Gui gui) {
        if (!GUI_TYPES.containsKey(name)) {
            GuiType<T> guiType = new GuiType<>(name, gui);
            GUI_TYPES.put(name, guiType);
            return guiType;
        }
        LOGGER.error("Asset type '{}' already exists!", name);
        throw new Error();
    }

    private GuiType(String name, Gui gui) {
        this.name = name;
        this.gui = gui;
    }

    public Gui getGui() {
        return this.gui;
    }

    public void mouseClicked(int mouseX, int mouseY, int button, int action, int modifiers) {
        this.gui.mouseClicked(mouseX, mouseY, button, action, modifiers);
    }

    public void render(Renderer renderer, int mouseX, int mouseY) {
        this.gui.render(renderer, mouseX, mouseY);
    }
}
