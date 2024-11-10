package ca.seed.client.render.renderer.special;

import org.joml.Matrix4f;

public interface SpecialRenderer {
    void render(Matrix4f projectionMatrix, Matrix4f viewMatrix, Matrix4f worldTransformationMatrix);
}
