package ca.seed.client.asset.texture;

import ca.seed.client.resource.ResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL30;
import org.lwjgl.stb.STBImage;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public final class Texture {
    private static final Logger LOGGER = LogManager.getLogger(Texture.class);

    private final String name;

    private int textureId;
    private int width;
    private int height;

    public Texture(String name) {
        this.name = name;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void load() {
        this.textureId = GL30.glGenTextures();
        GL30.glBindTexture(GL30.GL_TEXTURE_2D, this.textureId);

        GL30.glTexParameteri(GL30.GL_TEXTURE_2D, GL30.GL_TEXTURE_WRAP_S, GL30.GL_REPEAT);
        GL30.glTexParameteri(GL30.GL_TEXTURE_2D, GL30.GL_TEXTURE_WRAP_T, GL30.GL_REPEAT);
        GL30.glTexParameteri(GL30.GL_TEXTURE_2D, GL30.GL_TEXTURE_MIN_FILTER, GL30.GL_NEAREST);
        GL30.glTexParameteri(GL30.GL_TEXTURE_2D, GL30.GL_TEXTURE_MAG_FILTER, GL30.GL_NEAREST);

        IntBuffer width = BufferUtils.createIntBuffer(1);
        IntBuffer height = BufferUtils.createIntBuffer(1);
        IntBuffer channels = BufferUtils.createIntBuffer(1);
        STBImage.stbi_set_flip_vertically_on_load(true);
        ByteBuffer image = STBImage.stbi_load(ResourceManager.getTexture(this.name).getPath(), width, height, channels, 0);

        if (image != null) {
            this.width = width.get(0);
            this.height = height.get(0);

            if (channels.get(0) == 3) {
                GL30.glTexImage2D(GL30.GL_TEXTURE_2D, 0, GL30.GL_RGB, width.get(0), height.get(0), 0, GL30.GL_RGB, GL30.GL_UNSIGNED_BYTE, image);
            } else if (channels.get(0) == 4) {
                GL30.glTexImage2D(GL30.GL_TEXTURE_2D, 0, GL30.GL_RGBA, width.get(0), height.get(0), 0, GL30.GL_RGBA, GL30.GL_UNSIGNED_BYTE, image);
            } else {
                LOGGER.error("Unknown number of channels");
            }
        } else {
            LOGGER.error("Could not load image '{}'", this.name);
        }
    }

    public void bind() {
        GL30.glBindTexture(GL30.GL_TEXTURE_2D, this.textureId);
    }

    public void unBind() {
        GL30.glBindTexture(GL30.GL_TEXTURE_2D, 0);
    }
}
