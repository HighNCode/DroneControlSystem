package ca.master.aa4.island.team114;

import java.io.StringReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eu.ace_design.island.bot.IExplorerRaid;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Explorer implements IExplorerRaid {

    private final Logger logger = LogManager.getLogger();
//    private int echoCount = 0;
    private boolean stopFlag = false;
    private int actionDecision = 0;
    private boolean onGround = false;

    @Override
    public void initialize(String s) {
        logger.info("** Initializing the Exploration Command Center");
        JSONObject info = new JSONObject(new JSONTokener(new StringReader(s)));
        logger.info("** Initialization info:\n {}",info.toString(2));
        String direction = info.getString("heading");
        Integer batteryLevel = info.getInt("budget");
        logger.info("The drone is facing {}", direction);
        logger.info("Battery level is {}", batteryLevel);
    }

    @Override
    public String takeDecision() 
    { 
    	JSONObject decision = new JSONObject();
    	
    	if (stopFlag == true)
    	{
    		decision.put("action", "stop");
    	}
    	
    	else if (actionDecision == 0)
    	{
    		decision.put("action", "echo");
            JSONObject parameters = new JSONObject();
            parameters.put("direction", "S");
            decision.put("parameters", parameters);
            actionDecision = 1;
    	}
    	
    	else if (actionDecision == 1)
    	{
            decision.put("action", "fly");
//            echoCount++;
            if (onGround)
            {
            	actionDecision = 3;
            }
            
            else
            {
            	actionDecision = 0;
            }
        }
    	
    	else if (actionDecision == 2)
    	{
    		decision.put("action", "heading");
            JSONObject parameters = new JSONObject();
            parameters.put("direction", "S");
            decision.put("parameters", parameters);
            actionDecision = 3;
        }
    	
    	else if (actionDecision == 3)
    	{
            decision.put("action", "scan");
            actionDecision = 1;
        }

        logger.info("** Decision: {}", decision.toString(4));
        
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
            Integer range = extraInfo.getInt("range");
            
            if (found.equals("GROUND"))
            {
            	onGround = true;
            	actionDecision = 2;            		
            }
        }
        
        else if (extraInfo.has("biomes"))
        {
        	String biomes = extraInfo.getString("biomes");
        	
        	if (biomes.equals("BEACH"))
            {
            	stopFlag = true;           		
            }
        }
    }

    @Override
    public String deliverFinalReport() {
        return "no creek found";
    }

}