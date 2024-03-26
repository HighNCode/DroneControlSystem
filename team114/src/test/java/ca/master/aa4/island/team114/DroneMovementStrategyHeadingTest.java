package ca.master.aa4.island.team114;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DroneMovementStrategyHeadingTest {

    @Test
    public void shouldMoveWithHeading() {
        Drone testDrone = Drone.getInstance();
        DroneMovementStrategy strategy = new DroneMovementStrategyHeading();
        JSONObject result = strategy.move(testDrone, "N");
        assertNotNull(result);
        assertEquals("heading", result.getString("action"));
        assertTrue(result.getJSONObject("parameters").has("direction"));
        assertEquals("N", result.getJSONObject("parameters").getString("direction"));
        assertEquals("N", testDrone.getterH());
    }
}
