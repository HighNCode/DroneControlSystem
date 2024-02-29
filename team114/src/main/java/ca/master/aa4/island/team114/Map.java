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

    // Static inner class for Cell, assuming similar properties to original Map
    public static class Cell {
	
		private int X;
		private int Y;
		private boolean V;
		private String C;
		private String E;
		
		public Cell(int X, int Y, boolean V, String C, String E) {
            this.X = X;
            this.Y = Y;
            this.V = V;
            this.C = C;
            this.E = E;
        }
		
		// getter setters
	
	    public void setterX(int x)
	    {
	    	X = x;
	    }
	    
	    public void setterY(int y)
	    {
	    	Y = y;
	    }
	    
	    public void setterV(Boolean v)
	    {
	    	V = v;
	    }
	    
	    public void setterC(String c)
	    {
	    	C = c;
	    }
	    
	    public void setterE(String e)
	    {
	    	E = e;
	    }
	    
	    public int getterX()
	    {
	    	return X;
	    }
	    
	    public int getterY()
	    {
	    	return Y;
	    }
	    
	    public boolean getterV()
	    {
	    	return V;
	    }
	    
	    public String getterC()
	    {
	    	return C;
	    }
	    
	    public String getterE()
	    {
	    	return E;
	    }
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