package ca.master.aa4.island.team114;

import java.io.StringReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eu.ace_design.island.bot.IExplorerRaid;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Explorer implements IExplorerRaid {

    private final Logger logger = LogManager.getLogger();
    
    private boolean stopFlag = false;
    private int actionDecision = 0;
    private boolean onFound = false;
    private boolean onRange = false;
    private boolean oneStep = false;
    private boolean phaseChanged = false;
    private int creekCount = 0;
    private int groundRange = 0;
    private int iterationCount = 0;
    private int headingCount = 0;
    
    private Integer batteryLevel = 0;
    
    Map M;
    private DroneMovement DM = new DroneMovement();
    private Drone D;
    
    public Explorer()
    {
    	this.D = Drone.getInstance();
    	
    	M = new Map.MapBuilder()
                .width(53)
                .height(53)
                .build();

		for (int y = 0; y < 53; y++)
		{
		    for (int x = 0; x < 53; x++)
		    {
		        M = new Map.MapBuilder()
		                     .fromMap(M)
		                     .addCell(x, y, false, "", "")
		                     .build();	        	
		    }
		    
		    onRange = false;
		}
    	
//    	this.M = new Map[52][52];
//   
//        for (int i = 0; i < 52; i++)
//        {
//            for (int j = 0; j < 52; j++)
//            {
//                M[i][j] = new Map();
//            }
//        }
    }

    @Override
    public void initialize(String s) {
        logger.info("** Initializing the Exploration Command Center");
        JSONObject info = new JSONObject(new JSONTokener(new StringReader(s)));
        logger.info("** Initialization info:\n {}",info.toString(2));
        String direction = info.getString("heading");
        batteryLevel = info.getInt("budget");
        logger.info("The drone is facing {}", direction);
        logger.info("Battery level is {}", batteryLevel);

        D.setterH(direction);
    }

    @Override
    public String takeDecision() 
    { 
    	JSONObject decision = new JSONObject();
    	
    	// stop action
    	
    	if (stopFlag == true)
    	{
    		decision.put("action", "stop");
    	}
    	
    	// initial actions
    	
    	else if (actionDecision == 0)
    	{
    		DM.setMovementStrategy(D, new DroneMovementStrategyEcho());
            decision = DM.performMove("S");
            actionDecision = 1;
    	}
    	
    	else if (actionDecision == 1)
    	{
            DM.setMovementStrategy(D, new DroneMovementStrategyFly());
            decision = DM.performMove();
            D.nextDirection("fly", D.getterH(), iterationCount, phaseChanged);
            actionDecision = 0;
        }
    	
    	else if (actionDecision == 2)
    	{
    		String d = "";
            
            if (headingCount == 1)
            {
            	d = "W";
            	D.setterX(D.getterX() - 1);
            	D.setterY(D.getterY() + 1);
            }
            		
            else
            {
            	d = "S";
            	D.setterY(D.getterY() + 1);
            	if (headingCount == 0)
            		D.setterX(D.getterX() + 1);
            	else
            		D.setterX(D.getterX() - 1);
            }
            
            DM.setMovementStrategy(D, new DroneMovementStrategyHeading());
            decision = DM.performMove(d);
            headingCount++;
            
            if (headingCount == 3)
            {
            	actionDecision = 4;
            	headingCount = 0;
            }
            
//            else
//            {
//            	actionDecision = 3;
//            }
        }
    	
    	// further actions
    	
    	else if (actionDecision == 3)
    	{
    		String d = "";
    		
    		// heading count 0
    		
    		if (headingCount == 0 && iterationCount % 2 == 0 && phaseChanged)
    		{
				d = "N";
    			D.setterX(D.getterX() - 1);
            	D.setterY(D.getterY() - 1);
            	actionDecision = 4;
    		}
    		
    		else if (headingCount == 0 && iterationCount % 2 != 0 && phaseChanged)
    		{
    			d = "S";
    			D.setterX(D.getterX() - 1);
            	D.setterY(D.getterY() + 1);
            	actionDecision = 4;
    		}
    		
    		else if (headingCount == 0 && !phaseChanged)
    		{
    			DM.setMovementStrategy(D, new DroneMovementStrategyFly());
	            decision = DM.performMove();
	            D.nextDirection("fly", D.getterH(), iterationCount, phaseChanged);
	            logger.info("** Decision: {}", decision.toString());
	            phaseChanged = true;
	            return decision.toString(4);
    		}
    		
    		// heading count 1
    		
    		else if (headingCount == 1 && iterationCount % 2 == 0)
    		{
    			if (!phaseChanged)
    			{
    				d = "S";
        			D.setterX(D.getterX() + 1);
                	D.setterY(D.getterY() + 1);
                	phaseChanged = true;
    			}
    			else
    			{
    				d = "W";
        			D.setterX(D.getterX() - 1);
                	D.setterY(D.getterY() + 1);
                	phaseChanged = false;
                	headingCount--;
    			}
    		}
    		
    		else if (headingCount == 1 && iterationCount % 2 != 0)
    		{
    			if (!phaseChanged)
    			{
    				d = "N";
        			D.setterX(D.getterX() + 1);
                	D.setterY(D.getterY() - 1);
                	phaseChanged = true;
    			}
    			else
    			{
    				d = "W";
        			D.setterX(D.getterX() - 1);
                	D.setterY(D.getterY() - 1);
                	phaseChanged = false;
                	headingCount--;
    			}
    		}
    		
    		// heading count 2
    		
    		else if (headingCount == 2 && iterationCount % 2 == 0)
    		{
    			d = "E";
    			D.setterX(D.getterX() + 1);
            	D.setterY(D.getterY() - 1);
            	headingCount--;
    		}
    		
    		else if (headingCount == 2 && iterationCount % 2 != 0)
    		{
    			d = "E";
    			D.setterX(D.getterX() + 1);
            	D.setterY(D.getterY() + 1);
            	headingCount--;
    		}
    		
    		DM.setMovementStrategy(D, new DroneMovementStrategyHeading());
            decision = DM.performMove(d);
        }
    	
    	else if (actionDecision == 4)
    	{
            DM.setMovementStrategy(D, new DroneMovementStrategyEcho());
            decision = DM.performMove(D.nextDirection("echo", D.getterH(), iterationCount, phaseChanged));
            
            if (headingCount == 0 || headingCount == 2)
            	actionDecision = 5;
            else if (headingCount == 1)
            	actionDecision = 6;
    	}
    	
    	else if (actionDecision == 5)
    	{   
    		DM.setMovementStrategy(D, new DroneMovementStrategyFly());
            decision = DM.performMove();
            D.nextDirection("fly", D.getterH(), iterationCount, phaseChanged);
            headingCount = 0;
            
            if (groundRange > 0)
            {
            	groundRange--;
            }
            	
            else if (groundRange == 0)
				actionDecision = 7;
    	}
    	
    	else if (actionDecision == 6)
    	{
            DM.setMovementStrategy(D, new DroneMovementStrategyHeading());
            decision = DM.performMove(D.nextDirection("heading", D.getterH(), iterationCount, phaseChanged));
            headingCount++;
            actionDecision = 4;
            
//            if (headingCount == 2)
//            {
//            	actionDecision = 5;
//            	headingCount = 0;
//            }
//            
//            else
//            {
//            	actionDecision = 5;
//            }
        }
    	
    	else if (actionDecision == 7)
    	{
    		DM.setMovementStrategy(D, new DroneMovementStrategyScan());
            decision = DM.performMove();
//            Map.MapCell C = M.getCell(D.getterX(), D.getterY());
            
//            if (C.getterV() == true)
//            {
//            	if (visitedCount == 1)
//            	{
//            		stopFlag = true;
//            	}
//            	
//            	else if (visitedCount == 0)
//            	{
//                	visitedCount = 1;
//                	actionDecision = 4;
//            	}
//            	
//            	else if (visitedCount == 2)
//            	{
//            		visitedCount = 0;
//            		actionDecision = 6;
//            	}
//            }
//            
//            else
//            {
//            	visitedCount = 2;
//                C.setterV(true);
//            	actionDecision = 4;
//            }
        }

        logger.info("** Decision: {}", decision.toString());
        
        return decision.toString(4);
    }

    @Override
    public void acknowledgeResults(String s) {
        JSONObject response = new JSONObject(new JSONTokener(new StringReader(s)));
        logger.info("** Response received:\n"+response.toString(2));
        Integer cost = response.getInt("cost");
        logger.info("The cost of the action was {}", cost);
        batteryLevel -= cost; 
        logger.info("Battery level is {}", batteryLevel);
        String status = response.getString("status");
        logger.info("The status of the drone is {}", status);
        JSONObject extraInfo = response.getJSONObject("extras");
        logger.info("Additional information received: {}", extraInfo);
        
        if (extraInfo.has("found"))
        {
            String found = extraInfo.getString("found");
            
            if (found.equals("GROUND") && !oneStep)
            {
            	actionDecision = 1;
            	oneStep = true;
            }
            
            else if (found.equals("GROUND") && !onFound)
            {
            	onFound = true;
            	actionDecision = 2;
            }
            
            else if (found.equals("GROUND") && onFound)
            {
            	onRange = true;
            	groundRange = extraInfo.getInt("range");
            	
            	if (headingCount == 2)
            	{
            		iterationCount++;
            		headingCount = 0;
            	}
            }
            
            else if (found.equals("OUT_OF_RANGE") && onRange && headingCount == 0)
            {
            	actionDecision = 6;
            	onRange = false;
            }
            
            else if (found.equals("OUT_OF_RANGE") && headingCount > 0)
            {
            	if (phaseChanged)
            		stopFlag = true;
            	actionDecision = 3;
            	onRange = false;
            }
        }
        
        else if (extraInfo.has("biomes"))
        {
        	JSONArray biomesArray = extraInfo.getJSONArray("biomes");
        	
        	if(biomesArray.length() == 1 && biomesArray.getString(0).equals("OCEAN"))
        	{
        		actionDecision = 4;
        	}
        	
        	else
        	{
        		actionDecision = 5;
        	}
        		
	        if (extraInfo.has("creeks"))
	        {
	        	JSONArray creeksArray = extraInfo.getJSONArray("creeks");
	
	        	for (int x = 0; x < creeksArray.length(); x++)
	        	{
	        		String creek = creeksArray.getString(x);
	        		Map.MapCell C = M.getCell(D.getterX(), D.getterY());
	                C.setterC(creek);
	                C.setterX(D.getterX());
	                C.setterY(D.getterY());
//	        	    M[D.getterX()][D.getterY()].setterC(creek);
//	        	    M[D.getterX()][D.getterY()].setterX(D.getterX());
//	        	    M[D.getterX()][D.getterY()].setterY(D.getterY());
	        	    creekCount++;
	        	}
	        }
	        
	        if (extraInfo.has("sites"))
	        {
	        	JSONArray sitesArray = extraInfo.getJSONArray("sites");
	        	
	
	        	for (int x = 0; x < sitesArray.length(); x++)
	        	{
	        		String sites = sitesArray.getString(x);
	        		Map.MapCell C = M.getCell(D.getterX(), D.getterY());
	        		C.setterE(sites);
	                C.setterX(D.getterX());
	                C.setterY(D.getterY());
//	        	    M[D.getterX()][D.getterY()].setterE(sites);
//	        	    M[D.getterX()][D.getterY()].setterX(D.getterX());
//	        	    M[D.getterX()][D.getterY()].setterY(D.getterY());
	        	    creekCount++;
	        	}
	        }
        }
    }

    @Override
    public String deliverFinalReport() {
    	int[] closestPointIndices = new int[2];
        double shortestDistance = Double.MAX_VALUE;
        int X = 0;
    	int Y = 0;
    	
    	emergencySitesLoop:
    	for (int i = 0; i < 53; i++)
    	{
            for (int j = 0; j < 53; j++)
            {
            	Map.MapCell C = M.getCell(i, j);
                if (C.getterE() != "")
                {
                	
                	X = i;
                	Y = j;
                	
                	break emergencySitesLoop;
                }
            }
        }
    	
        for (int i = 0; i < 53; i++)
        {
            for (int j = 0; j < 53; j++)
            {
            	Map.MapCell C = M.getCell(i, j);
            	if (C.getterC() != "")
            	{
            		double distance = Math.abs(X - i) + Math.abs(Y - j);
            		
//            		logger.info("creek distance {} {} {} {} {} {}", C.getterC(), distance, X, i, Y, j);

                    if (distance < shortestDistance && distance != 0)
                    { // Exclude the starting point itself
                        shortestDistance = distance;
                        closestPointIndices[0] = i; // Update with the current point's indices
                        closestPointIndices[1] = j;
                    }
            	}
            }
        }
        
        Map.MapCell C = M.getCell(closestPointIndices[0], closestPointIndices[1]);
        logger.info("The nearest creek to the emergency site is {} ", C.getterC());

        return C.getterC();
//        // Assuming M is a 2D array you want to print, with its declaration available in the class
//        for (int i = 0; i < 52; i++)
//        { // Assuming M.length is the length of the first dimension
//            StringBuilder sb = new StringBuilder(); // Create a StringBuilder to concatenate the row elements
//            for (int j = 0; j < 52; j++)
//            { // Assuming M[i].length is the length of the second dimension
//                sb.append((M[i][j]).getterC()).append(" "); // Append each element of the 2D array and a space
//            }
//            logger.info(sb.toString()); // Log the entire row
//        }
//        
//        return "no creek found";
    }
}