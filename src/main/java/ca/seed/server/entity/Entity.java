package ca.seed.server.entity;

public class Entity {
    private float x = 0.0F;
    private float y = 0.0F;
    private float z = 0.0F;

    public Entity() {
    }

    public float getX() {
        return this.x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return this.y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return this.z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public void moveTo(float x, float y, float z) {
        this.setX(x);
        this.setY(y);
        this.setZ(z);
    }
}
