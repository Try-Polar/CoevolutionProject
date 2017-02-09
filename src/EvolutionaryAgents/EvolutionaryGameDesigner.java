package EvolutionaryAgents;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.lang.Math;

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
	
	int populationSize = 8;
	int generations = 20;
	int individualSize = 500;
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
		int[] hof = new int[individualSize];
		double hofFitness = -100;
		
		//Initial Evaluation
		for (int ind=0; ind < populationSize; ind++)	{
			fitnesses[ind] = evaluationFunction(pop[ind]);
			if (fitnesses[ind] > hofFitness) {
				hof = pop[ind];
				hofFitness = fitnesses[ind];
			}
		}
		
		for (int gen=0; gen < generations; gen++) {
			//Selection, Crossover, Mutation
			for (int i=0; i<populationSize/3; i++) {
				tournament2();
			}
			
			//Evaluation
			for (int ind=0; ind < populationSize; ind++)	{
				fitnesses[ind] = evaluationFunction(pop[ind]);
				if (fitnesses[ind] > hofFitness) {
					hof = pop[ind];
					hofFitness = fitnesses[ind];
				}
			}
			
		}
		
		gameDesigner.saveGameFromGenome(hof);
		writer.println("Best fitness is " + hofFitness);
		System.out.println("Best fitness is " + hofFitness);
		System.out.println("Finished after " + evals + " evaluations");
		writer.close();
	}
	
	public void makeSingleGame() {
		initialisePopulation();
		evaluationFunction(pop[0]);
		writer.close();
	}
	
	private void tournament2() {
		int a,b,W,L;
		//Select 2 members of the population
		a = rnd.nextInt(populationSize);
		do {
			b = rnd.nextInt(populationSize);
		} while (a == b);
		
		//determine which is better through fitness
		if (fitnesses[a] > fitnesses[b]) {
			L = b;
			W = a;
		} else {
			L = a;
			W = b;
		}
		
		//crossover
		for (int i=0; i<individualSize; i++) {
			if (rnd.nextDouble() < crossoverProbability) {
				pop[L][i] = pop[W][i];
			}
		}
		
		//mutation
		for (int i=0; i<individualSize;i++) {
			if (rnd.nextDouble() < indpb) {
				pop[L][i] = rnd.nextInt(255);
			}
		}
	}
	
	private void tournament3() {
		int a,b,c,W,L1,L2;
		a = rnd.nextInt(populationSize);
		do {
			b = rnd.nextInt(populationSize);
		} while (a == b);
		do {
			c = rnd.nextInt(populationSize);
		} while ((a == c) || (b == c));
		
		if (fitnesses[a] > fitnesses[b]) {
			L1 = b;
			if (fitnesses[a] > fitnesses[c]) {
				W = a;
				L2 = c;

			} else {
				W = c;
				L2 = a;
			}
		} else {
			L1 = a;
			if (fitnesses[b] > fitnesses[c]) {
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
		MCTSScore = new double[3];
		doNothingScore = new double[3];
		int runs = 5;
		
		boolean win = false;
		boolean lose = false;
		int MCTSWins = 0;
		int doNothingWins = 0;
		double MCTSAverageScore = 0;
		double doNothingAverageScore = 0;
		double RDScore;
		double RDWins;
		
		System.out.println("evaluation is begining");
		gameDesigner.createGameFromGenome(individual);
		try {
			System.out.println("valid game########################## I think");
			//ArcadeMachine.runOneGame("examples/gridphysics/earlyattempts.txt", "examples/gridphysics/earlyAttempts_lvl0.txt", true, sampleMCTSController, null, 15, 0);
			if(ArcadeMachine.generateOneLevel(game, constructiveLevelGenerator, recordLevelFile)){
	        	ArcadeMachine.runOneGeneratedLevel(game, false, sampleMCTSController, recordActionsFile, recordLevelFile, 5, false);
	        }
			//randomScore = ArcadeMachine.runOneGame(game, recordLevelFile, true, sampleRandomController, null, 15, 0);			
			//oneStepScore = ArcadeMachine.runOneGame(game, recordLevelFile, true, sampleOneStepController, null, 15, 0);
			for (int i=0; i < runs; i++) {
				doNothingScore = ArcadeMachine.runOneGame(game, recordLevelFile, false, sampleMCTSController, null, 15, 0);
				MCTSScore = ArcadeMachine.runOneGame(game, recordLevelFile, false, sampleMCTSController, null, 15, 0);
				MCTSAverageScore += MCTSScore[1];
				doNothingAverageScore += doNothingScore[1];
				if (MCTSScore[0] == 0) {
					MCTSWins++;
					win = true;
				} else {
					lose = true;
				}
				if (doNothingScore[0] == 0) {
					doNothingWins++;
					win = true;
				} else {
					lose = true;
				}
			}

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
		int win_lose = 0;
		if (win && lose) {
			win_lose = 1;
		}
		if (Math.max(MCTSScore[1], doNothingScore[1]) != 0) {
			RDScore = ((MCTSScore[1] - doNothingScore[1])/ Math.max(MCTSScore[1], doNothingScore[1]));
		} else if (Math.min(MCTSScore[1], doNothingScore[1]) != 0)  {
			RDScore = ((MCTSScore[1] - doNothingScore[1])/ Math.min(MCTSScore[1], doNothingScore[1]));
		} else {
			RDScore = 0;
		}
		
		if (Math.max(MCTSScore[0], doNothingScore[0]) != 0) {
			RDWins = ((MCTSScore[0] - doNothingScore[0])/ Math.max(MCTSScore[0], doNothingScore[0]));
		} else if (Math.min(MCTSScore[0], doNothingScore[0]) != 0) {
			RDWins = ((MCTSScore[0] - doNothingScore[0])/ Math.min(MCTSScore[0], doNothingScore[0]));
		} else {
			RDWins = 0;
		}
		
		System.out.println("RDScore " + RDScore);
		System.out.println(Math.max(MCTSScore[1], doNothingScore[1]));
		System.out.println("RDWins " + RDWins);
		System.out.println(Math.max(MCTSScore[0], doNothingScore[0]));
		System.out.println("win_50 " + win_50);
		System.out.println("win_lose " + win_lose);
		writer.println("RDScore " + RDScore);
		writer.println(Math.max(MCTSScore[1], doNothingScore[1]));
		writer.println("RDWins " + RDWins);
		writer.println(Math.max(MCTSScore[0], doNothingScore[0]));
		writer.println("win_50 " + win_50);
		writer.println("win_lose " + win_lose);
		double result = (RDScore + RDWins + win_50 + win_lose)/4;
		writer.println("FITNESS = " + result);
		System.out.println("FITNESS = " + result);
		return result;
		
	}
}
