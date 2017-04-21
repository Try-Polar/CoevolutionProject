package controllers.singlePlayer.ReinforcementLearning;

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
	
	String s = "C:" + File.separator + "Users" + File.separator + "Elliot" + File.separator + "Documents" + File.separator + "GitHub" + File.separator + "CoevolutionProject" + File.separator + "src" + File.separator + "controllers" + File.separator + "singlePlayer" + File.separator + "ReinforcementLearning" + File.separator + "RLPolicy.ser";
	
	protected int simulationDepth = 500;
	
	protected double currentScore, prevScore;
	int[][] currentState, prevState;
	//List<int[][]> previousStates;
	protected Random rnd;
	
	int reusedPolicies = 0;
	int newPolicies = 0;
	
	float discountFactor = 0.1f;

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
	HashMap <String, Types.ACTIONS> policies;
	
	public Agent(StateObservation so, ElapsedCpuTimer elapsedTimer)
	{
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
		//rewards.put(currentState, currentScore - prevScore);
		
		//Somehow previous policy based on reward receieved
		if (currentScore < prevScore)
		{
			index = rnd.nextInt(actions.length);
			policies.put(stateToString(prevState), actions[index]);
		}
		
		prevScore = currentScore;
		
		//pick action from policy
		policies.put(stateToString(currentState), simulateOptions(currentState, stateObs));
		stateObs.advance(policies.get(stateToString(currentState)));
		if (stateObs.isGameOver())
		{
			System.out.println("reused policies: " + reusedPolicies);
			System.out.println("new policies: " + newPolicies);
			savePolicies(); //Not ideal doing this here but can't see any easy way to call this after a game is done
		}
		//System.out.println(policies.get(stateToString(currentState)));
		return policies.get(stateToString(currentState));
		//Mapping must be saved somehow at end of game(serialized?)
		
	}
	
	private Types.ACTIONS getActionForState(int[][] state)
	{
		String stateString = stateToString(state);
		if (policies.containsKey(stateString))
		{
			reusedPolicies++;
			return policies.get(stateString);
		}
		else
		{
			newPolicies++;
			int index = rnd.nextInt(actions.length);
			policies.put(stateToString(currentState), actions[index]);
			return actions[index];
		}
	}
	
	private void printState(int[][] state)
	{
		System.out.println("STATE");
		for (int i = 0; i < state.length; i++)
		{
			for (int j = 0; j < state[i].length; j++)
			{
				System.out.print(state[i][j]);
			}
			System.out.println("");
		}
	}
	
	private String stateToString(int[][] state)
	{
		String stateString = "";
		for (int i = 0; i < state.length; i++)
		{
			for (int j = 0; j < state[i].length; j++)
			{
				stateString = stateString + state[i][j];
			}
		}
		return stateString;
	}
	
	private Types.ACTIONS simulateOptions(int[][] initialState, StateObservation initialStateObs)
	{
		int[][] currentState;
		StateObservation currentStateObs;
		double initialScore = initialStateObs.getGameScore();
		Types.ACTIONS bestAction = getActionForState(initialState);
		double bestScore = -1000000;
		
		for (int i=0; i < actions.length; i++)
		{
			double prevScore = initialScore;
			double actionScore = 0;

			currentStateObs = initialStateObs.copy();
			currentStateObs.advance(actions[i]);
				
			for (int j=0; j < simulationDepth-1; j++) //simulationDepth-1 as first action is not picked by policy
			{
				currentState = extractState(currentStateObs.getObservationGrid());
				currentStateObs.advance(getActionForState(currentState));
				double currentScore = currentStateObs.getGameScore();
				//System.out.println("Score: " + currentScore);
				actionScore += (currentScore - prevScore) * Math.pow(discountFactor,j) ;
				if (currentStateObs.getGameWinner() == WINNER.PLAYER_LOSES)
				{
					actionScore -= 5; //just needs to an amount to stop the AI killing itself if it currently sees no way to score
				}
				if (actionScore > bestScore) //greedy approach, favouring any action that leads to increase in score even if it could lead to losing score later
				{
					bestAction = actions[i];
					bestScore = actionScore;
				}
				if (currentStateObs.isGameOver()) {
		               break;
		        }
				prevScore = currentScore;
			}
			//System.out.println(actions[i] + ": " + actionScore);
		}	
		//if nothing seems to lead to an improvement, move randomly
		//Agent could previously get stuck in a rut where it stayed in the same state since no 
		//action could be seen to be an improvement, this should help fix that by changing the state up 
		//even if nothing good is found to be possible in the current state, hopefully allows 
		//agent to explore when it can't find a good action
		//System.out.println("init:"+initialScore);
		//System.out.println("best"+bestScore);
		if (bestScore == 0)
		{
			//System.out.println("RandomAction");
			bestAction = actions[rnd.nextInt(actions.length)];
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
			policies = (HashMap <String, Types.ACTIONS>) in.readObject();
			in.close();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			policies = new HashMap <String, Types.ACTIONS>();
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
