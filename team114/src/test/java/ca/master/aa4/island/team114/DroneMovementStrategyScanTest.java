package ca.master.aa4.island.team114;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DroneMovementStrategyScanTest {

    @Test
    public void shouldMoveWithScan() {
        Drone testDrone = Drone.getInstance();
        DroneMovementStrategy strategy = new DroneMovementStrategyScan();
        JSONObject result = strategy.move(testDrone);
        assertNotNull(result);
        assertEquals("scan", result.getString("action"));
    }
}
