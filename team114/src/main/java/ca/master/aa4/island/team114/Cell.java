package ca.master.aa4.island.team114;

public class Cell implements Map.MapCell{

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
    
    public void setterV(boolean v)
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
};