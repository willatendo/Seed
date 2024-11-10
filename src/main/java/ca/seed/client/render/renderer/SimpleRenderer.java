package ca.seed.client.render.renderer;

import ca.seed.client.asset.AssetTypes;
import ca.seed.client.asset.font.CharacterInformation;
import ca.seed.client.asset.font.Font;
import ca.seed.client.asset.shader.Shaders;
import ca.seed.client.asset.texture.Texture;
import ca.seed.client.render.GameRenderer;
import ca.seed.client.render.renderer.font.FontDrawingInformation;
import com.google.common.collect.Maps;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL30;

import java.util.Map;

public final class SimpleRenderer implements Renderer {
    private final Map<String, FontDrawingInformation> allDrawnText = Maps.newHashMap();
    private final GameRenderer gameRenderer;

    public SimpleRenderer(GameRenderer gameRenderer) {
        this.gameRenderer = gameRenderer;
    }

    @Override
    public int getWidth(Font font, String message, int size) {
        if (this.allDrawnText.containsKey(message)) {
            return this.allDrawnText.get(message).fullWidth() * size;
        }
        return font.setup(message).fullWidth() * size;
    }

    @Override
    public void render(String texture, int color, float alpha, int x, int y, int width, int height, int u, int v) {
        Shaders.GUI_SHADER.bind();
        if (texture != null) {
            AssetTypes.TEXTURES.get(texture).bind();
        }

        Shaders.GUI_SHADER.uploadMatrix4f("uProjectionMatrix", Renderer.CAMERA.getProjectionMatrix2d());
        Shaders.GUI_SHADER.uploadMatrix4f("uViewMatrix", Renderer.CAMERA.getViewMatrix2d());
        Shaders.GUI_SHADER.uploadTexture("uTexture", 0);

        Plane.create(texture != null ? 1 : 0, color, alpha, x, y, width, height, u, v).render();

        if (texture != null) {
            AssetTypes.TEXTURES.get(texture).unBind();
        }
        Shaders.GUI_SHADER.unBind();
    }

    @Override
    public void render(String model, String texture, float x, float y, float z) {
        GL30.glEnable(GL30.GL_DEPTH_TEST);
        Shaders.WORLD_SHADER.bind();
        AssetTypes.TEXTURES.get(texture).bind();

        Shaders.WORLD_SHADER.uploadMatrix4f("uProjectionMatrix", this.projectionMatrix3d());
        Shaders.WORLD_SHADER.uploadMatrix4f("uViewMatrix", this.viewMatrix3d());
        Shaders.WORLD_SHADER.uploadMatrix4f("uTransformationMatrix", this.worldTransformationMatrix().translate(new Vector3f(x, y, z)));
        Shaders.WORLD_SHADER.uploadTexture("uTexture", 0);

        AssetTypes.MESHES.get(model).render();

        AssetTypes.TEXTURES.get(texture).unBind();
        Shaders.WORLD_SHADER.unBind();
        GL30.glDisable(GL30.GL_DEPTH_TEST);
    }

    @Override
    public void drawString(Font font, String message, int x, int y, int color, float alpha, int size) {
        FontDrawingInformation fontDrawingInformation = font.setup(message);
        this.allDrawnText.put(message, fontDrawingInformation);

        Shaders.GUI_SHADER.bind();
        fontDrawingInformation.textures().forEach(Texture::bind);

        Shaders.GUI_SHADER.uploadMatrix4f("uProjectionMatrix", Renderer.CAMERA.getProjectionMatrix2d());
        Shaders.GUI_SHADER.uploadMatrix4f("uViewMatrix", Renderer.CAMERA.getViewMatrix2d());
        Shaders.GUI_SHADER.uploadTexture("uTexture", 0);

        Map<Integer, CharacterInformation> allCharacterInformation = fontDrawingInformation.allCharacterInformation();
        int xPlace = x;
        for (int i = 0; i < fontDrawingInformation.length(); i++) {
            CharacterInformation characterInformation = allCharacterInformation.get(i);
            int width = characterInformation.width() * size;
            int height = characterInformation.height() * size;
            int yOffset = characterInformation.yOffset() * size;
            Plane.create(1, color, alpha, xPlace, y + (16 * size) - height - yOffset, width, height, characterInformation.uv()).render();
            xPlace += width;
        }

        fontDrawingInformation.textures().forEach(Texture::unBind);
        Shaders.GUI_SHADER.unBind();
    }
}
