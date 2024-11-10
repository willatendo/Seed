package ca.seed.client.asset.mesh;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL30;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public final class Mesh {
    private final int vaoId;

    public Mesh(float[] vertexArrays, float[] uvArrays, float[] normalsArrays, int[] elementArray) {
        int arraySize = 3 + 2 + 3;
        int count = vertexArrays.length / 3;
        float[] vertexArray = new float[arraySize * count];

        for (int i = 0; i < count; i++) {
            int index = i * arraySize;
            vertexArray[index] = vertexArrays[i * 3];
            vertexArray[index + 1] = vertexArrays[i * 3 + 1];
            vertexArray[index + 2] = vertexArrays[i * 3 + 2];
            vertexArray[index + 3] = uvArrays[i * 2];
            vertexArray[index + 4] = uvArrays[i * 2 + 1];
        }

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
        int uvSize = 2;
        int normalSize = 3;
        int floatSizeInBytes = 4;
        int vertexSizeInBytes = (positionSize + uvSize + normalSize) * floatSizeInBytes;
        GL30.glVertexAttribPointer(0, positionSize, GL30.GL_FLOAT, false, vertexSizeInBytes, 0);
        GL30.glEnableVertexAttribArray(0);

        GL30.glVertexAttribPointer(1, uvSize, GL30.GL_FLOAT, false, vertexSizeInBytes, positionSize * floatSizeInBytes);
        GL30.glEnableVertexAttribArray(1);

        GL30.glVertexAttribPointer(2, normalSize, GL30.GL_FLOAT, false, vertexSizeInBytes, (positionSize + uvSize) * floatSizeInBytes);
        GL30.glEnableVertexAttribArray(2);
    }

    public void render() {
        GL30.glBindVertexArray(this.vaoId);

        GL30.glEnableVertexAttribArray(0);
        GL30.glEnableVertexAttribArray(1);
        GL30.glEnableVertexAttribArray(2);

        GL30.glDrawElements(GL30.GL_TRIANGLES, 6, GL30.GL_UNSIGNED_INT, 0);

        GL30.glDisableVertexAttribArray(0);
        GL30.glDisableVertexAttribArray(1);
        GL30.glDisableVertexAttribArray(2);

        GL30.glBindVertexArray(0);
    }
}
