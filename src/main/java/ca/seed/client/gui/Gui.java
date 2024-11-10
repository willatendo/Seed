package ca.seed.client.gui;

import ca.seed.client.Seed;
import ca.seed.client.gui.widget.Widget;
import ca.seed.client.render.renderer.Renderer;
import ca.seed.client.render.renderer.special.SpecialRenderer;
import com.google.common.collect.Lists;

import java.util.List;

public abstract class Gui {
    private final List<SpecialRenderer> specialRenderers = Lists.newArrayList();
    private final List<Widget> widgets = Lists.newArrayList();
    protected final Seed seed = Seed.getInstance();

    public Gui() {
        this.init();
    }

    public abstract void render(Renderer renderer, int mouseX, int mouseY);

    public void mouseClicked(int mouseX, int mouseY, int button, int action, int modifiers) {
        this.widgets.forEach(widget -> widget.mouseClicked(mouseX, mouseY, button, action, modifiers));
    }

    protected void addSpecialRenderer(SpecialRenderer specialRenderer) {
        this.specialRenderers.add(specialRenderer);
    }

    protected Widget addWidget(Widget widget) {
        this.widgets.add(widget);
        return widget;
    }

    protected void renderSpecials(Renderer renderer) {
        this.specialRenderers.forEach(specialRenderer -> specialRenderer.render(renderer.projectionMatrix3d(), renderer.viewMatrix3d(), renderer.worldTransformationMatrix()));
    }

    protected void renderWidgets(Renderer renderer, int mouseX, int mouseY) {
        this.widgets.forEach(widget -> widget.render(renderer, mouseX, mouseY));
    }

    public void init() {
    }
}
