package ss.itba.edu.ar;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Particle {
    private float rc;
    private float radius;
    private float x;
    private float y;
    private int id;
    private int cellX;
    private int cellY;

    private Set<Particle> neighbours;

    public Particle(float rc, float radius, float x, float y, int id) {
        this.rc = rc;
        this.radius = radius;
        this.x = x;
        this.y = y;
        this.id = id;
        neighbours = new HashSet<>();
    }

    Particle(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("Id %d pos[%.2f, %.2f] rc %.2f cellX %d cellY %d neighbours: %s", id, x, y, rc, cellX, cellY, strNeighbours());
    }

    public String strNeighbours() {
        StringBuilder toRet = new StringBuilder();
        for (Particle p : neighbours) {
            toRet.append(p.getId()).append(" ");
        }
        return toRet.toString();
    }

    public Set<Particle> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(Set<Particle> neighbours) {
        this.neighbours = neighbours;
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

    public int getCellX() {
        return cellX;
    }

    public void setCellX(int cellX) {
        this.cellX = cellX;
    }

    public int getCellY() {
        return cellY;
    }

    public void setCellY(int cellY) {
        this.cellY = cellY;
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

    public void setCellCoords(int M, float L) {
        int xOffset = (int)Math.floor((getX() * M) / L);
        int yOffset = (int)Math.floor((getY() * M) / L);

        setCellX(xOffset);
        setCellY(yOffset);
    }

    public void checkNeighbours(List<Particle> particles) {
        // Calculate distance for each neighbor and add to set, check if not already calculated distance
        for (Particle p : particles) {
            if (!p.equals(this)) {
                if (p.getNeighbours().contains(this) || isInRange(p)) {
                    neighbours.add(p);
                }
            }
        }
    }
    public boolean isInRange(Particle p) {
        // TODO: Add particle radius into account
        return Math.sqrt(Math.pow(p.getX() - getX(), 2) + Math.pow(p.getY() - getY(), 2)) <= getRc();
    }

    public void checkPeriodicNeighbour(List<Particle> particles, float L, Direction dir) {
        for (Particle p : particles) {
            if (p.getNeighbours().contains(this) || isInRange(new Particle(p.getX() + dir.getX() * L, p.getY() + dir.getY() * L))) {
                neighbours.add(p);
            }
        }
    }
}
