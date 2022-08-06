package ss.itba.edu.ar;

import java.io.File;
import java.io.InputStream;
import java.util.Scanner;

public class App {
    public static void main( String[] args ) throws Exception{

        // Locating inputs.txt in resources
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("inputs.txt");

        Scanner scanner = new Scanner(is);
        SimulationHandler simulationHandler = new SimulationHandler();

        // Read N value
        if (scanner.hasNextLine()) {
            simulationHandler.setN(Integer.parseInt(scanner.next()));
        }

        // Read L value
        if (scanner.hasNextLine()) {
            simulationHandler.setL(Integer.parseInt(scanner.next()));
        }

        // Create and stores N particles
        simulationHandler.generateParticles();
    }
}
