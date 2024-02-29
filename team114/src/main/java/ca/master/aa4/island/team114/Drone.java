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
