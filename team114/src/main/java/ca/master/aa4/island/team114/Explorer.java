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
    private int creekCount = 0;
    private int headingCount = 0;
    private int visitedCount = 0;
    private int boundaryCount = 0;
    
    private Map[][] M = new Map[52][52];
    private Drone D = new Drone();
    
    public Explorer() {
        for (int i = 0; i < 52; i++)
        {
            for (int j = 0; j < 52; j++)
            {
                M[i][j] = new Map();
            }
        }
    }

    @Override
    public void initialize(String s) {
        logger.info("** Initializing the Exploration Command Center");
        JSONObject info = new JSONObject(new JSONTokener(new StringReader(s)));
        logger.info("** Initialization info:\n {}",info.toString(2));
        String direction = info.getString("heading");
        Integer batteryLevel = info.getInt("budget");
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
    		String action = "echo";
    		decision.put("action", action);
            JSONObject parameters = new JSONObject();
            parameters.put("direction", "S");
            decision.put("parameters", parameters);
            actionDecision = 1;
    	}
    	
    	else if (actionDecision == 1)
    	{
    		String action = "fly";
            decision.put("action", action);
            D.nextDirection(action, D.getterH());
            actionDecision = 0;
        }
    	
    	else if (actionDecision == 2)
    	{
    		String action = "heading";
    		decision.put("action", action);
            JSONObject parameters = new JSONObject();
//            String next = D.nextDirection(action, D.getterH());
            
            if (headingCount == 1)
            {
            	parameters.put("direction", "W");
            	D.setterH("W");
            }
            	
            else
            {
            	parameters.put("direction", "S");
            	D.setterH("S");
            }
            	
            decision.put("parameters", parameters);
            headingCount++;
            
            if (headingCount == 3)
            {
            	actionDecision = 7;
            	headingCount = 0;
            }
            
            else
            {
            	actionDecision = 3;
            }
        }
    	
    	else if (actionDecision == 3)
    	{
    		String action = "scan";
            decision.put("action", action);
            M[D.getterX()][D.getterY()].setterV(true);
            actionDecision = 2;
        }
    	
    	// further actions
    	
    	else if (actionDecision == 4)
    	{
    		String action = "echo";
    		decision.put("action", action);
            JSONObject parameters = new JSONObject();
            parameters.put("direction", D.nextDirection(action, D.getterH()));
            decision.put("parameters", parameters);
            actionDecision = 5;
    	}
    	
    	else if (actionDecision == 5)
    	{
    		String action = "fly";
            decision.put("action", action);
            D.nextDirection(action, D.getterH());
            actionDecision = 7;
    	}
    	
    	else if (actionDecision == 6)
    	{
    		String action = "heading";
    		decision.put("action", action);
            JSONObject parameters = new JSONObject();
            String next = D.nextDirection(action, D.getterH());
            parameters.put("direction", next);
            decision.put("parameters", parameters);
            D.setterH(next);
            headingCount++;
            
            if (headingCount == 3)
            {
            	actionDecision = 5;
            	headingCount = 0;
            }
        }
    	
    	else if (actionDecision == 7)
    	{
    		String action = "scan";
            decision.put("action", action);
            
            if (M[D.getterX()][D.getterY()].getterV() == true)
            {
            	if (visitedCount == 1)
            	{
            		stopFlag = true;
            	}
            	
            	else if (visitedCount == 0)
            	{
                	visitedCount = 1;
                	actionDecision = 4;
            	}
            	
            	else if (visitedCount == 2)
            	{
            		visitedCount = 0;
            		actionDecision = 6;
            	}
            }
            
            else
            {
            	visitedCount = 2;
            	M[D.getterX()][D.getterY()].setterV(true);
            	actionDecision = 4;
            }
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
        String status = response.getString("status");
        logger.info("The status of the drone is {}", status);
        JSONObject extraInfo = response.getJSONObject("extras");
        logger.info("Additional information received: {}", extraInfo);
        
        if (extraInfo.has("found"))
        {
            String found = extraInfo.getString("found");
            
            if (found.equals("GROUND") && !onFound)
            {
            	onFound = true;
            	actionDecision = 2;            		
            }
            
            else if (found.equals("GROUND") && onFound)
            {
            	onRange = true;
            }
            
            else if (found.equals("OUT_OF_RANGE") && onRange && boundaryCount < 4)
            {
            	actionDecision = 6;
            	boundaryCount++;
            	visitedCount = 0;
            	onRange = false;
            }
        }
        
        else if (extraInfo.has("biomes"))
        {
//        	if (!onGround)
//        	{
//	        	JSONArray biomesArray = extraInfo.getJSONArray("biomes");
//	        	
//	        	for (int x = 0; x < biomesArray.length(); x++)
//	        	{
//	        		if (biomesArray.getString(x).equals("BEACH"))
//	        		{
//	        			onGround = true;
//	        			actionDecision = 4;
//	        		}
//	        	}
//        	}
        	
	        if (extraInfo.has("creeks"))
	        {
	        	JSONArray creeksArray = extraInfo.getJSONArray("creeks");
	
	        	for (int x = 0; x < creeksArray.length(); x++)
	        	{
	        		String creek = creeksArray.getString(x);
	        	    M[D.getterX()][D.getterY()].setterC(creek);
	        	    M[D.getterX()][D.getterY()].setterX(D.getterX());
	        	    M[D.getterX()][D.getterY()].setterY(D.getterY());
	        	    creekCount++;
	        	}
	        }
	        
	        else if (extraInfo.has("sites"))
	        {
	        	JSONArray sitesArray = extraInfo.getJSONArray("sites");
	
	        	for (int x = 0; x < sitesArray.length(); x++)
	        	{
	        		String sites = sitesArray.getString(x);
	        	    M[D.getterX()][D.getterY()].setterE(sites);
	        	    M[D.getterX()][D.getterY()].setterX(D.getterX());
	        	    M[D.getterX()][D.getterY()].setterY(D.getterY());
	        	    creekCount++;
	        	}
	        }
        }
        
//        else if (onFound && extraInfo.has("found"))
//        {
//        	String found = extraInfo.getString("found");
//            
//            if (found.equals("OUT_OF_RANGE"))
//            {
//            	actionDecision = 6;
//            }
//        }
    }

    @Override
    public String deliverFinalReport() {
        return "no creek found";
    }

}