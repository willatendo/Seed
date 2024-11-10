package ca.seed.client.asset.shader;

import ca.seed.client.resource.ResourceManager;
import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL30;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.FloatBuffer;

public final class Shader {
    private final String vertexShader;
    private final String fragmentShader;

    private int shaderId;
    private int vertexId;
    private int fragmentId;

    private boolean active = false;

    public Shader(String vertexShader, String fragmentShader) {
        this.vertexShader = vertexShader;
        this.fragmentShader = fragmentShader;
    }

    public void compileAndLink() {
        this.vertexId = GL30.glCreateShader(GL30.GL_VERTEX_SHADER);
        GL30.glShaderSource(this.vertexId, this.readShader(this.vertexShader));
        GL30.glCompileShader(this.vertexId);

        this.fragmentId = GL30.glCreateShader(GL30.GL_FRAGMENT_SHADER);
        GL30.glShaderSource(this.fragmentId, this.readShader(this.fragmentShader));
        GL30.glCompileShader(this.fragmentId);

        this.shaderId = GL30.glCreateProgram();
        GL30.glAttachShader(this.shaderId, this.vertexId);
        GL30.glAttachShader(this.shaderId, this.fragmentId);
        GL30.glLinkProgram(this.shaderId);
    }

    public void bind() {
        if (!this.active) {
            GL30.glUseProgram(this.shaderId);
            this.active = true;
        }
    }

    public void unBind() {
        GL30.glUseProgram(0);
        this.active = false;
    }

    private String readShader(String path) {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(ResourceManager.getShader(path)));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append("\n");
            }
        } catch (IOException e) {
        }
        return stringBuilder.toString();
    }

    public void uploadMatrix4f(String name, Matrix4f matrix4f) {
        int variableId = GL30.glGetUniformLocation(this.shaderId, name);
        FloatBuffer floatBuffer = BufferUtils.createFloatBuffer(16);
        matrix4f.get(floatBuffer);
        GL30.glUniformMatrix4fv(variableId, false, floatBuffer);
    }

    public void uploadTexture(String name, int texture) {
        int varLocation = GL30.glGetUniformLocation(this.shaderId, name);
        GL30.glUniform1i(varLocation, texture);
    }
}
