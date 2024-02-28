package ca.master.aa4.island.team114;

public class Drone {
	
	private int X;
	private int Y;
	private String H;
	private int B;
	
	// setters

    public void setterH(String h)
    {
    	H = h;
    }
    
    public void setterB(int b)
    {
    	B = b;
    }
    
    // getters
    
    public int getterX()
    {
    	return X;
    }
    
    public int getterY()
    {
    	return Y;
    }
    
    public String getterH()
    {
    	return H;
    }
    
    public int getterB()
    {
    	return B;
    }
    
    public String nextDirection(String a, String d)
    {
    	String newDirection = d;
    	
    	if (a.equals("echo"))
    	{
    		switch(d)
    		{
    			case "N":
    		    	newDirection = "W";
    		    break;
    		    
    			case "E":
    				newDirection = "N";
    		    break;
    		    
    			case "S":
    				newDirection = "E";
    			break;
    			
    			case "W":
    				newDirection = "S";
    			break;
    		}
    	}
    	
    	else if (a.equals("heading"))
    	{
    		switch(d)
    		{
    			case "N":
    				newDirection = "E";
    				Y--;
    				X++;
    		    break;
    		    
    			case "E":
    				newDirection = "S";
    				Y++;
    				X++;
    		    break;
    		    
    			case "S":
    				newDirection = "W";
    				Y++;
    				X--;
    			break;
    			
    			case "W":
    				newDirection = "N";
    				Y--;
    				X--;
    			break;
    		}
    	}
    	
    	else if (a.equals("fly"))
    	{
    		switch(d)
    		{
	    		case "N":
			    	Y--;
			    break;
			    
				case "E":
			    	X++;
			    break;
			    
				case "S":
					Y++;
				break;
				
				case "W":
					X--;
				break;
    		}
    	}
    	
    	return newDirection;
    }
};
