package ca.master.aa4.island.team114;

public class Map {
	
	private int X = 0;
	private int Y = 0;
	private boolean V = false;
	private String C = "";
	private String E = "";
	
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
    
//    public void updateMap(String a, String d)
//    {
//    	if (a.equals("fly"))
//    	{
//    		switch(d)
//    		{
//    			case "N":
//    		    	Y--;
//    		    break;
//    		    
//    			case "E":
//    		    	X++;
//    		    break;
//    		    
//    			case "S":
//    				Y++;
//    			break;
//    			
//    			case "W":
//    				X--;
//    			break;
//    		}
//    	}
//    	
//    	else if (a.equals("heading"))
//    	{
//    		switch(d)
//    		{
//    			case "N":
//    		    	Y--;
//    		    break;
//    		    
//    			case "E":
//    		    	X++;
//    		    break;
//    		    
//    			case "S":
//    				Y++;
//    			break;
//    			
//    			case "W":
//    				X--;
//    			break;
//    		}
//    	}
//    }
}
