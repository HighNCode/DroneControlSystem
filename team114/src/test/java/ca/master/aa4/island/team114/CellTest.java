package ca.master.aa4.island.team114;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CellTest {

    private Cell testCell;

    @BeforeEach
    public void initCell() {
        testCell = new Cell(0, 0, false, "DefaultC", "DefaultE");
    }

    @Test
    public void testSetterX() {
        testCell.setterX(10);
        assertEquals(10, testCell.getterX());
    }

    @Test
    public void testSetterY() {
        testCell.setterY(20);
        assertEquals(20, testCell.getterY());
    }

    @Test
    public void testSetterV() {
        testCell.setterV(true);
        assertTrue(testCell.getterV());
    }

    @Test
    public void testSetterC() {
        testCell.setterC("NewC");
        assertEquals("NewC", testCell.getterC());
    }

    @Test
    public void testSetterE() {
        testCell.setterE("NewE");
        assertEquals("NewE", testCell.getterE());
    }
}
