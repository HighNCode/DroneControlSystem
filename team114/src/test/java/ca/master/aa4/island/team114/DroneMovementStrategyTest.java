package ca.master.aa4.island.team114;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DroneMovementStrategyTest {

    @Test
    public void shouldMoveWithDirection() {
        Drone testDrone = Drone.getInstance();
        DroneMovementStrategy strategy = new DroneMovementStrategyEcho();
        JSONObject result = strategy.move(testDrone, "move");
        assertNotNull(result);
    }

    @Test
    public void shouldMoveWithoutDirection() {
        Drone testDrone = Drone.getInstance();
        DroneMovementStrategy strategy = new DroneMovementStrategyFly();
        JSONObject result = strategy.move(testDrone);
        assertNotNull(result);
    }
}
