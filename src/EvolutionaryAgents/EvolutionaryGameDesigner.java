package EvolutionaryAgents;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import core.ArcadeMachine;
import gameDesigner.GameDesigner;


public class EvolutionaryGameDesigner {
	GameDesigner gameDesigner;
	Random rnd = new Random();
	
	String path ="C:" + File.separator + "Users" + File.separator + "Elliot" + File.separator + "Documents" + File.separator + "GitHub" + File.separator + "CoevolutionProject" + File.separator + "src" + File.separator + "EvolutionaryAgents" + File.separator + "results.txt";
	//C:\Users\Elliot\Documents\GitHub\CoevolutionProject\examples\gridphysics
	File f = new File(path);
	PrintWriter writer;
	
	//Controllers
	String doNothingController = "controllers.singlePlayer.doNothing.Agent";
	String sampleRandomController = "controllers.singlePlayer.sampleRandom.Agent";
	String sampleOneStepController = "controllers.singlePlayer.sampleonesteplookahead.Agent";
    String sampleMCTSController = "controllers.singlePlayer.sampleMCTS.Agent";
    
    String constructiveLevelGenerator = "levelGenerators.constructiveLevelGenerator.LevelGenerator";
	
	int populationSize = 5;
	int generations = 20;
	int individualSize = 750;
	float mutationProbability = 0.5f;
	float crossoverProbability = 0.5f;
	float indpb = 0.4f;
	
	int evals = 0;
	
	double[] fitnesses =new double[populationSize];
	
	int[][] pop = new int[populationSize][individualSize];
	
	String gamesPath = "examples/gridphysics/";
	String gameName = "earlyAttempts";
	String game = gamesPath + gameName + ".txt";
	
	String recordLevelFile = gamesPath + gameName + "glvl.txt";
	String recordActionsFile = null;
			
	public EvolutionaryGameDesigner() {
		gameDesigner = new GameDesigner();
		
		f.getParentFile().mkdirs();
		try {
		f.createNewFile();
		writer = new PrintWriter(f);
		} catch (IOException e) { 
			e.printStackTrace();
		}
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
		
		double max = -10;
		int index = -10;
		for (int i=0; i < fitnesses.length; i++) {
			if (fitnesses[i] > max) {
				max = fitnesses[i];
				index = i;
			}
		}
		writer.println("Best fitness is " + max);
		writer.println(pop[index].toString());
		System.out.println("Best fitness is " + max);
		//for (int i=0; i < pop[index].length; i ++) {
		//	writer.print(pop[index][i]);
		//}
		gameDesigner.createGameFromGenome(pop[index]);
		System.out.println("Finished after " + evals + " evaluations");
		writer.close();
	}
	
	public void makeSingleGame() {
		initialisePopulation();
		evaluationFunction(pop[0]);
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
	
	public double evaluationFunction(int[] individual) {
		//If game does not run return negative score for now if game runs return score of 0
		//Strings will later be changed to variables
		evals++;
		double[] randomScore, oneStepScore, MCTSScore, doNothingScore;
		
		System.out.println("evaluation is begining");
		gameDesigner.createGameFromGenome(individual);
		try {
			System.out.println("valid game########################## I think");
			//ArcadeMachine.runOneGame("examples/gridphysics/earlyattempts.txt", "examples/gridphysics/earlyAttempts_lvl0.txt", true, sampleMCTSController, null, 15, 0);
			if(ArcadeMachine.generateOneLevel(game, constructiveLevelGenerator, recordLevelFile)){
	        	ArcadeMachine.runOneGeneratedLevel(game, false, doNothingController, recordActionsFile, recordLevelFile, 5, false);
	        }
			//randomScore = ArcadeMachine.runOneGame(game, recordLevelFile, true, sampleRandomController, null, 15, 0);			
			//oneStepScore = ArcadeMachine.runOneGame(game, recordLevelFile, true, sampleOneStepController, null, 15, 0);
			doNothingScore = ArcadeMachine.runOneGame(game, recordLevelFile, false, sampleMCTSController, null, 15, 0);
			MCTSScore = ArcadeMachine.runOneGame(game, recordLevelFile, false, sampleMCTSController, null, 15, 0);
		} catch (Exception e) {
			System.out.println("invalid game----------------------------------------------------------------------");
			return -5;
		}
		float win_50;
		if (MCTSScore[0] > -1 && MCTSScore[2] < 50) {
			win_50 = -1;
		} else {
			win_50 = 1;
		}
		double result = ((MCTSScore[1] - doNothingScore[1])+(MCTSScore[0] - doNothingScore[0])+(win_50))/3;
		writer.println("FITNESS = " + result);
		System.out.println("FITNESS = " + result);
		return result;
		
	}
}
