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
        periodicContours = true;
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
            // Sets an object with the corresponding xy indexes
            NeighbourCells nc = new NeighbourCells(p, M);

            for (int i = nc.xStart; i < nc.xEnd; i++) {
                for (int j = nc.yStart; j < nc.yEnd; j++) {
                    p.checkNeighbours(cells.get(i + j * M));
                }
            }
            // Add an alternative for periodic contours
            // TODO: Check if this is working
            if (periodicContours) {
                if (p.getCellX() == 0) {
                    p.checkPeriodicNeighbour(cells.get(M - 1 + M * p.getCellY()), L, Direction.LEFT);
                    if (p.getCellY() == M - 1) {
                        p.checkPeriodicNeighbour(cells.get(M - 1), L, Direction.UP_LEFT);
                        p.checkPeriodicNeighbour(cells.get((p.getCellY() - 1) * M + M - 1), L, Direction.LEFT);
                        p.checkPeriodicNeighbour(cells.get(p.getCellX() + 1), L, Direction.UP);
                        p.checkPeriodicNeighbour(cells.get(p.getCellX()), L, Direction.UP);
                    } else if (p.getCellY() == 0) {
                        p.checkPeriodicNeighbour(cells.get(M * M - 1), L, Direction.DOWN_LEFT);
                        p.checkPeriodicNeighbour(cells.get((p.getCellY() + 1) * M + M - 1), L, Direction.LEFT);
                        p.checkPeriodicNeighbour(cells.get((M - 1) * M + p.getCellX() + 1), L, Direction.DOWN);
                        p.checkPeriodicNeighbour(cells.get((M - 1) * M + p.getCellX()), L, Direction.DOWN);
                    } else {
                        p.checkPeriodicNeighbour(cells.get((p.getCellY() - 1) * M + M - 1), L, Direction.LEFT);
                        p.checkPeriodicNeighbour(cells.get((p.getCellY() + 1) * M + M - 1), L, Direction.LEFT);
                    }
                    continue;
                }
                if (p.getCellX() == M - 1) {
                    p.checkPeriodicNeighbour(cells.get(M * p.getCellY()), L, Direction.RIGHT);
                    if (p.getCellY() == M - 1) {
                        p.checkPeriodicNeighbour(cells.get(0), L, Direction.UP_RIGHT);
                        p.checkPeriodicNeighbour(cells.get((p.getCellY() - 1) * M), L, Direction.RIGHT);
                        p.checkPeriodicNeighbour(cells.get(p.getCellX() - 1), L, Direction.UP);
                        p.checkPeriodicNeighbour(cells.get(p.getCellX()), L, Direction.UP);
                    } else if (p.getCellY() == 0) {
                        p.checkPeriodicNeighbour(cells.get(M * M - M), L, Direction.DOWN_RIGHT);
                        p.checkPeriodicNeighbour(cells.get((p.getCellY() + 1) * M), L, Direction.RIGHT);
                        p.checkPeriodicNeighbour(cells.get((M - 1) * M + p.getCellX() - 1), L, Direction.DOWN);
                        p.checkPeriodicNeighbour(cells.get((M - 1) * M + p.getCellX()), L, Direction.DOWN);
                    } else {
                        p.checkPeriodicNeighbour(cells.get((p.getCellY() - 1) * M), L, Direction.RIGHT);
                        p.checkPeriodicNeighbour(cells.get((p.getCellY() + 1) * M), L, Direction.RIGHT);
                    }
                    continue;
                }
                if (p.getCellY() == 0) {
                    p.checkPeriodicNeighbour(cells.get((M - 1) * M + p.getCellX()), L, Direction.DOWN);
                    p.checkPeriodicNeighbour(cells.get((M - 1) * M + p.getCellX() - 1), L, Direction.DOWN);
                    p.checkPeriodicNeighbour(cells.get((M - 1) * M + p.getCellX() + 1), L, Direction.DOWN);
                }
                if (p.getCellY() == M - 1) {
                    p.checkPeriodicNeighbour(cells.get(p.getCellX()), L, Direction.UP);
                    p.checkPeriodicNeighbour(cells.get(p.getCellX() + 1), L, Direction.UP);
                    p.checkPeriodicNeighbour(cells.get(p.getCellX() - 1), L, Direction.UP);
                }
            }
        }
    }

    private static class NeighbourCells {
        int xStart, xEnd, yStart, yEnd;

        public NeighbourCells(Particle p, int M) {
            // set xStart
            if (p.getCellX() == 0) {
                xStart = 0;
            } else {
                xStart = p.getCellX();
            }
            // Set xEnd
            if (p.getCellX() == M - 1) {
                xEnd = M;
            } else {
                xEnd = p.getCellX() + 2;
            }
            // Set yStart
            if (p.getCellY() == 0) {
                yStart = 0;
            } else {
                yStart = p.getCellY() - 1;
            }
            // Set yEnd
            if (p.getCellY() == M - 1) {
                yEnd = M;
            } else {
                yEnd = p.getCellY() + 2;
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
