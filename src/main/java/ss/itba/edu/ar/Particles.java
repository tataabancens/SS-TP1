package ss.itba.edu.ar;

public class Particles {
    private float rc;
    private float radius;
    private float x;
    private float y;

    public Particles(float rc, float radius, float x, float y) {
        this.rc = rc;
        this.radius = radius;
        this.x = x;
        this.y = y;
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
