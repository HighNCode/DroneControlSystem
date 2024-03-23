package ca.master.aa4.island.team114;

import org.json.JSONObject;

public class DroneMovementStrategyFly implements DroneMovementStrategy {
    @Override
    public JSONObject move(Drone D) {
    	JSONObject decision = new JSONObject();
    	String action = "fly";
        decision.put("action", action);
        return decision;
    }
}
