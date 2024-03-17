package ca.master.aa4.island.team114;

import org.json.JSONObject;

public class DroneMovementStrategyEcho implements DroneMovementStrategy {
    @Override
    public JSONObject move(Drone D, String d) {
    	JSONObject decision = new JSONObject();
    	String action = "echo";
		decision.put("action", action);
        JSONObject parameters = new JSONObject();
        parameters.put("direction", d);
        decision.put("parameters", parameters);
        return decision;
    }
}
