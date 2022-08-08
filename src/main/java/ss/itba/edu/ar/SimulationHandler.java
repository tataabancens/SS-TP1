package ss.itba.edu.ar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimulationHandler {
    private int N;
    private float L;
    private int M;
    private float rc;
    private int particleCount;
    private boolean periodicContours;

    List<Particle> particlesList = new ArrayList<>();

    public SimulationHandler() {
        // Default
        particleCount = 0;
        M = 1;
        periodicContours = false;
    }

    public void generateParticles() {
        Random r = new Random();
        for (int i = 0; i < N; i++) {
            float randomX = 0 + r.nextFloat() * L;
            float randomY = 0 + r.nextFloat() * L;
            particlesList.add(new Particle(rc, 0.1f, randomX, randomY, particleCount++));
        }
    }

    public void printParticles() {
        for (Particle particle : particlesList) {
            System.out.println(particle);
        }
    }

    public void calculateM() {
        float maxRadius = 0.0f;
        for(Particle p : getParticlesList()) {
            if (p.getRadius() > maxRadius) {
                maxRadius = p.getRadius();
            }
        }
        while(L / M >= rc + 2 * maxRadius) {
            M++;
        }
        M--;
    }



    public List<Particle> getParticlesList() {
        return particlesList;
    }

    public List<List<Particle>> cellIndexMethodSetup() {
        List<List<Particle>> cells = new ArrayList<>(getM() * getM());
        for (int i = 0; i < getM() * getM(); i++) {
            cells.add(new ArrayList<>());
        }
        for (Particle particle: getParticlesList()) {
            // Calculates cell coordinates and stores them in particle
            particle.setCellCoords(getM(), getL());

            // Adds the particle to de corresponding cell
            cells.get(particle.getCellX() + particle.getCellY() * getM()).add(particle);
        }
        return cells;
    }

    public void cellIndexMethod() {

        List<List<Particle>> cells = cellIndexMethodSetup();

        for (Particle p : getParticlesList()) {
            // This method works with non-periodic contours
            int xIndex, yIndex;
            if (p.getCellX() == 0) {
                xIndex = 0;
            } else {
                xIndex = p.getCellX() - 1;
            }
            if (p.getCellY() == 0) {
                yIndex = 0;
            } else {
                yIndex = p.getCellY() - 1;
            }
            for (int i = xIndex; i < M; i++) {
                for (int j = yIndex; j < M; j++) {
                    p.checkNeighbours(cells.get(i + j * M));
                }
            }
            // Add an alternative for periodic contours
            // TODO: Check if this is working
            if (periodicContours) {
                if (p.getCellX() == 0) {
                    p.checkPeriodicNeighbour(cells.get(M - 1 + M * p.getCellY()), L, Direction.LEFT);
                }
                if (p.getCellX() == M - 1) {
                    p.checkPeriodicNeighbour(cells.get(M * p.getCellY()), L, Direction.RIGHT);
                }
                if (p.getCellY() == 0) {
                    p.checkPeriodicNeighbour(cells.get(M - 1 + M * p.getCellX()), L, Direction.UP);
                }
                if (p.getCellY() == M - 1) {
                    p.checkPeriodicNeighbour(cells.get(M * p.getCellX()), L, Direction.DOWN);
                }
            }
        }
    }

    public float getRc() {
        return rc;
    }

    public void setRc(float rc) {
        this.rc = rc;
    }

    public int getN() {
        return N;
    }

    public void setN(int n) {
        N = n;
    }

    public float getL() {
        return L;
    }

    public void setL(int l) {
        L = l;
    }

    public int getM() {
        return M;
    }

    public void setM(int m) {
        M = m;
    }
}
