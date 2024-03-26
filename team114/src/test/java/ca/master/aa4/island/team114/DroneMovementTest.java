package ca.master.aa4.island.team114;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.json.JSONObject;

public class DroneMovementTest {

    private DroneMovement droneMovement;
    private Drone drone;
    private DroneMovementStrategy movementStrategy;

    @BeforeEach
    public void setUp() {
        drone = Drone.getInstance();;
        movementStrategy = new DroneMovementStrategyFly();
        droneMovement = new DroneMovement();
        droneMovement.setMovementStrategy(drone, movementStrategy);
    }

    @Test
    public void testPerformMoveWithStringParameter() {
        JSONObject result = droneMovement.performMove("some_direction");

        assertNotNull(result);
    }

    @Test
    public void testPerformMoveWithoutParameter() {
        JSONObject result = droneMovement.performMove();

        assertNotNull(result);
    }
}

