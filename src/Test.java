import gameDesigner.GameDesigner;

import java.util.Random;

import core.ArcadeMachine;
import EvolutionaryAgents.EvolutionaryGameDesigner;

/**
 * Created with IntelliJ IDEA.
 * User: Diego
 * Date: 04/10/13
 * Time: 16:29
 * This is a Java port from Tom Schaul's VGDL - https://github.com/schaul/py-vgdl
 */
public class Test
{

    public static void main(String[] args)
    {
        //GameDesigner test = new GameDesigner();
        EvolutionaryGameDesigner evoGameDesigner = new EvolutionaryGameDesigner();
        //evoGameDesigner.eaSimple();
        //evoGameDesigner.eaSymbolsSimple();
        evoGameDesigner.singleSymbolRun();
        //evoGameDesigner.makeSingleGame();
    	//test.loadGame();
    	
    	//Available controllers:
    	String sampleRandomController = "controllers.singlePlayer.sampleRandom.Agent";
    	String doNothingController = "controllers.singlePlayer.doNothing.Agent";
        String sampleOneStepController = "controllers.singlePlayer.sampleonesteplookahead.Agent";
        String sampleMCTSController = "controllers.singlePlayer.sampleMCTS.Agent";
        String sampleFlatMCTSController = "controllers.singlePlayer.sampleFlatMCTS.Agent";
        String sampleOLMCTSController = "controllers.singlePlayer.sampleOLMCTS.Agent";
        String sampleGAController = "controllers.singlePlayer.sampleGA.Agent";
        String sampleOLETSController = "controllers.singlePlayer.olets.Agent";
        String repeatOLETS = "controllers.singlePlayer.repeatOLETS.Agent";

        //Available Generators
        String randomLevelGenerator = "levelGenerators.randomLevelGenerator.LevelGenerator";
        String geneticGenerator = "levelGenerators.geneticLevelGenerator.LevelGenerator";
        String constructiveLevelGenerator = "levelGenerators.constructiveLevelGenerator.LevelGenerator";
        
        //Available games:
        String gamesPath = "examples/gridphysics/";
        String games[] = new String[]{};
        String generateLevelPath = "examples/gridphysics/";

        //All public games
        games = new String[]{"aliens", "angelsdemons", "assemblyline", "avoidgeorge", "bait", //0-4
                "beltmanager", "blacksmoke", "boloadventures", "bomber", "bomberman",         //5-9
                "boulderchase", "boulderdash", "brainman", "butterflies", "cakybaky",         //10-14
                "camelRace", "catapults", "chainreaction", "chase", "chipschallenge",         //15-19
                "clusters", "colourescape", "chopper", "cookmepasta", "cops",                 //20-24
                "crossfire", "defem",  "defender", "digdug", "dungeon",                       //25-29
                "eighthpassenger", "eggomania", "enemycitadel", "escape", "factorymanager",   //30-34
                "firecaster",  "fireman", "firestorms", "freeway", "frogs",                   //35-39
                "garbagecollector", "gymkhana", "hungrybirds", "iceandfire", "ikaruga",       //40-44
                "infection", "intersection", "islands", "jaws", "killbillVol1",               //45-49
                "labyrinth", "labyrinthdual", "lasers", "lasers2", "lemmings",                //50-54
                "missilecommand", "modality", "overload", "pacman", "painter",                //55-59
                "pokemon", "plants", "plaqueattack", "portals", "racebet",                    //60-64
                "raceBet2", "realportals", "realsokoban", "rivers", "roadfighter",            //65-69
                "roguelike", "run", "seaquest", "sheriff", "shipwreck",                       //70-74
                "sokoban", "solarfox" ,"superman", "surround", "survivezombies",              //75-79
                "tercio", "thecitadel", "thesnowman",  "waitforbreakfast", "watergame",       //80-84
                "waves", "whackamole", "wildgunman", "witnessprotection", "wrapsokoban",      //85-89
                "zelda", "zenpuzzle", "earlyAttempts", "hallOfFame001", "hallOfFame002",	  //90-94
                "hallOfFame003", "hallOfFame004", "hallOfFame005", "hallOfFame006", "hallOfFame007",//95-99
        		"hallOfFame008", "hallOfFame009", "hallOfFame010", "hallOfFame011", "hallOfFame012", //100-104 12:0.5
        		"hallOfFame013", "hallOfFame014", "hallOfFame015", "hallOfFame016", "hallOfFame017", //105-109 13:0.5 14:21.33 15:? 16:0.499 17:0.5
        		"hallOfFame018", "hallOfFame019", "hallOfFame020", "hallOfFame021", "hallOfFame022", //110-114 19:0.5v20:0.3749 21:0.75 22:?
        		"hallOfFame023"};//23:1(First finished with new method 


        //Other settings
        boolean visuals = true;
        int seed = new Random().nextInt();

        //Game and level to play
        int gameIdx = 113;
        int levelIdx = 0; //level names from 0 to 4 (game_lvlN.txt).
        String game = gamesPath + games[gameIdx] + ".txt";
        String level1 = gamesPath + games[gameIdx] + "_lvl" + levelIdx +".txt";

        String recordLevelFile = generateLevelPath + games[gameIdx] + "_glvl.txt";
        String recordActionsFile = null;//"actions_" + games[gameIdx] + "_lvl" + levelIdx + "_" + seed + ".txt"; //where to record the actions executed. null if not to save.
        
        // 1. This starts a game, in a level, played by a human.
        //ArcadeMachine.playOneGame(game, level1, recordActionsFile, seed);
        
        // 2. This plays a game in a level by the controller.
        //ArcadeMachine.runOneGame(game, level1, visuals, sampleMCTSController, recordActionsFile, seed, 0);
        
        // 3. This replays a game from an action file previously recorded
        //String readActionsFile = recordActionsFile;
        //ArcadeMachine.replayGame(game, level1, visuals, readActionsFile);

        // 4. This plays a single game, in N levels, M times :
//        String level2 = gamesPath + games[gameIdx] + "_lvl" + 1 +".txt";
//        int M = 10;
//        for(int i=0; i<games.length; i++){
//        	game = gamesPath + games[i] + ".txt";
//        	level1 = gamesPath + games[i] + "_lvl" + levelIdx +".txt";
//        	ArcadeMachine.runGames(game, new String[]{level1}, M, sampleMCTSController, null);
//        }
        
        //5. This starts a game, in a generated level created by a specific level generator played by a human

        //if(ArcadeMachine.generateOneLevel(game, constructiveLevelGenerator, recordLevelFile)){
        	//ArcadeMachine.playOneGeneratedLevel(game, recordActionsFile, recordLevelFile, seed);
        //	ArcadeMachine.runOneGeneratedLevel(game, true, sampleMCTSController, recordActionsFile, recordLevelFile, 5, false);
        //}
        
        //6. This starts a game, in a generated level created by a specific level generator played by a selected agent
        
        //if(ArcadeMachine.generateOneLevel(game, constructiveLevelGenerator, recordLevelFile)){
        //	ArcadeMachine.runOneGeneratedLevel(game, true, sampleMCTSController, recordActionsFile, recordLevelFile, 5, false);
        //}
        
        //7. This plays N games, in the first L levels, M times each. Actions to file optional (set saveActions to true).
//        int N = 82, L = 5, M = 1;
//        boolean saveActions = false;
//        String[] levels = new String[L];
//        String[] actionFiles = new String[L*M];
//        for(int i = 0; i < N; ++i)
//        {
//            int actionIdx = 0;
//            game = gamesPath + games[i] + ".txt";
//            for(int j = 0; j < L; ++j){
//                levels[j] = gamesPath + games[i] + "_lvl" + j +".txt";
//                if(saveActions) for(int k = 0; k < M; ++k)
//                    actionFiles[actionIdx++] = "actions_game_" + i + "_level_" + j + "_" + k + ".txt";
//            }
//            ArcadeMachine.runGames(game, levels, M, sampleMCTSController, saveActions? actionFiles:null);
//        }
        
        
    }
}
