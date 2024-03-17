package ca.master.aa4.island.team114;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Map {
    private int width, height;
    private List<List<Cell>> cells;
    
    private final Logger logger = LogManager.getLogger();

    // Private constructor to prevent instantiation without builder
    private Map(int width, int height, List<List<Cell>> cells) {
        this.width = width;
        this.height = height;
        this.cells = cells;
    }
    
    public interface MapCell {
    	void setterX(int x);
    	void setterY(int y);
    	void setterV(boolean v);
    	void setterC(String c);
    	void setterE(String e);
        int getterX();
        int getterY();
        boolean getterV();
        String getterC();
        String getterE();
    }
    
    // Static inner Builder class
    public static class MapBuilder {
        private int width, height;
        private List<List<Cell>> cells = new ArrayList<>();

        public MapBuilder fromMap(Map existingMap) {
            this.width = existingMap.width;
            this.height = existingMap.height;
            this.cells = new ArrayList<>(existingMap.cells); // Deep copy if necessary
            return this;
        }

        public MapBuilder width(int width) {
            this.width = width;
            return this;
        }

        public MapBuilder height(int height) {
            this.height = height;
            return this;
        }

        public MapBuilder addCell(int x, int y, boolean v, String c, String e) {
            while (y >= cells.size()) {
                cells.add(new ArrayList<>());
            }
            List<Cell> row = cells.get(y);
            while (x >= row.size()) {
                row.add(null);
            }
            row.set(x, new Cell(x, y, v, c, e));
            return this;
        }

        public Map build() {
            return new Map(width, height, cells);
        }
    }
    
    public Cell getCell(int x, int y) {
        if (y < 0 || y >= cells.size() || x < 0 || x >= cells.get(y).size())
        {
            return null;
        }
        
        return cells.get(y).get(x);
    }
}