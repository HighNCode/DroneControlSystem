package ca.master.aa4.island.team114;

import org.json.JSONObject;

public class DroneMovementStrategyHeading implements DroneMovementStrategy {
    @Override
    public JSONObject move(Drone D, String d) {
    	JSONObject decision = new JSONObject();
    	String action = "heading";
		decision.put("action", action);
        JSONObject parameters = new JSONObject();
        parameters.put("direction", d);
        decision.put("parameters", parameters);
        D.setterH(d);
        return decision;
    }
}
