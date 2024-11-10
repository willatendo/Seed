package ca.seed.client.render.renderer;

import ca.seed.client.options.ClientOptions;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL30;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public final class Plane {
    private final int vaoId;

    public static Plane create(int texture, int color, float alpha, int x, int y, int width, int height, float[] uv) {
        return new Plane(texture, color, alpha, x, y, width, height, uv);
    }

    public static Plane create(int texture, int color, float alpha, int x, int y, int width, int height, int u, int v) {
        return new Plane(texture, color, alpha, x, y, width, height, Renderer.createUV(width, height, width, height, u, v));
    }

    private Plane(int texture, int color, float alpha, int x, int y, int width, int height, float[] uv) {
        float red = ((color >> 16) & 0xFF) / 255.0F;
        float green = ((color >> 8) & 0xFF) / 255.0F;
        float blue = (color & 0xFF) / 255.0F;
        float[] vertexArray = {
                /*
                 * OX
                 * XX
                 */
                x, ClientOptions.HEIGHT - y, 0.0F, red, green, blue, alpha, uv[0], uv[1], texture,
                /*
                 * XX
                 * OX
                 */
                x, ClientOptions.HEIGHT - (y + height), 0.0F, red, green, blue, alpha, uv[2], uv[3], texture,
                /*
                 * XX
                 * XO
                 */
                x + width, ClientOptions.HEIGHT - (y + height), 0.0F, red, green, blue, alpha, uv[4], uv[5], texture,
                /*
                 * XO
                 * XX
                 */
                x + width, ClientOptions.HEIGHT - y, 0.0F, red, green, blue, alpha, uv[6], uv[7], texture};
        int[] elementArray = new int[]{0, 1, 3, 3, 1, 2};

        this.vaoId = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(this.vaoId);

        FloatBuffer floatBuffer = BufferUtils.createFloatBuffer(vertexArray.length);
        floatBuffer.put(vertexArray).flip();

        int vboId = GL30.glGenBuffers();
        GL30.glBindBuffer(GL30.GL_ARRAY_BUFFER, vboId);
        GL30.glBufferData(GL30.GL_ARRAY_BUFFER, floatBuffer, GL30.GL_STATIC_DRAW);

        IntBuffer intBuffer = BufferUtils.createIntBuffer(elementArray.length);
        intBuffer.put(elementArray).flip();

        int eboId = GL30.glGenBuffers();
        GL30.glBindBuffer(GL30.GL_ELEMENT_ARRAY_BUFFER, eboId);
        GL30.glBufferData(GL30.GL_ELEMENT_ARRAY_BUFFER, intBuffer, GL30.GL_STATIC_DRAW);

        int positionSize = 3;
        int colorSize = 4;
        int uvSize = 2;
        int textureSize = 1;
        int floatSizeInBytes = 4;
        int vertexSizeInBytes = (positionSize + colorSize + uvSize + textureSize) * floatSizeInBytes;
        GL30.glVertexAttribPointer(0, positionSize, GL30.GL_FLOAT, false, vertexSizeInBytes, 0);
        GL30.glEnableVertexAttribArray(0);

        GL30.glVertexAttribPointer(1, colorSize, GL30.GL_FLOAT, false, vertexSizeInBytes, positionSize * floatSizeInBytes);
        GL30.glEnableVertexAttribArray(1);

        GL30.glVertexAttribPointer(2, uvSize, GL30.GL_FLOAT, false, vertexSizeInBytes, (positionSize + colorSize) * floatSizeInBytes);
        GL30.glEnableVertexAttribArray(2);

        GL30.glVertexAttribPointer(3, textureSize, GL30.GL_FLOAT, false, vertexSizeInBytes, (positionSize + colorSize + uvSize) * floatSizeInBytes);
        GL30.glEnableVertexAttribArray(3);
    }

    public void render() {
        GL30.glBindVertexArray(this.vaoId);

        GL30.glEnableVertexAttribArray(0);
        GL30.glEnableVertexAttribArray(1);
        GL30.glEnableVertexAttribArray(2);
        GL30.glEnableVertexAttribArray(3);

        GL30.glDrawElements(GL30.GL_TRIANGLES, 6, GL30.GL_UNSIGNED_INT, 0);

        GL30.glDisableVertexAttribArray(0);
        GL30.glDisableVertexAttribArray(1);
        GL30.glDisableVertexAttribArray(2);
        GL30.glDisableVertexAttribArray(3);

        GL30.glBindVertexArray(0);
    }
}
