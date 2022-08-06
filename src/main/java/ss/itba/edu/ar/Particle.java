package ss.itba.edu.ar;

import java.util.Objects;

public class Particle {
    private float rc;
    private float radius;
    private float x;
    private float y;
    private int id;
    private int cellNumber;

    public Particle(float rc, float radius, float x, float y, int id) {
        this.rc = rc;
        this.radius = radius;
        this.x = x;
        this.y = y;
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("Id %d pos[%.2f, %.2f] rc %.2f radius %.2f", id, x, y, rc, radius);
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCellNumber() {
        return cellNumber;
    }

    public void setCellNumber(int cellNumber) {
        this.cellNumber = cellNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Particle particle = (Particle) o;
        return id == particle.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
