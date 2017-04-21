package controllers.singlePlayer.ReinforcementLearning2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import core.game.Observation;
import core.game.StateObservation;
import core.player.AbstractPlayer;
import ontology.Types;
import ontology.Types.ACTIONS;
import ontology.Types.WINNER;
import tools.ElapsedCpuTimer;

public class Agent extends AbstractPlayer {
	
	//Not sure if this will be needed
	public int num_actions;
	
	String s = "C:" + File.separator + "Users" + File.separator + "Elliot" + File.separator + "Documents" + File.separator + "GitHub" + File.separator + "CoevolutionProject" + File.separator + "src" + File.separator + "controllers" + File.separator + "singlePlayer" + File.separator + "ReinforcementLearning2" + File.separator + "RLPolicy.ser";
	
	protected int simulationDepth = 7;
	
	protected double currentScore, prevScore;
	int[][] currentState, prevState;
	//List<int[][]> previousStates;
	protected Random rnd;

	//Things needed for RL
	//State transition function - not sure we can use this as it will be mostly unknown until it is learned
	//and it will change from game to game but declaring for now for completetion
	HashMap<int[][], HashMap<Types.ACTIONS, int[][]>> transitionFunction;
	
	//Reward function for being in a given state
	HashMap <int[][], Double> rewards;
	
	//Actions
	public Types.ACTIONS[] actions;
	
	//States - not sure we can make this or need it
	//
		
	//Policies
	//Should be saved and reloaded so that better policies can be learned
	HashMap <int[][], Types.ACTIONS> policies;
	
	public Agent(StateObservation so, ElapsedCpuTimer elapsedTimer)
	{
		//Look at area around agent
		//look at possible transitions
		//look at rewards for possible transitions
		
		//Get the actions in a static array.
				ArrayList<Types.ACTIONS> act = so.getAvailableActions();
		        actions = new Types.ACTIONS[act.size()];
		        for(int i = 0; i < actions.length; ++i)
		        {
		            actions[i] = act.get(i);
		        }
		        num_actions = actions.length;
		        currentScore = 0;
		        prevScore = 0;
		        rnd = new Random();
		        prevState = new int[5][5];
		        loadPolicies();
		        rewards = new HashMap <int[][], Double>();
		        
	}
	
	@Override
	public ACTIONS act(StateObservation stateObs, ElapsedCpuTimer elapsedTimer)
	{
		int index;
		//look at current state (probably using obersvation grid as representation here)
		ArrayList<Observation>[][] currentObsGrid = stateObs.getObservationGrid();
		currentState = extractState(currentObsGrid);
		
		
		//Depending on result of previous action (score change) alter rewards
		//need to find a way to stop first call of act affecting this, simple enough but will do later
		currentScore = stateObs.getGameScore();
		
		//Change reward function based on previous result
		rewards.put(currentState, currentScore - prevScore);
		
		//Somehow previous policy based on reward receieved
		if (currentScore < prevScore)
		{
			index = rnd.nextInt(actions.length);
			policies.put(prevState, actions[index]);
		}
		
		prevScore = currentScore;
		
		//pick action from policy
		policies.put(currentState, simulateOptions(currentState, stateObs));
		stateObs.advance(policies.get(currentState));
		if (stateObs.isGameOver())
		{
			savePolicies(); //Not ideal doing this here but can't see any easy way to call this after a game is done
		}
		
		return policies.get(currentState);
		//Mapping must be saved somehow at end of game(serialized?)
		
	}
	
	private Types.ACTIONS getActionForState(int[][] state)
	{
		if (policies.containsKey(state))
		{
			return policies.get(state);
		}
		else
		{
			int index = rnd.nextInt(actions.length);
			policies.put(currentState, actions[index]);
			return actions[index];
		}
	}
	
