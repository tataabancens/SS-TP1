package ss.itba.edu.ar;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;

public class ParticleFormatUtil {

    public static String positionsToCsvFormat(List<Particle> particleList) {
        StringBuilder sb = new StringBuilder();
        sb.append("id,x,y\n");
        for (Particle p : particleList) {
            sb.append(p.getId()).append(',').append(p.getX()).append(",").append(p.getY()).append("\n");
        }
        return sb.toString();
    }

    public static String particleNeighboursJSON(List<Particle> particleList) {
        JSONArray arr = new JSONArray();
        for (Particle p : particleList) {
            JSONObject jsParticle = new JSONObject();
            jsParticle.put("id", p.getId());
            JSONArray neigh = new JSONArray();
            for (Particle n : p.getNeighbours()) {
                neigh.add(n.getId());
            }
            jsParticle.put("neighbours", neigh);
            arr.add(jsParticle);
        }
        return arr.toJSONString();
    }

    public static String testResultToCsvFormat(int[] particleCount, long[] cellIndexTimes, long[] bruteForceTimes) {
        StringBuilder sb = new StringBuilder();
        sb.append("N,CellIndexTime(ms),BruteForceTime(ms)\n");
        for (int i = 0; i < particleCount.length; i++) {
            sb.append(particleCount[i]).append(',').append(cellIndexTimes[i]).append(',').append(bruteForceTimes[i]).append("\n");
        }
        return sb.toString();
    }
}

