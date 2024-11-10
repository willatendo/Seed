package ca.seed.client.gui;

import ca.seed.client.render.renderer.Renderer;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

public final class GuiManager {
    private final Map<Integer, GuiType<?>> integerToGuiType = Maps.newHashMap();
    private final Map<GuiType<?>, Integer> guiTypeToInteger = Maps.newHashMap();

    public List<GuiType<?>> getGuiTypes() {
        return this.integerToGuiType.values().stream().toList();
    }

    public void addGui(int layer, GuiType<?> guiType) {
        if (layer < 0) {
            layer = 0;
        }
        if (layer > 6) {
            layer = 6;
        }
        if (this.integerToGuiType.containsKey(layer)) {
            this.integerToGuiType.replace(layer, guiType);
            this.guiTypeToInteger.replace(guiType, layer);
            return;
        }
        this.integerToGuiType.put(layer, guiType);
        this.guiTypeToInteger.put(guiType, layer);
    }

    public void removeGui(GuiType<?> guiType) {
        this.integerToGuiType.remove(this.guiTypeToInteger.get(guiType));
        this.guiTypeToInteger.remove(guiType);
    }

    public boolean hasGui(GuiType<?> guiType) {
        return this.integerToGuiType.containsValue(guiType);
    }

    public void mouseClicked(int mouseX, int mouseY, int button, int action, int modifiers) {
        for (int i = 0; i < 6; i++) {
            if (this.integerToGuiType.containsKey(i)) {
                this.integerToGuiType.get(i).mouseClicked(mouseX, mouseY, button, action, modifiers);
            }
        }
    }

    public void render(Renderer renderer, int mouseX, int mouseY) {
        for (int i = 0; i < 6; i++) {
            if (this.integerToGuiType.containsKey(i)) {
                this.integerToGuiType.get(i).render(renderer, mouseX, mouseY);
            }
        }
    }
}