	private Types.ACTIONS simulateOptions(int[][] initialState, StateObservation initialStateObs)
	{
		int[][] currentState;
		StateObservation currentStateObs;
		Types.ACTIONS bestAction = getActionForState(initialState);
		int bestScore = 0;
		double simScore = 0;
		
		for (int i=0; i < actions.length; i++)
		{
			currentStateObs = initialStateObs.copy();
			currentStateObs.advance(actions[i]);
			for (int j=0; j < simulationDepth-1; j++) //simulationDepth-1 as first action is not picked by policy
			{
				currentState = extractState(currentStateObs.getObservationGrid());
				currentStateObs.advance(getActionForState(currentState));
				simScore = currentStateObs.getGameScore();
				if (currentStateObs.getGameWinner() == WINNER.PLAYER_LOSES)
				{
					simScore -= 5; //just needs to an amount to stop the AI killing itself if it currently sees no way to score
				}
				if (currentStateObs.isGameOver()) {
	                break;
	            }
			}
			if (simScore > bestScore)
			{
				bestAction = actions[i];
			}
		}	
		return bestAction;
	}
	
	public void savePolicies()
	{		
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		
		try {
			fos = new FileOutputStream(s);
			out = new ObjectOutputStream(fos);
			out.writeObject(policies);
			out.close();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void loadPolicies()
	{		
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream(s);
			in = new ObjectInputStream(fis);
			policies = (HashMap <int[][], Types.ACTIONS>) in.readObject();
			in.close();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			policies = new HashMap <int[][], Types.ACTIONS>();
		}	
	}
	
	private int[][] extractState(ArrayList<Observation>[][] obsGrid)
	{
		//state should be 5x5 as that is what the level is fixed to being
		int[][] state = new int[obsGrid.length][obsGrid[0].length];
		//feels like a messy way of doing things but can't see a better solution right now
		boolean containsAvatar;
		boolean containsNPC;
		boolean containsImmovable;
		boolean containsMovable;
		boolean containsResource; 
		boolean containsPortal;
		boolean containsFromAvatarSprite; //sprites created by the avatar
		
		for (int x=0; x<obsGrid.length; x++)
		{
			for (int y=0; y<obsGrid[0].length; y++)
			{
				//State should default to 0 but just to be sure
				state[x][y] = 0;
				
				containsAvatar = false;
				containsNPC = false;
				containsImmovable = false;
				containsMovable = false;
				containsResource = false; 
				containsPortal = false;
				containsFromAvatarSprite = false;
				
				for (int i=0; i<obsGrid[x][y].size(); i++)
				{
					//Currently working under the assumption that there are only 6 different categories as such 
					//I'm only concerned with numbers 0-5 (will check to be sure these are the values used)
					Observation currObs = obsGrid[x][y].get(i);
					
					//Since each square of the grid can contain multiple observations of different
					//categories I have set this up such that the will be a 6 bit integer
					//If there is more than one of the same category of observation in the same square
					//this will be ignored
					switch(currObs.category)
					{
					case 0:
						if (!containsAvatar)
						{
							state[x][y] += Math.pow(2, 0);
							containsAvatar = true;
						}
						break;
					case 1:
						//Not certain that numbers are correctly matched to what they represent but
						//it's irrelevant in terms of how this functions
						if (!containsNPC)
						{
							state[x][y] += Math.pow(2, 1);
							containsNPC = true;
						}
						break;
					case 2:
						if (!containsImmovable)
						{
							state[x][y] += Math.pow(2, 2);
							containsImmovable = true;
						}
						break;
					case 3:
						if (!containsMovable)
						{
							state[x][y] += Math.pow(2, 3);
							containsMovable = true;
						}
						break;
					case 4:
						if (!containsResource)
						{
							state[x][y] += Math.pow(2, 4);
							containsResource = true;
						}
						break;
					case 5:
						if (!containsPortal)
						{
							state[x][y] += Math.pow(2, 5);
							containsPortal = true;
						}
						break;
					case 6:
						if (!containsFromAvatarSprite)
						{
							state[x][y] += Math.pow(2, 6);
							containsFromAvatarSprite = true;
						}
						break;
					default:
						System.out.println("Unaccounted for category value: " + currObs.category);
						break;
					}
				}
			}
		}
		return state;
	}

}
