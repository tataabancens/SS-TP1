package ss.itba.edu.ar;

import java.io.File;
import java.io.InputStream;
import java.util.Scanner;

public class App {
    public static void main( String[] args ) throws Exception{

        // Locating inputs.txt in resources
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("inputs.txt");
        if (is == null) {
            System.out.println("File not found");
            System.exit(1);
        }

        // Initiate setup
        Scanner scanner = new Scanner(is);
        SimulationHandler simulationHandler = new SimulationHandler();

        // Reads txt
        App.readTxt(simulationHandler, scanner);

        // Create and stores N particles
        simulationHandler.generateParticles();

        // Calculate M
        simulationHandler.calculateM();

        // Calculate neighbours distances
        simulationHandler.cellIndexMethod();

        // Show particle data
        simulationHandler.printParticles();
    }

    public static void readTxt(SimulationHandler simulationHandler, Scanner scanner) {
        // Read N value
        if (scanner.hasNextLine()) {
            String in = scanner.next();
            simulationHandler.setN(Integer.parseInt(scanner.next()));
            System.out.println(in + " " + simulationHandler.getN());
        }
        // Read L value
        if (scanner.hasNextLine()) {
            String in = scanner.next();
            simulationHandler.setL(Integer.parseInt(scanner.next()));
            System.out.println(in + " " + simulationHandler.getL());
        }
        // Read rc value
        if (scanner.hasNextLine()) {
            String in = scanner.next();
            simulationHandler.setRc(Float.parseFloat(scanner.next()));
            System.out.println(in + " " + simulationHandler.getRc());
        }
    }
}
