package ss.itba.edu.ar;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import static ss.itba.edu.ar.ParticleFormatUtil.*;

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

        // Store particles positions
        String positionsFilePath = "python/positions.csv"; // LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE)
        App.writeToFile(positionsFilePath, positionsToCsvFormat(simulationHandler.getParticlesList()));

        // Calculate M
        simulationHandler.calculateM();

        // Calculate neighbours distances
        simulationHandler.cellIndexMethod();

        // Store results
        String filePath = "python/neighbours.json"; // LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE)
        App.writeToFile(filePath, particleNeighboursJSON(simulationHandler.getParticlesList()));

        // Show particle data
        simulationHandler.printParticles();
    }

    private static void writeToFile(String filepath, String toWrite) {
        try {
            FileWriter fw = new FileWriter(filepath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            pw.print(toWrite);
            pw.flush();
            pw.close();
        } catch (Exception e) {
            System.out.println("Failed");
        }
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
