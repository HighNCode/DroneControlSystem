package ca.master.aa4.island.team114;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DroneMovementStrategyFlyTest {

    @Test
    public void shouldMoveWithFly() {
        Drone testDrone = Drone.getInstance();
        DroneMovementStrategy strategy = new DroneMovementStrategyFly();
        JSONObject result = strategy.move(testDrone);
        assertNotNull(result);
        assertEquals("fly", result.getString("action"));
    }
}
