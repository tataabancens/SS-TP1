package ss.itba.edu.ar;

import java.util.ArrayList;
import java.util.List;

public class SimulationHandler {
    private int N;
    private int L;
    private int M;

    List<Particles> particlesList = new ArrayList<>();

    SimulationHandler() {
        // Default
    }
    public void generateParticles() {

    }


    public int getN() {
        return N;
    }

    public void setN(int n) {
        N = n;
    }

    public int getL() {
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
