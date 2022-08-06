package ss.itba.edu.ar;

public class Particle {
    private float rc;
    private float radius;
    private float x;
    private float y;
    private int id;

    public Particle(float rc, float radius, float x, float y, int id) {
        this.rc = rc;
        this.radius = radius;
        this.x = x;
        this.y = y;
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("Im particle %d pos[%f, %f]", id, x, y);
    }

    public float getRc() {
        return rc;
    }

    public void setRc(float rc) {
        this.rc = rc;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
