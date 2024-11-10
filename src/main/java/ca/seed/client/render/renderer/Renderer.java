package ca.seed.client.render.renderer;

import ca.seed.client.asset.font.Font;
import ca.seed.client.asset.font.Fonts;
import ca.seed.client.options.ClientOptions;
import ca.seed.client.render.Camera;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public interface Renderer {
    Camera CAMERA = new Camera(0.0F, 0.0F, 5.0F, 0.0F, 0.0F, 0.0F);

    default int getWindowWidth() {
        return ClientOptions.WIDTH;
    }

    default int getWindowHeight() {
        return ClientOptions.HEIGHT;
    }

    default Matrix4f projectionMatrix3d() {
        float aspectRatio = (float) ClientOptions.WIDTH / ClientOptions.HEIGHT;
        return new Matrix4f().perspective(ClientOptions.FOV, aspectRatio, ClientOptions.Z_NEAR, ClientOptions.Z_FAR);
    }

    default Matrix4f viewMatrix3d() {
        return CAMERA.getViewMatrix3d();
    }

    default Matrix4f worldTransformationMatrix() {
        return new Matrix4f().identity().translate(new Vector3f(0.0F, 0.0F, 0.0F)).rotateX((float) Math.toRadians(0.0F)).rotateY((float) Math.toRadians(0.0F)).rotateZ((float) Math.toRadians(0.0F)).scale(1);
    }

    int getWidth(Font font, String message, int size);

    default int getSeedyWidth(String message, int size) {
        return this.getWidth(Fonts.SEEDY, message, size);
    }

    default int getHeight(int size) {
        return 16 * size;
    }

    void render(String texture, int color, float alpha, int x, int y, int width, int height, int u, int v);

    default void render(String texture, float alpha, int x, int y, int width, int height, int u, int v) {
        this.render(texture, 0xFFFFFF, alpha, x, y, width, height, u, v);
    }

    default void render(String texture, int color, int x, int y, int width, int height, int u, int v) {
        this.render(texture, color, 1.0F, x, y, width, height, u, v);
    }

    default void render(String texture, int x, int y, int width, int height, int u, int v) {
        this.render(texture, 0xFFFFFF, 1.0F, x, y, width, height, u, v);
    }

    default void render(String texture, int color, float alpha, int x, int y, int width, int height) {
        this.render(texture, color, alpha, x, y, width, height, 0, 0);
    }

    default void render(String texture, float alpha, int x, int y, int width, int height) {
        this.render(texture, 0xFFFFFF, alpha, x, y, width, height);
    }

    default void render(String texture, int color, int x, int y, int width, int height) {
        this.render(texture, color, 1.0F, x, y, width, height);
    }

    default void render(String texture, int x, int y, int width, int height) {
        this.render(texture, 0xFFFFFF, 1.0F, x, y, width, height);
    }

    default void render(int color, float alpha, int x, int y, int width, int height) {
        this.render(null, color, alpha, x, y, width, height);
    }

    default void render(int color, int x, int y, int width, int height) {
        this.render(color, 1.0F, x, y, width, height);
    }

    default void render(float alpha, int x, int y, int width, int height) {
        this.render(0xFFFFFF, alpha, x, y, width, height);
    }

    void render(String model, String texture, float x, float y, float z);

    void drawString(Font font, String message, int x, int y, int color, float alpha, int size);

    default void drawSeedy(String message, int x, int y, int color, float alpha, int size) {
        this.drawString(Fonts.SEEDY, message, x, y, color, alpha, size);
    }

    default void drawSeedy(String message, int x, int y, int color, int size) {
        this.drawSeedy(message, x, y, color, 1.0F, size);
    }

    default void drawSeedy(String message, int x, int y, float alpha, int size) {
        this.drawSeedy(message, x, y, 0xFFFFFF, alpha, size);
    }

    default void drawSeedy(String message, int x, int y, int size) {
        this.drawSeedy(message, x, y, 0xFFFFFF, 1.0F, size);
    }

    default void drawCenteredString(Font font, String message, int width, int y, int color, float alpha, int size) {
        int x = (width / 2) - (this.getWidth(font, message, size) / 2);
        this.drawString(font, message, x, y, color, alpha, size);
    }

    default void drawCenteredSeedy(String message, int width, int y, int color, float alpha, int size) {
        this.drawCenteredString(Fonts.SEEDY, message, width, y, color, alpha, size);
    }

    default void drawCenteredSeedy(String message, int width, int y, int color, int size) {
        this.drawCenteredSeedy(message, width, y, color, 1.0F, size);
    }

    default void drawCenteredSeedy(String message, int width, int y, float alpha, int size) {
        this.drawCenteredSeedy(message, width, y, 0xFFFFFF, alpha, size);
    }

    default void drawCenteredSeedy(String message, int width, int y, int size) {
        this.drawCenteredSeedy(message, width, y, 0xFFFFFF, 1.0F, size);
    }

    static float[] createUV(int sheetWidth, int sheetHeight, int spriteWidth, int spriteHeight, int xOffset, int yOffset) {
        int y = (sheetHeight - spriteHeight) - yOffset;
        float topY = (y + spriteHeight) / (float) sheetHeight;
        float rightX = (xOffset + spriteWidth) / (float) sheetWidth;
        float leftX = xOffset / (float) sheetWidth;
        float bottomY = y / (float) sheetHeight;

        return new float[]{leftX, topY, leftX, bottomY, rightX, bottomY, rightX, topY};
    }
}
