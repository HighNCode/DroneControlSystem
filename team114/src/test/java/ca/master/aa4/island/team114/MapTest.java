package ca.master.aa4.island.team114;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

public class MapTest {

    private Map map;

    @BeforeEach
    public void setUp() {
        map = new Map.MapBuilder()
            .width(5)
            .height(5)
            .addCell(0, 0, false, "Creek", "Emergency Site")
            .addCell(1, 1, true, "", "")
            .build();
    }

    @Test
    public void testGetCell() {
        Map.MapCell cell = map.getCell(0, 0);
        assertNotNull(cell);
        assertEquals(0, cell.getterX());
        assertEquals(0, cell.getterY());
        assertFalse(cell.getterV());
        assertEquals("Creek", cell.getterC());
        assertEquals("Emergency Site", cell.getterE());
    }

    @Test
    public void testGetCellOutOfBounds() {
        Map.MapCell cell = map.getCell(-1, 0);
        assertNull(cell);
        cell = map.getCell(0, 10);
        assertNull(cell);
    }
}

