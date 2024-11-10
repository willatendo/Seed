package ca.seed.client.render;

import ca.seed.client.options.ClientOptions;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public final class Camera {
    private final Vector3f position;
    private final Vector3f rotation;
    private final Matrix4f projectionMatrix2d;
    private final Matrix4f viewMatrix2d;

    public Camera(float x, float y, float z, float xRotation, float yRotation, float zRotation) {
        this.position = new Vector3f(x, y, z);
        this.rotation = new Vector3f(xRotation, yRotation, zRotation);
        this.projectionMatrix2d = new Matrix4f();
        this.viewMatrix2d = new Matrix4f();
        this.adjustProjection();
    }

    public Vector3f getPosition() {
        return this.position;
    }

    public void setPosition(float x, float y, float z) {
        this.position.x = x;
        this.position.y = y;
        this.position.z = z;
    }

    public Vector3f getRotation() {
        return this.rotation;
    }

    public void setRotation(float x, float y, float z) {
        this.rotation.x = x;
        this.rotation.y = y;
        this.rotation.z = z;
    }

    public Matrix4f getProjectionMatrix2d() {
        return this.projectionMatrix2d;
    }

    public void movePosition(float x, float y, float z) {
        if (z != 0) {
            this.position.x += (float) Math.sin(Math.toRadians(this.rotation.y())) * -1.0F * z;
            this.position.z += (float) Math.cos(Math.toRadians(this.rotation.y())) * z;
        }
        if (x != 0) {
            this.position.x += (float) Math.sin(Math.toRadians(this.rotation.y() - 90)) * -1.0F * x;
            this.position.z += (float) Math.cos(Math.toRadians(this.rotation.y() - 90)) * x;
        }
        this.position.y += y;
    }

    public void moveRotation(float x, float y, float z) {
        this.rotation.x += x;
        this.rotation.y += y;
        this.rotation.z += z;
    }

    public void adjustProjection() {
        this.projectionMatrix2d.identity();
        this.projectionMatrix2d.ortho(0.0F, ClientOptions.WIDTH, 0.0F, ClientOptions.HEIGHT, 0.0F, 100.0F);
    }

    public Matrix4f getViewMatrix3d() {
        Matrix4f matrix = new Matrix4f();
        matrix.identity();
        matrix.rotate((float) Math.toRadians(this.getRotation().x()), new Vector3f(1.0F, 0.0F, 0.0F));
        matrix.rotate((float) Math.toRadians(this.getRotation().y()), new Vector3f(0.0F, 1.0F, 0.0F));
        matrix.rotate((float) Math.toRadians(this.getRotation().z()), new Vector3f(0.0F, 0.0F, 1.0F));
        matrix.translate(-this.getPosition().x(), -this.getPosition().y(), -this.getPosition().z());
        return matrix;
    }

    public Matrix4f getViewMatrix2d() {
        Vector3f cameraFront = new Vector3f(0.0F, 0.0F, -1.0F);
        Vector3f cameraUp = new Vector3f(0.0F, 1.0F, 0.0F);
        this.viewMatrix2d.identity();
        this.viewMatrix2d.lookAt(new Vector3f(0.0F, 0.0F, 20.0F), cameraFront.add(0.0F, 0.0F, 0.0F), cameraUp);
        return this.viewMatrix2d;
    }
}
