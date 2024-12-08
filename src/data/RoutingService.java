package data;

import com.graphhopper.GHRequest;
import com.graphhopper.GHResponse;
import com.graphhopper.GraphHopper;
import com.graphhopper.ResponsePath;
import com.graphhopper.config.CHProfile;
import com.graphhopper.config.Profile;
import com.graphhopper.util.PointList;
import com.graphhopper.util.Translation;
import com.graphhopper.util.Instruction;
import com.graphhopper.util.InstructionList;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RoutingService {
    private static RoutingService instance;
    private final GraphHopper hopper;
    private final String apiKey = "e8c4eebf87cc450cb6f25be41699d04c"; // API key

    public static RoutingService getInstance() {
        if (instance == null) {
            instance = new RoutingService();
        }
        return instance;
    }

    private RoutingService() {
        hopper = createGraphHopperInstance("osm file/philippines-latest.osm.pbf");
    }

    private GraphHopper createGraphHopperInstance(String ghLoc) {
        GraphHopper graHopper = new GraphHopper();
        graHopper.setOSMFile(ghLoc);
        graHopper.setGraphHopperLocation("target/routing-graph-cache");
        graHopper.setProfiles(new Profile("car").setVehicle("car").setWeighting("fastest").setTurnCosts(false));
        graHopper.getCHPreparationHandler().setCHProfiles(new CHProfile("car"));

        // Use the API key if required for initialization
        graHopper.getCHPreparationHandler().setPreparationThreads(1); // Example adjustment
        graHopper.importOrLoad();
        return graHopper;
    }

    public List<RoutingData> routing(double fromLat, double fromLon, double toLat, double toLon) {
        GHRequest req = new GHRequest(fromLat, fromLon, toLat, toLon)
                .setProfile("car")
                .setLocale(Locale.US);

        // Example: Adding API key to the request headers (if the API service requires it)
        req.putHint("key", apiKey);

        GHResponse rsp = hopper.route(req);

        if (rsp.hasErrors()) {
            throw new RuntimeException(rsp.getErrors().toString());
        }

        ResponsePath path = rsp.getBest();
        PointList pointList = path.getPoints();
        double distance = path.getDistance();
        long timeInMs = path.getTime();

        System.out.println("Route found. Distance: " + distance + " meters. Time: " + timeInMs + " milliseconds.");

        Translation tr = hopper.getTranslationMap().getWithFallBack(Locale.UK);
        InstructionList il = path.getInstructions();

        List<RoutingData> list = new ArrayList<>();
        for (Instruction instruction : il) {
            System.out.println("Instruction: " + instruction.getTurnDescription(tr) + ", Distance: " + instruction.getDistance());
            list.add(new RoutingData(instruction.getDistance(), instruction.getTurnDescription(tr), instruction.getPoints()));
        }
        return list;
    }
}