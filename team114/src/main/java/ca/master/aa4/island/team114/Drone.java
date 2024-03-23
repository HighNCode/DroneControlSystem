package ca.master.aa4.island.team114;

import org.json.JSONObject;

public class Drone {
	
	private int X;
	private int Y;
	private String H;
	private int B;
	
	private static Drone instance;
	
	private DroneMovementStrategy movementStrategy;

    private Drone()
    {
        X = 0;
        Y = 0;
        H = "";
        B = 0;
    }

    // Static method to get the instance of the Drone class
    public static Drone getInstance()
    {
        if (instance == null) {
            instance = new Drone();
        }
        
        return instance;
    }
	
	// setters
	
	public void setterX(int x)
    {
    	X = x;
    }
    
    public void setterY(int y)
    {
    	Y = y;
    }

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
    
    public void setMovementStrategy(DroneMovementStrategy movementStrategy) {
        this.movementStrategy = movementStrategy;
    }

    public JSONObject performMove(String d) {
        if (movementStrategy != null) {
            return movementStrategy.move(this, d);
        }
		return null;
    }
    
    public JSONObject performMove() {
        if (movementStrategy != null) {
            return movementStrategy.move(this);
        }
		return null;
    }
    
    public String nextDirection(String a, String d, int i, boolean p)
    {
    	String newDirection = d;
    	
    	if (a.equals("echo"))
    	{
    		switch(d)
    		{
    			case "N":
    		    	newDirection = "N";
    		    break;
    		    
    			case "E":
    				if (i % 2 == 0)
    					newDirection = "N";
    				else
    					newDirection = "S";
    		    break;
    		    
    			case "S":
    				newDirection = "S";
    			break;
    			
    			case "W":
    				if (i % 2 == 0)
    					newDirection = "N";
    				else
    					newDirection = "S";
    			break;
    		}
    	}
    	
    	else if (a.equals("heading"))
    	{
    		switch(d)
    		{
    			case "N":
    				if (p == false)
    				{
    					newDirection = "E";
    					Y--;
        				X++;
    				}
    				else
    				{
    					newDirection = "W";
        				Y--;
        				X--;
    				}	
    		    break;
    		    
    			case "E":
    				if (i % 2 == 0)
    				{
    					newDirection = "N";
    					Y--;
        				X++;
    				}
    					
    				else
    				{
    					newDirection = "S";
        				Y++;
        				X++;
    				}
    		    break;
    		    
    			case "S":
    				if (p == false)
    				{
    					newDirection = "E";
    					Y++;
        				X++;
    				}
    				else
    				{
    					newDirection = "W";
        				Y++;
        				X--;
    				}
    			break;
    			
    			case "W":
    				if (i % 2 == 0)
    				{
    					newDirection = "N";
    					Y--;
        				X--;
    				}
    				else
    				{
    					newDirection = "S";
        				Y++;
        				X--;
    				}
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
