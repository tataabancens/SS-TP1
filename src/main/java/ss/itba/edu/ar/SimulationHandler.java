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

    List<Particle> particlesList = new ArrayList<>();

    public SimulationHandler() {
        // Default
        particleCount = 0;
        M = 1;
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
        while(L / M >= rc) {
            M++;
        }
        M--;
    }

    public void cellIndexMethod() {
        List<List<Particle>> cells = new ArrayList<>(M * M);
        for (int i = 0; i < M * M; i++) {
            cells.add(new ArrayList<>());
        }
        for (Particle particle: particlesList) {
            // Calculates cell coordinates and stores them in particle
            particle.setCellCoords(M, L);

            // Adds the particle to de corresponding cell
            cells.get(particle.getCellX() + particle.getCellY() * M).add(particle);
        }

        for (Particle p : particlesList) {
            if (p.getCellX() > 0 && p.getCellX() < M && p.getCellY() > 0 && p.getCellY() < M) {
                for (int i = p.getCellX() - 1; i < M; i++) {
                    for (int j = p.getCellY() - 1; j < M; j++) {
                        p.checkNeighbours(cells.get(i + j * M));
                    }
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
