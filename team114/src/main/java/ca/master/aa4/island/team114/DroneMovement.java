package ca.master.aa4.island.team114;

import org.json.JSONObject;

public class DroneMovement {
	
	private DroneMovementStrategy movementStrategy;
	private Drone droneState;
    
    public void setMovementStrategy(Drone droneState, DroneMovementStrategy movementStrategy) {
        this.droneState = droneState;
    	this.movementStrategy = movementStrategy;
    }

    public JSONObject performMove(String d) {
        if (movementStrategy != null) {
            return movementStrategy.move(droneState, d);
        }
		return null;
    }
    
    public JSONObject performMove() {
        if (movementStrategy != null) {
            return movementStrategy.move(droneState);
        }
		return null;
    }
    
};
