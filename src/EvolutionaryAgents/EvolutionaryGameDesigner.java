package EvolutionaryAgents;

import java.util.Random;

import core.ArcadeMachine;
import gameDesigner.GameDesigner;


public class EvolutionaryGameDesigner {
	GameDesigner gameDesigner;
	Random rnd = new Random();
	
	//Controllers
	String sampleRandomController = "controllers.singlePlayer.sampleRandom.Agent";
	String sampleOneStepController = "controllers.singlePlayer.sampleonesteplookahead.Agent";
    String sampleMCTSController = "controllers.singlePlayer.sampleMCTS.Agent";
	
	int populationSize = 5;
	int generations = 20;
	int individualSize = 750;
	float mutationProbability = 0.5f;
	float crossoverProbability = 0.5f;
	float indpb = 0.4f;
	
	int[] fitnesses =new int[populationSize];
	
	int[][] pop = new int[populationSize][individualSize];
			
	public EvolutionaryGameDesigner() {
		gameDesigner = new GameDesigner();
	}
	
	public void eaSimple() {
		initialisePopulation();
		for (int gen=0; gen < generations; gen++) {
			//Evaluation
			for (int ind=0; ind < populationSize; ind++)	{
				fitnesses[ind] = evaluationFunction(pop[ind]);
			}
			//Selection, Crossover, Mutation
			for (int i=0; i<populationSize/3; i++) {
				tournament();
			}
		}
		System.out.println("Finished");
	}
	
	private void tournament() {
		int a,b,c,W,L1,L2;
		a = rnd.nextInt(populationSize);
		do {
			b = rnd.nextInt(populationSize);
		} while (a == b);
		do {
			c = rnd.nextInt(populationSize);
		} while ((a == c) || (b == c));
		
		if (a > b) {
			L1 = b;
			if (a > c) {
				W = a;
				L2 = c;

			} else {
				W = c;
				L2 = a;
			}
		} else {
			L1 = a;
			if (b > c) {
				W = b;
				L2 = c;
			} else {
				W = c;
				L2 = b;
			}
		}
		
		for (int i=0; i<individualSize; i++) {
			if (rnd.nextDouble() < crossoverProbability) {
				pop[L1][i] = pop[W][i];
				pop[L2][i] = pop[W][i];
			}
		}
		
		for (int i=0; i<individualSize;i++) {
			if (rnd.nextDouble() < indpb) {
				pop[L1][i] = rnd.nextInt(255);
				pop[L2][i] = rnd.nextInt(255);
			}
		}
		
	}
	
	private void initialisePopulation() {
		for (int i=0; i < populationSize; i++) {
			for (int j=0; j < individualSize; j++) {
				pop[i][j] = rnd.nextInt(255);
			}
		}
	}
	
	public int evaluationFunction(int[] individual) {
		//If game does not run return negative score for now if game runs return score of 0
		//Strings will later be changed to variables
		gameDesigner.createGameFromGenome(individual);
		try {
			ArcadeMachine.runOneGame("examples/gridphysics/earlyattempts.txt", "examples/gridphysics/earlyAttempts_lvl0.txt", true, sampleMCTSController, null, 15, 0);
		} catch (Exception e) {
			System.out.println("invalid game----------------------------------------------------------------------");
			return -5;
		}
		System.out.println("validGame##################################################################################");
		return 0;
		
	}
}
