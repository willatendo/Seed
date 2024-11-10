package ca.seed.client.render.entity;

import ca.seed.client.render.renderer.Renderer;
import ca.seed.server.entity.Entity;

public class EntityRender {
    private final Entity entity;
    private final String texture;

    public EntityRender(Entity entity, String texture) {
        this.entity = entity;
        this.texture = texture;
    }

    public void render(Renderer renderer) {
        renderer.render("lo", "missing", this.entity.getX(), this.entity.getY(), this.entity.getZ());
    }
}
