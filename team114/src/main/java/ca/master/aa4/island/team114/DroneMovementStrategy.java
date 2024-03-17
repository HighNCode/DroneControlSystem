package ca.master.aa4.island.team114;

import org.json.JSONObject;

public interface DroneMovementStrategy {
    default JSONObject move(Drone D, String d)
    {
    	JSONObject decision = new JSONObject();
    	return decision;
    }
    default JSONObject move(Drone D)
    {
    	JSONObject decision = new JSONObject();
    	return decision;
    };
}
