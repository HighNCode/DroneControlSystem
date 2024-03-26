package ca.master.aa4.island.team114;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.json.JSONObject;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DroneTest {

    private Drone testDrone;

    @BeforeEach
    public void initDrone() {
        testDrone = Drone.getInstance();
    }

    @Test
    public void shouldSetAndGetCoordinates() {
        testDrone.setterX(5);
        testDrone.setterY(10);
        assertEquals(5, testDrone.getterX());
        assertEquals(10, testDrone.getterY());
    }

    @Test
    public void shouldSetAndGetHeading() {
        testDrone.setterH("N");
        assertEquals("N", testDrone.getterH());
    }

    @Test
    public void shouldSetAndGetBattery() {
        testDrone.setterB(50);
        assertEquals(50, testDrone.getterB());
    }

    @Test
    public void shouldPerformMoveWithDirection() {
        DroneMovementStrategy strategy = new DroneMovementStrategyEcho();
        testDrone.setMovementStrategy(strategy);
        JSONObject result = testDrone.performMove("move");
        assertNotNull(result);
    }

    @Test
    public void shouldPerformMoveWithoutDirection() {
        DroneMovementStrategy strategy = new DroneMovementStrategyFly();
        testDrone.setMovementStrategy(strategy);
        JSONObject result = testDrone.performMove();
        assertNotNull(result);
    }
}
