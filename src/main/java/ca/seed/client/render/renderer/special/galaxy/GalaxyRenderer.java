package ca.seed.client.render.renderer.special.galaxy;

import ca.seed.client.asset.AssetTypes;
import ca.seed.client.asset.shader.Shaders;
import ca.seed.client.render.renderer.special.SpecialRenderer;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL30;

public class GalaxyRenderer implements SpecialRenderer {
    private final float x;
    private final float y;
    private final float z;

    public GalaxyRenderer(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public void render(Matrix4f projectionMatrix, Matrix4f viewMatrix, Matrix4f worldTransformationMatrix) {
        GL30.glEnable(GL30.GL_DEPTH_TEST);
        Shaders.GALAXY_SHADER.bind();

        Shaders.GALAXY_SHADER.uploadMatrix4f("uProjectionMatrix", projectionMatrix);
        Shaders.GALAXY_SHADER.uploadMatrix4f("uViewMatrix", viewMatrix);
        Shaders.GALAXY_SHADER.uploadMatrix4f("uTransformationMatrix", worldTransformationMatrix.translate(new Vector3f(this.x, this.y, this.z)));

        Shaders.GALAXY_SHADER.unBind();
        GL30.glDisable(GL30.GL_DEPTH_TEST);
    }
}
