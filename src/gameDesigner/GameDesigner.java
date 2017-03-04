package gameDesigner;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class GameDesigner {
	
	String path ="C:" + File.separator + "Users" + File.separator + "Elliot" + File.separator + "Documents" + File.separator + "GitHub" + File.separator + "CoevolutionProject" + File.separator + "examples" + File.separator + "gridphysics" + File.separator + "earlyAttempts.txt";
	String savePath ="C:" + File.separator + "Users" + File.separator + "Elliot" + File.separator + "Documents" + File.separator + "GitHub" + File.separator + "CoevolutionProject" + File.separator + "examples" + File.separator + "gridphysics" + File.separator + "hallOfFame022.txt";
	String saveSymbols = "C:" + File.separator + "Users" + File.separator + "Elliot" + File.separator + "Documents" + File.separator + "GitHub" + File.separator + "CoevolutionProject" + File.separator + "src" + File.separator + "gameDesigner" + File.separator + "GamesAsSymbols" + File.separator + "hallOfFame022.ser";

	//C:\Users\Elliot\Documents\GitHub\CoevolutionProject\examples\gridphysics
	File f = new File(path);
	File s = new File(savePath);
	
	FileOutputStream fos = null;
	ObjectOutputStream out = null;
	
	FileInputStream fis = null;
	ObjectInputStream in = null;
	
	PrintWriter writer;
	PrintWriter saveWriter;
	
	enum section { LEVEL, SPRITE, INTERACTION, TERMINATION };
	
	//Symbols
	//Declare Non-Terminals
	NonTerminalSymbol game = new NonTerminalSymbol("game", false, false, false);
	NonTerminalSymbol levelBlock = new NonTerminalSymbol("levelBlock", false, false, false);
	NonTerminalSymbol spriteBlock = new NonTerminalSymbol("spriteBlock", false, false, false);
	NonTerminalSymbol interactionBlock = new NonTerminalSymbol("interactionBlock", false, false, false);
	NonTerminalSymbol terminationBlock = new NonTerminalSymbol("terminationBlock", false, false, false);
	NonTerminalSymbol charMap = new NonTerminalSymbol("charMap", false, false, false);
	NonTerminalSymbol avatarMap = new NonTerminalSymbol("avatarMap", false, false, false);
	NonTerminalSymbol spriteDef = new NonTerminalSymbol("sprieDef", false, false, false);
	NonTerminalSymbol interactionDef = new NonTerminalSymbol("interactionDef", false, false, false);
	NonTerminalSymbol terminationDef = new NonTerminalSymbol("terminationdef", false, false, false);
	NonTerminalSymbol spriteSimple = new NonTerminalSymbol("spriteSimple", false, false, false);
	NonTerminalSymbol avatarSimple = new NonTerminalSymbol("avatarSimple", false, false, false);
	NonTerminalSymbol option = new NonTerminalSymbol("option", false, false, false);
	NonTerminalSymbol eol = new NonTerminalSymbol("eol", false, false, false);
	NonTerminalSymbol avatarDefNewline = new NonTerminalSymbol("avatarDefNewline", false, false, false);
	NonTerminalSymbol avatarMapNewline = new NonTerminalSymbol("avatarMapNewline", false, false, false);
	//Repitition symbols
	NonTerminalSymbol charMapNewline = new NonTerminalSymbol("charMapNewline", false, false, false); //Will be repeated but is not defined as a repeatable symbol
	NonTerminalSymbol spriteDefNewline = new NonTerminalSymbol("spriteDefNewline", false, false, false); //Will be repeated but is not defined as a repeatable symbol
	NonTerminalSymbol interactionDefEol = new NonTerminalSymbol("interactionDefEol", false, false, false); //Will be repeated but is not defined as a repeatable symbol
	NonTerminalSymbol terminationDefEol = new NonTerminalSymbol("terminationDefEol", false, false, false); //Will be repeated but is not defined as a repeatable symbol
	NonTerminalSymbol spaceSpriteType = new NonTerminalSymbol("spaceSpriteType", true, false, false);
	NonTerminalSymbol spriteDefEol = new NonTerminalSymbol("spriteDefEol", true, false, false);
	NonTerminalSymbol spaceOption = new NonTerminalSymbol("spaceOption", true, false, false);
	NonTerminalSymbol spaceRepeat = new NonTerminalSymbol("spaceRepeat", true, false, false);
	NonTerminalSymbol charOrSpaceRepeat = new NonTerminalSymbol("charOrSpaceRepeat", true, false, false);
	//Optional symbols	
	//NonTerminalSymbol spriteDefOptionalBlock = new NonTerminalSymbol("spriteDefOptional", false, true, false);
	//NonTerminalSymbol spriteSimpleOptionalBlock = new NonTerminalSymbol("spriteSimpleOptional", false, true, false);
	//NonTerminalSymbol eolOptionalBlock = new NonTerminalSymbol("eolOptional", false, true, false);
	//choice symbols (symbol where the string will be chosen by the algorithm
	NonTerminalSymbol charOrSpace = new NonTerminalSymbol("charOrSpace", false, false, true);
	NonTerminalSymbol spriteTypeOrEvaluable = new NonTerminalSymbol("spriteTypeOrEvaluable", false, false, true);
	NonTerminalSymbol spriteType = new NonTerminalSymbol("spriteType", false, false, true);
	NonTerminalSymbol spriteTypeMinusAvatar = new NonTerminalSymbol("spriteTypeMinusAvatar", false, false, true);
	NonTerminalSymbol evaluable = new NonTerminalSymbol("evaluable", false, false, true);
	//NonTerminalSymbol game_class; //probably not needed for the type of games we are making, also can't find a clear list of what this would contain
	NonTerminalSymbol sprite_class = new NonTerminalSymbol("sprite_class", false, false, true);
	NonTerminalSymbol avatar_class = new NonTerminalSymbol("avatar_class", false, false, true);
	NonTerminalSymbol interaction_method = new NonTerminalSymbol("interaction_method", false, false, true);
	NonTerminalSymbol termination_class = new NonTerminalSymbol("termination_class", false, false, true);
	NonTerminalSymbol identifier = new NonTerminalSymbol("identifier", false, false, true);
	//Making a few more of these to help distinguish things later (it sort of makes sense when you look through it)
	NonTerminalSymbol levelIdentifier = new NonTerminalSymbol("levelIdentifier", false, false, true);
	NonTerminalSymbol spriteIdentifier = new NonTerminalSymbol("spriteIdentifier", false, false, true);
	NonTerminalSymbol parameter = new NonTerminalSymbol("parameter", false, false, true);
	NonTerminalSymbol charVar = new NonTerminalSymbol("char", false, false, true);
	NonTerminalSymbol evaluableBoolean = new NonTerminalSymbol("boolean", false, false ,true);
	NonTerminalSymbol evaluableFloat = new NonTerminalSymbol("float", false, false, true);
	NonTerminalSymbol evaluableInt = new NonTerminalSymbol("int", false, false, true);
	NonTerminalSymbol terminationOption = new NonTerminalSymbol("terminationOption", false, false ,true);
	NonTerminalSymbol evaluableDirection = new NonTerminalSymbol("direction", false, false, true);
	NonTerminalSymbol evaluableScoreInt = new NonTerminalSymbol("scoreInt", false, false, true);
	NonTerminalSymbol evaluableLargeInt = new NonTerminalSymbol("largeInt", false, false, true);
	
	String[] identifiers = {"var1", "var2", "var3", "var4", "var5"};
	String[] parameters = {"avatar", "var1", "var2", "var3", "var4", "var5"};
	String[] chars = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
	String[] evaluableBooleans = {"True", "False"};
	String[] evaluableFloats = {"0.1","0.2","0.3","0.4","0.5","0.6","0.7","0.8","0.9"}; //make for loop to expand
	String[] evaluableInts = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20"}; //make for loop to expand
	String[] terminationOptions = {"win=True", "win=False"};
	String[] evaluableDirections = {"UP", "DOWN", "LEFT", "RIGHT"};
	String[] evaluableScoreInts = {"-3", "-2", "-1", "1", "2", "3"};
	String[] evaluableLargeInts = new String[150];

		/*InterchangableSymbol identifier = new InterchangableSymbol("identifier", identifiers);
		InterchangableSymbol parameter = new InterchangableSymbol("parameter", parameters); //basically the same thing named differently so that parameters are treated differently to identifiers
		InterchangableSymbol charVar = new InterchangableSymbol("char", chars);
		InterchangableSymbol evaluableBoolean = new InterchangableSymbol("evaluable", evaluableBooleans);
		InterchangableSymbol evaluableFloat = new InterchangableSymbol("evaluable", evaluableFloats);
		InterchangableSymbol evaluableInt = new InterchangableSymbol("evaluable", evaluableInts);
		InterchangableSymbol terminationOption = new InterchangableSymbol("terminationOption", terminationOptions);
		InterchangableSymbol evaluableDirection = new InterchangableSymbol("evaluableDirection", evaluableDirections);
		InterchangableSymbol evaluableScoreInt = new InterchangableSymbol("evaluableScoreInt", evaluableScoreInts);
		InterchangableSymbol evaluableLargeInt;*/
	
	
	//Declare Terminals	
	TerminalSymbol newline = new TerminalSymbol("newline", "\n");
	TerminalSymbol indent = new TerminalSymbol("indent","	");
	TerminalSymbol lambda = new TerminalSymbol("lambda","LAMBDA");
	TerminalSymbol greaterThan = new TerminalSymbol("greater than"," > ");
	TerminalSymbol hash = new TerminalSymbol("hash", "#");
	TerminalSymbol space = new TerminalSymbol("space", " ");
	TerminalSymbol equals = new TerminalSymbol("equals", "=");
	//strings
	TerminalSymbol game_class = new TerminalSymbol("game_class", "BasicGame");
	TerminalSymbol levelMapping = new TerminalSymbol("levelMapping","LevelMapping");
	TerminalSymbol avatar = new TerminalSymbol("avatar","avatar");
	TerminalSymbol wall = new TerminalSymbol("wall","wall");
	TerminalSymbol eos = new TerminalSymbol("eos", "EOS");
	TerminalSymbol spriteSet = new TerminalSymbol("spriteSet","SpriteSet");
	TerminalSymbol interactionSet = new TerminalSymbol("interactionSet","InteractionSet");
	TerminalSymbol terminationSet = new TerminalSymbol("terminationSet","TerminationSet");
	TerminalSymbol scoreChangeString = new TerminalSymbol("scoreChangeString", "scoreChange=");
	
	
	//Avatar Sprite Classes
	//Terminals
	TerminalSymbol movingAvatar = new TerminalSymbol("MovingAvatar", "MovingAvatar", avatar_class);
	TerminalSymbol horizontalAvatar = new TerminalSymbol("HorizontalAvatar", "HorizontalAvatar", avatar_class);
	TerminalSymbol verticalAvatar = new TerminalSymbol("VerticalAvatar", "VerticalAvatar", avatar_class);
	TerminalSymbol ongoingAvatar = new TerminalSymbol("OngoingAvatar", "OngoingAvatar", avatar_class);
	TerminalSymbol ongoingTurningAvatar = new TerminalSymbol("OngoingTurningAvatar", "OngoingTurningAvatar", avatar_class);
	TerminalSymbol ongoingShootAvatar = new TerminalSymbol("OngoingShootAvatar", "OngoingShootAvatar", avatar_class);
	TerminalSymbol missileAvatar = new TerminalSymbol("MissileAvatar", "MissileAvatar", avatar_class);
	TerminalSymbol orientedAvatar = new TerminalSymbol("OrientedAvatar", "OrientedAvatar", avatar_class);
	//Non Terminals
	NonTerminalSymbol shootAvatar = new NonTerminalSymbol("ShootAvatar", false, false, false);
	NonTerminalSymbol flakAvatar = new NonTerminalSymbol("FlakAvatar", false, false, false);
	//avatar strings
	TerminalSymbol shootAvatarString = new TerminalSymbol("ShootAvatar", "ShootAvatar ", avatar_class);
	TerminalSymbol flakAvatarString = new TerminalSymbol("FlakAvatar", "FlakAvatar ", avatar_class);
	//avatar parameters
	//TerminalSymbol ammo = new TerminalSymbol("Ammo", "ammo="); //For now not going to give this to the GA to try and figure out
	//TerminalSymbol minAmmo = new TerminalSymbol("minAmmo", "minAmmo="); //commented same as ammo
	TerminalSymbol stype = new TerminalSymbol("stype", "stype="); 
	//Other Sprite Classes
	//Terminals
	TerminalSymbol immovable = new TerminalSymbol("Immovable", "Immovable", sprite_class);
	TerminalSymbol passive = new TerminalSymbol("Passive", "Passive", sprite_class);
	TerminalSymbol missile = new TerminalSymbol("Missile", "Missile", sprite_class);
	TerminalSymbol randomMissile = new TerminalSymbol("RandomMissile", "RandomMissile", sprite_class);
	TerminalSymbol randomNPC = new TerminalSymbol("RandomNPC", "RandomNPC", sprite_class);
	TerminalSymbol door = new TerminalSymbol("Door", "Door", sprite_class);
	//NonTerminals
	NonTerminalSymbol flicker = new NonTerminalSymbol("Flicker", false, false, false);
	NonTerminalSymbol orientedFlicker = new NonTerminalSymbol("OrientedFlick", false, false, false);
	NonTerminalSymbol chaser = new NonTerminalSymbol("Chaser", false, false, false);
	NonTerminalSymbol fleeing = new NonTerminalSymbol("Fleeing", false, false, false);
	NonTerminalSymbol alternateChaser = new NonTerminalSymbol("AlternateChaser", false, false, false);
	NonTerminalSymbol randomAltChaser = new NonTerminalSymbol("RandolAltChaser", false, false, false);
	NonTerminalSymbol spawnPoint = new NonTerminalSymbol("SpawnPoint", false, false, false);
	NonTerminalSymbol bomber = new NonTerminalSymbol("Bomber", false, false, false);
	NonTerminalSymbol randomBomber = new NonTerminalSymbol("RandomBomber", false, false, false);
	NonTerminalSymbol bomberRandomMissile = new NonTerminalSymbol("BomberRandomMissile", false, false, false);
	NonTerminalSymbol spreader = new NonTerminalSymbol("Spreader", false, false, false);
	NonTerminalSymbol portal = new NonTerminalSymbol("Portal", false, false, false);
	NonTerminalSymbol resource = new NonTerminalSymbol("Resource", false, false, false);
	//Other sprite class strings
	TerminalSymbol flickerString = new TerminalSymbol("flicker", "Flicker ", sprite_class);
	TerminalSymbol orientedFlickerString = new TerminalSymbol("orientedFlicker","OrientedFlicker ", sprite_class);
	TerminalSymbol chaserString = new TerminalSymbol("chaser", "Chaser ", sprite_class);
	TerminalSymbol fleeingString = new TerminalSymbol("fleeing", "Fleeing ", sprite_class);
	TerminalSymbol alternateChaserString = new TerminalSymbol("alternateChaser", "AlternateChaser ", sprite_class);
	TerminalSymbol randomAltChaserString = new TerminalSymbol("randomAltChaser", "RandomAltChaser ", sprite_class);
	TerminalSymbol spawnPointString = new TerminalSymbol("spawnPoint", "SpawnPoint ", sprite_class);
	TerminalSymbol bomberString	= new TerminalSymbol("bomber", "Bomber ", sprite_class);
	TerminalSymbol randomBomberString = new TerminalSymbol("randomBomberString", "RandomBomber ", sprite_class);
	TerminalSymbol bomberRandomMissileString = new TerminalSymbol("bomberRandomMissile", "BomberRandomMissile ", sprite_class);
	TerminalSymbol spreaderString = new TerminalSymbol("spreader", "Spreader ", sprite_class);
	TerminalSymbol portalString = new TerminalSymbol("portal", "Portal ", sprite_class);
	TerminalSymbol resourceString = new TerminalSymbol("resource", "Resource ", sprite_class);
	//Other sprite class parameters
	TerminalSymbol limit = new TerminalSymbol("limit", "limit=");
	TerminalSymbol stype1 = new TerminalSymbol("stype1", "stype1=");
	TerminalSymbol stype2 = new TerminalSymbol("stype2", "stype2=");
	TerminalSymbol prob = new TerminalSymbol("prob", "prob=");
	TerminalSymbol total = new TerminalSymbol("total", "total=");
	TerminalSymbol spawnorientation = new TerminalSymbol("spawnOrientation", "spawnorientation=");
	TerminalSymbol stypeMissile = new TerminalSymbol("stypeMissile", "stypeMissile=");
	//TerminalSymbol updateItype //more complicated so leaving for now
	TerminalSymbol spreadProb = new TerminalSymbol("spreadProb", "spreadprob=");
	TerminalSymbol resourceName = new TerminalSymbol("resource", "resource=");
	TerminalSymbol value = new TerminalSymbol("value", "value=");
	
	//Interaction methods
	//NonTerminals
	NonTerminalSymbol killSprite = new NonTerminalSymbol("killSprite", false, false, false);
	NonTerminalSymbol killAll = new NonTerminalSymbol("killAll", false, false, false);
	NonTerminalSymbol killIfHasMore = new NonTerminalSymbol("killIfHasMore", false, false, false);
	NonTerminalSymbol killIfHasLess = new NonTerminalSymbol("killIfHasLess", false, false, false);
	NonTerminalSymbol killIfFromAbove = new NonTerminalSymbol("killIfFromAbove", false, false, false);
	//NonTerminalSymbol killIfOtherHasMore = new NonTerminalSymbol("killIfOtherHasMore", false, false, false);
	NonTerminalSymbol transformToSingleton = new NonTerminalSymbol("transformToSingleton", false, false, false);
	NonTerminalSymbol spawnBehind = new NonTerminalSymbol("spawnBehind", false, false, false);
	NonTerminalSymbol spawnIfHasMore = new NonTerminalSymbol("spawnIfHasMore", false, false, false);
	NonTerminalSymbol spawnIfHasLess = new NonTerminalSymbol("spawnIfHasLess", false, false, false);
	NonTerminalSymbol cloneSprite = new NonTerminalSymbol("cloneSprite", false, false, false);
	NonTerminalSymbol transformTo = new NonTerminalSymbol("transformTo", false, false, false);
	NonTerminalSymbol transformIfCounts = new NonTerminalSymbol("transformIfCounts", false, false, false);
	NonTerminalSymbol transformToRandomChild = new NonTerminalSymbol("transformToRandomChild", false, false, false);
	NonTerminalSymbol updateSpawnType = new NonTerminalSymbol("updateSpawnType", false, false, false);
	NonTerminalSymbol removeScore = new NonTerminalSymbol("removeScore", false, false,false);
	NonTerminalSymbol addHealthPoints = new NonTerminalSymbol("addHealthPoints", false, false, false);
	NonTerminalSymbol addHealthPointsToMax = new NonTerminalSymbol("addHealthPointToMax",false,false,false);
	NonTerminalSymbol subtractHealthPoints = new NonTerminalSymbol("subtracHealthPoints",false,false,false);
	NonTerminalSymbol increaseSpeedToAll = new NonTerminalSymbol("increaseSpeedToAll",false,false,false);
	NonTerminalSymbol decreaseSpeedToAll = new NonTerminalSymbol("decreaseSpeedToAll",false,false,false);
	NonTerminalSymbol setSpeedForAll = new NonTerminalSymbol("setSpeedForAll",false,false,false);
	NonTerminalSymbol stepBack = new NonTerminalSymbol("stepBack", false, false, false);
	NonTerminalSymbol undoAll = new NonTerminalSymbol("undoAll", false, false, false);
	NonTerminalSymbol flipDirection = new NonTerminalSymbol("flipDirection", false, false, false);
	NonTerminalSymbol reverseDirection = new NonTerminalSymbol("reverseDirection", false, false, false);
	NonTerminalSymbol attractGaze = new NonTerminalSymbol("attractGaze", false, false, false);
	NonTerminalSymbol align = new NonTerminalSymbol("align", false, false, false);
	NonTerminalSymbol turnAround = new NonTerminalSymbol("turnAround", false, false, false);
	NonTerminalSymbol wrapAround = new NonTerminalSymbol("wrapAround", false, false, false);
	NonTerminalSymbol teleportToExit = new NonTerminalSymbol("teleportToExit", false, false, false);
	NonTerminalSymbol pullWithIt = new NonTerminalSymbol("pullWithIt", false, false, false);
	NonTerminalSymbol bounceForward	 = new NonTerminalSymbol("bounceForward", false, false, false);
	NonTerminalSymbol collectResource = new NonTerminalSymbol("collectResource", false, false, false);
	NonTerminalSymbol changeResource = new NonTerminalSymbol("changeResource",false,false,false);
	//Interaction method strings
	TerminalSymbol killSpriteString = new TerminalSymbol("killSprite", "killSprite ", interaction_method);
	TerminalSymbol killAllString = new TerminalSymbol("killAll","killAll ", interaction_method);
	TerminalSymbol killIfHasMoreString = new TerminalSymbol("killIfHasMore", "killIfHasMore ", interaction_method);
	TerminalSymbol killIfHasLessString = new TerminalSymbol("killIfHasLess", "killIfHasLess ", interaction_method);
	TerminalSymbol killIfFromAboveString = new TerminalSymbol("killIfFromAbove", "killIfFromAbove ", interaction_method);
	TerminalSymbol killIfOtherHasMoreString = new TerminalSymbol("killIfOtherHasMore", "killIfOtherHasMore ", interaction_method);
	TerminalSymbol transformToSingletonString = new TerminalSymbol("transformToSingleton", "transformToSingleton ", interaction_method);
	TerminalSymbol spawnBehindString = new TerminalSymbol("spawnBehind", "spawnBehind ", interaction_method);
	TerminalSymbol spawnIfHasMoreString = new TerminalSymbol("spawIfHasMore", "spawnIfHasMore ", interaction_method);
	TerminalSymbol spawnIfHasLessString = new TerminalSymbol("spawnIfHasLess", "spawnIfHasLess ", interaction_method);
	TerminalSymbol cloneSpriteString = new TerminalSymbol("cloneSprite", "cloneSprite ", interaction_method);
	TerminalSymbol transformToString = new TerminalSymbol("transformTo", "transformTo ", interaction_method);
	TerminalSymbol transformIfCountsString = new TerminalSymbol("transformIfCounts", "transformIfCounts ", interaction_method);
	TerminalSymbol transformToRandomChildString = new TerminalSymbol("transformToRandomChild", "transformToRandomChild ", interaction_method);
	TerminalSymbol updateSpawnTypeString = new TerminalSymbol("updateSpawnType", "updateSpawnType ", interaction_method);
	TerminalSymbol removeScoreString = new TerminalSymbol("removeScore", "removeScore ", interaction_method);
	TerminalSymbol addHealthPointsString = new TerminalSymbol("addHealthPoints","addHealthPoints ", interaction_method);
	TerminalSymbol addHealthPointsToMaxString = new TerminalSymbol("addHealthPointsToMax", "addHealthPointsToMax ", interaction_method);
	TerminalSymbol subtractHealthPointsString = new TerminalSymbol("subtractHealthPoints", "subtractHealthPoints ", interaction_method);
	TerminalSymbol increaseSpeedToAllString = new TerminalSymbol("increaseSpeedToAll", "increaseSpeedToAll ", interaction_method);
	TerminalSymbol decreaseSpeedToAllString = new TerminalSymbol("decreaseSpeedToAll", "decreaseSpeedToAll ", interaction_method);
	TerminalSymbol setSpeedForAllString = new TerminalSymbol("setSpeedForAll", "setSpeedForAll ", interaction_method);
	TerminalSymbol stepBackString = new TerminalSymbol("stepBack", "stepBack ", interaction_method);
	TerminalSymbol undoAllString = new TerminalSymbol("undoAll", "undoAll ", interaction_method);
	TerminalSymbol flipDirectionString = new TerminalSymbol("flipDirection", "flipDirection ", interaction_method);
	TerminalSymbol reverseDirectionString = new TerminalSymbol("reverseDirection", "reverseDirection ", interaction_method);
	TerminalSymbol attractGazeString = new TerminalSymbol("attractGaze", "attractGaze ", interaction_method);
	TerminalSymbol alignString = new TerminalSymbol("align","align ", interaction_method);
	TerminalSymbol turnAroundString = new TerminalSymbol("turnAround", "turnAround ", interaction_method);
	TerminalSymbol wrapAroundString = new TerminalSymbol("wrapAround", "wrapAround ", interaction_method);
	TerminalSymbol teleportToExitString = new TerminalSymbol("teleportToExit", "teleportToExit ", interaction_method);
	TerminalSymbol pullWithItString = new TerminalSymbol("pullWithIt", "pullWithIt ", interaction_method);
	TerminalSymbol bounceForwardString = new TerminalSymbol("bounceForward", "bounceForward ", interaction_method);
	TerminalSymbol collectResourceString = new TerminalSymbol("collectResource", "collectResource ", interaction_method);
	TerminalSymbol changeResourceString = new TerminalSymbol("changeResource", "changeResource ", interaction_method);
	//Interaction method parameters
	TerminalSymbol stype_other = new TerminalSymbol("stype_other","stype_other=");
	TerminalSymbol forceOrientation = new TerminalSymbol("forceOrientation", "forceOrientation=");
	TerminalSymbol stypeCount = new TerminalSymbol("stypeCount", "stypeCount=");
	TerminalSymbol geq = new TerminalSymbol("geq","geq=");
	TerminalSymbol leq = new TerminalSymbol("leq","leq=");
	TerminalSymbol spawnPointParam = new TerminalSymbol("spawnPoint", "spawnPoint=");
	NonTerminalSymbol scoreChange = new NonTerminalSymbol("scoreChange", false, true, false);
	//Termination Classes
	//NonTerminals
	NonTerminalSymbol spriteCounter = new NonTerminalSymbol("SpriteCounter",false,false,false);
	NonTerminalSymbol spriteCounterMore = new NonTerminalSymbol("SpriteCounterMore",false,false,false);
	NonTerminalSymbol multiSpriteCounter = new NonTerminalSymbol("MultiSpriteCounter",false,false,false);
	NonTerminalSymbol multiSpriteCounterSubTypes = new NonTerminalSymbol("MultiSpriteCounterSubTypes",false,false,false);
	NonTerminalSymbol stopCounter = new NonTerminalSymbol("StopCounter",false,false,false);
	NonTerminalSymbol timeout = new NonTerminalSymbol("Timeout",false,false,false);
	//Termination class strings
	TerminalSymbol spriteCounterString = new TerminalSymbol("spriteCounter", "SpriteCounter ", termination_class);
	TerminalSymbol spriteCounterMoreString = new TerminalSymbol("spriteCounterMore", "SpriteCounterMore ", termination_class);
	TerminalSymbol multiSpriteCounterString = new TerminalSymbol("multiSpriteCounter", "MultiSpriteCounter ", termination_class);
	TerminalSymbol multiSpriteCounterSubTypesString = new TerminalSymbol("multiSpriteCounterSubTypes", "MultiSpriteCounterSubTypes ", termination_class);
	TerminalSymbol stopCounterString = new TerminalSymbol("stopCounter", "StopCounter ", termination_class);
	TerminalSymbol timeOutString = new TerminalSymbol("timeout", "Timeout ", termination_class);
	//Termination class params
	TerminalSymbol stype3 = new TerminalSymbol("stype3", "stype3=");
	
	
	List<Symbol> gameSymbols = new LinkedList<Symbol>();
	
	Random rnd = new Random();
	
	public GameDesigner() {
		initiliseVGDLSymbols();		
	}
	
	private void initiliseVGDLSymbols() { //May remove this entirely later, it was useful earlier until I changed things
		for (int i=0; i < 150; i++) {
			evaluableLargeInts[i] = Integer.toString(500 + (i * 10));
		}
		addChildrenVGDLSymbols();		
	}
	
	public void addChildrenVGDLSymbols () {
		//Add Children
				//game
				game.addChild(game_class);
				game.addChild(eol);
				game.addChild(levelBlock);
				game.addChild(spriteBlock);
				game.addChild(interactionBlock);
				game.addChild(terminationBlock);
				//level-block
				levelBlock.addChild(indent);
				levelBlock.addChild(levelMapping);
				levelBlock.addChild(eol);
				levelBlock.addChild(avatarMapNewline);
				levelBlock.addChild(charMapNewline);
				//sprite-block
				spriteBlock.addChild(indent);
				spriteBlock.addChild(spriteSet);
				spriteBlock.addChild(eol);
				spriteBlock.addChild(avatarDefNewline);
				spriteBlock.addChild(spriteDefNewline);
				//interaction-block
				interactionBlock.addChild(indent);
				interactionBlock.addChild(interactionSet);
				interactionBlock.addChild(eol);
				interactionBlock.addChild(interactionDefEol);
				//termination-block
				terminationBlock.addChild(indent);
				terminationBlock.addChild(terminationSet);
				terminationBlock.addChild(eol);
				terminationBlock.addChild(terminationDefEol);
				terminationBlock.addChild(terminationDefEol);
				//char-map
				charMap.addChild(charVar);
				charMap.addChild(greaterThan);
				charMap.addChild(levelIdentifier);
				//charMap.addChild(spaceSpriteType);
				//avatarMap
				avatarMap.addChild(charVar);
				avatarMap.addChild(greaterThan);
				avatarMap.addChild(avatar);
				//sprite-def
				spriteDef.addChild(spriteSimple);
				//spriteDef.addChild(spriteDefOptionalBlock);
				//sprite-simple
				spriteSimple.addChild(spriteIdentifier);
				spriteSimple.addChild(greaterThan);
				spriteSimple.addChild(sprite_class);
				//spriteSimple.addChild(space);
				//spriteSimple.addChild(option); //I believe this is not currently necessary as parameters are done serparately
				//avatarSimple
				avatarSimple.addChild(avatar);
				avatarSimple.addChild(greaterThan);
				avatarSimple.addChild(avatar_class);				
				//interaction-def
				interactionDef.addChild(spriteType);
				interactionDef.addChild(space);
				interactionDef.addChild(spriteType);
				interactionDef.addChild(greaterThan);
				interactionDef.addChild(interaction_method);
				interactionDef.addChild(space);
				//interactionDef.addChild(option);
				//termination-def
				terminationDef.addChild(termination_class);
				//terminationDef.addChild(space);
				//terminationDef.addChild(terminationOption);
				//eol
				eol.addChild(newline);
				//eol.addChild(eolOptionalBlock);
				//option
				option.addChild(identifier);
				option.addChild(equals);
				option.addChild(spriteTypeOrEvaluable);
				//Repeaters
				//charMapNewline
				charMapNewline.addChild(indent);
				charMapNewline.addChild(indent);
				charMapNewline.addChild(charMap);
				charMapNewline.addChild(newline);
				//avatarMapNewline
				avatarMapNewline.addChild(indent);
				avatarMapNewline.addChild(indent);
				avatarMapNewline.addChild(avatarMap);
				avatarMapNewline.addChild(newline);
				//spriteDefNewline
				spriteDefNewline.addChild(indent);
				spriteDefNewline.addChild(indent);
				spriteDefNewline.addChild(spriteDef);
				spriteDefNewline.addChild(newline);
				//avatarDefNewline
				avatarDefNewline.addChild(indent);
				avatarDefNewline.addChild(indent);
				avatarDefNewline.addChild(avatarSimple);
				avatarDefNewline.addChild(newline);
				//interactionDefEol
				interactionDefEol.addChild(indent);
				interactionDefEol.addChild(indent);
				interactionDefEol.addChild(interactionDef);
				interactionDefEol.addChild(eol);
				//terminationDefEol
				terminationDefEol.addChild(indent);
				terminationDefEol.addChild(indent);
				terminationDefEol.addChild(terminationDef);
				terminationDefEol.addChild(eol);
				//spacecSpriteType
				spaceSpriteType.addChild(space);
				spaceSpriteType.addChild(spriteType);
				//spriteDefEol
				spriteDefEol.addChild(indent);
				spriteDefEol.addChild(indent);
				spriteDefEol.addChild(spriteDef);
				spriteDefEol.addChild(eol);
				//spaceOption
				spaceOption.addChild(space);
				spaceOption.addChild(option);
				//spaceRepeat
				spaceRepeat.addChild(space);
				//charOrSpaceRepeat
				charOrSpaceRepeat.addChild(charOrSpace);

				//Optionals
				//spriteDefOptionalBlock
				/*spriteDefOptionalBlock.addChild(eol);
				spriteDefOptionalBlock.addChild(indent);
				spriteDefOptionalBlock.addChild(spriteDefEol);
				//spriteSimpleOptionalBlock
				spriteSimpleOptionalBlock.addChild(sprite_class);
				//eolOptionalBlock
				eolOptionalBlock.addChild(hash);
				eolOptionalBlock.addChild(charOrSpaceRepeat);*/
				//scoreChange
				scoreChange.addChild(scoreChangeString);
				scoreChange.addChild(evaluableScoreInt);
				
				//Choices
				//spriteType
				spriteType.addChild(identifier);
				spriteType.addChild(avatar);
				spriteType.addChild(identifier);
				spriteType.addChild(identifier);
				spriteType.addChild(identifier);
				//spriteType.addChild(wall);
				spriteType.addChild(eos); //EOS should be used less frequently than the others so the others are added multiple time to try and balance this out
				//spriteTypeMinusAvatar
				spriteTypeMinusAvatar.addChild(levelIdentifier);
				//charOrSpace
				charOrSpace.addChild(charVar);
				charOrSpace.addChild(space);
				//spriteTypeOrEvaluable
				spriteTypeOrEvaluable.addChild(spriteType);
				spriteTypeOrEvaluable.addChild(evaluable);
				//evaluable
				evaluable.addChild(evaluableBoolean);
				evaluable.addChild(evaluableFloat);
				evaluable.addChild(evaluableInt);
				//avatar_class
				avatar_class.addChild(movingAvatar);
				avatar_class.addChild(horizontalAvatar);
				avatar_class.addChild(verticalAvatar);
				avatar_class.addChild(ongoingAvatar);
				avatar_class.addChild(ongoingTurningAvatar);
				//avatar_class.addChild(ongoingShootAvatar);
				avatar_class.addChild(missileAvatar);
				avatar_class.addChild(orientedAvatar);
				avatar_class.addChild(shootAvatar);
				avatar_class.addChild(flakAvatar);
				//sprite_class
				sprite_class.addChild(immovable);
				sprite_class.addChild(passive);
				sprite_class.addChild(flicker);
				sprite_class.addChild(orientedFlicker);
				sprite_class.addChild(missile);
				sprite_class.addChild(randomMissile);
				sprite_class.addChild(randomNPC);
				sprite_class.addChild(chaser);
				sprite_class.addChild(fleeing);
				sprite_class.addChild(alternateChaser);
				sprite_class.addChild(randomAltChaser);
				sprite_class.addChild(spawnPoint);
				sprite_class.addChild(bomber);
				sprite_class.addChild(randomBomber);
				sprite_class.addChild(bomberRandomMissile);
				sprite_class.addChild(spreader);
				sprite_class.addChild(door);
				sprite_class.addChild(portal);
				//sprite_class.addChild(resource);
				//interaction_method
				interaction_method.addChild(killSprite);
				interaction_method.addChild(killAll);
				interaction_method.addChild(killIfHasMore);
				interaction_method.addChild(killIfHasLess);
				interaction_method.addChild(killIfFromAbove);
				//interaction_method.addChild(killIfOtherHasMore);
				interaction_method.addChild(transformToSingleton);
				interaction_method.addChild(spawnBehind);
				interaction_method.addChild(spawnIfHasMore);
				interaction_method.addChild(spawnIfHasLess);
				interaction_method.addChild(cloneSprite);
				interaction_method.addChild(transformTo);
				interaction_method.addChild(transformIfCounts);
				interaction_method.addChild(transformToRandomChild);
				interaction_method.addChild(updateSpawnType);
				interaction_method.addChild(removeScore);
				interaction_method.addChild(addHealthPoints);
				interaction_method.addChild(addHealthPointsToMax);
				interaction_method.addChild(subtractHealthPoints);
				interaction_method.addChild(increaseSpeedToAll);
				interaction_method.addChild(decreaseSpeedToAll);
				interaction_method.addChild(setSpeedForAll);
				interaction_method.addChild(stepBack);
				interaction_method.addChild(undoAll);
				interaction_method.addChild(flipDirection);
				interaction_method.addChild(reverseDirection);
				interaction_method.addChild(attractGaze);
				interaction_method.addChild(align);
				interaction_method.addChild(turnAround);
				interaction_method.addChild(wrapAround);
				interaction_method.addChild(teleportToExit);
				interaction_method.addChild(pullWithIt);
				interaction_method.addChild(bounceForward);
				//interaction_method.addChild(collectResource);
				//interaction_method.addChild(changeResource);
				//termination__class
				termination_class.addChild(spriteCounter);
				termination_class.addChild(spriteCounterMore);
				//termination_class.addChild(multiSpriteCounter);
				//termination_class.addChild(multiSpriteCounterSubTypes); //Unclear from documentation how this works and seems to cause errors
				termination_class.addChild(stopCounter);
				termination_class.addChild(timeout);
				//Avatar sprite classes
				shootAvatar.addChild(shootAvatarString);
				shootAvatar.addChild(stype);
				shootAvatar.addChild(parameter);
				flakAvatar.addChild(flakAvatarString);
				flakAvatar.addChild(stype);
				flakAvatar.addChild(parameter);
				//flakAvatar ammo params current not being used
				//Other sprite classes
				flicker.addChild(flickerString);
				flicker.addChild(limit);
				flicker.addChild(evaluableInt);
				orientedFlicker.addChild(orientedFlickerString);
				orientedFlicker.addChild(limit);
				orientedFlicker.addChild(evaluableInt);
				chaser.addChild(chaserString);
				chaser.addChild(stype);
				chaser.addChild(parameter);
				fleeing.addChild(fleeingString);
				fleeing.addChild(stype);
				fleeing.addChild(parameter);
				alternateChaser.addChild(alternateChaserString);
				alternateChaser.addChild(stype1);
				alternateChaser.addChild(parameter);
				alternateChaser.addChild(space);
				alternateChaser.addChild(stype2);
				alternateChaser.addChild(parameter);
				randomAltChaser.addChild(randomAltChaserString);
				randomAltChaser.addChild(stype1);
				randomAltChaser.addChild(parameter);
				randomAltChaser.addChild(space);
				randomAltChaser.addChild(stype2);
				randomAltChaser.addChild(parameter);
				randomAltChaser.addChild(space);
				randomAltChaser.addChild(prob);
				randomAltChaser.addChild(evaluableFloat);
				spawnPoint.addChild(spawnPointString);
				spawnPoint.addChild(stype);
				spawnPoint.addChild(parameter);
				spawnPoint.addChild(space);
				spawnPoint.addChild(total);
				spawnPoint.addChild(evaluableInt);
				spawnPoint.addChild(space);
				spawnPoint.addChild(prob);
				spawnPoint.addChild(evaluableFloat);
				spawnPoint.addChild(space);
				spawnPoint.addChild(spawnorientation);
				spawnPoint.addChild(evaluableDirection);
				bomber.addChild(bomberString);
				bomber.addChild(stype);
				bomber.addChild(parameter);
				bomber.addChild(space);
				bomber.addChild(total);
				bomber.addChild(evaluableInt);
				bomber.addChild(space);
				bomber.addChild(spawnorientation);
				bomber.addChild(evaluableDirection);
				randomBomber.addChild(randomBomberString);
				randomBomber.addChild(stype);
				randomBomber.addChild(parameter);
				randomBomber.addChild(space);
				randomBomber.addChild(total);
				randomBomber.addChild(evaluableInt);
				randomBomber.addChild(space);
				randomBomber.addChild(prob);
				randomBomber.addChild(evaluableFloat);
				randomBomber.addChild(space);
				randomBomber.addChild(spawnorientation);
				randomBomber.addChild(evaluableDirection);
				bomberRandomMissile.addChild(bomberRandomMissileString);
				bomberRandomMissile.addChild(stypeMissile);
				bomberRandomMissile.addChild(parameter);
				bomberRandomMissile.addChild(space);
				bomberRandomMissile.addChild(total);
				bomberRandomMissile.addChild(evaluableInt);
				bomberRandomMissile.addChild(space);
				bomberRandomMissile.addChild(prob);
				bomberRandomMissile.addChild(evaluableFloat);
				bomberRandomMissile.addChild(space);
				bomberRandomMissile.addChild(spawnorientation);
				bomberRandomMissile.addChild(evaluableDirection);
				spreader.addChild(spreaderString);
				spreader.addChild(stype);
				spreader.addChild(parameter);
				spreader.addChild(space);
				spreader.addChild(spreadProb);
				spreader.addChild(evaluableFloat);
				portal.addChild(portalString);
				portal.addChild(stype);
				portal.addChild(parameter);
				resource.addChild(resourceString);
				resource.addChild(resourceName);
				resource.addChild(parameter);
				resource.addChild(space);
				resource.addChild(value);
				resource.addChild(evaluableInt);
				resource.addChild(space);
				resource.addChild(limit);
				resource.addChild(evaluableInt);
				//Interaction Methods
				killSprite.addChild(killSpriteString);
				killSprite.addChild(scoreChange);
				killAll.addChild(killAllString);
				killAll.addChild(stype);
				killAll.addChild(parameter);
				killAll.addChild(space);
				killAll.addChild(scoreChange);
				killIfHasMore.addChild(killIfHasMoreString);
				killIfHasMore.addChild(resourceName);
				killIfHasMore.addChild(parameter);
				killIfHasMore.addChild(space);
				killIfHasMore.addChild(limit);
				killIfHasMore.addChild(evaluableInt);
				killIfHasMore.addChild(space);
				killIfHasMore.addChild(scoreChange);
				killIfHasLess.addChild(killIfHasLessString);
				killIfHasLess.addChild(resourceName);
				killIfHasLess.addChild(parameter);
				killIfHasLess.addChild(space);
				killIfHasLess.addChild(limit);
				killIfHasLess.addChild(evaluableInt);
				killIfHasLess.addChild(space);
				killIfHasLess.addChild(scoreChange);
				killIfFromAbove.addChild(killIfFromAboveString);
				killIfFromAbove.addChild(scoreChange);
				/*killIfOtherHasMore.addChild(killIfOtherHasMoreString);
				killIfOtherHasMore.addChild(resourceName);
				killIfOtherHasMore.addChild(parameter);
				killIfOtherHasMore.addChild(space);
				killIfOtherHasMore.addChild(limit);
				killIfOtherHasMore.addChild(evaluableInt);
				killIfOtherHasMore.addChild(space);
				killIfOtherHasMore.addChild(scoreChange);*/
				transformToSingleton.addChild(transformToRandomChildString);
				transformToSingleton.addChild(stype);
				transformToSingleton.addChild(parameter);
				transformToSingleton.addChild(space);
				transformToSingleton.addChild(stype_other);
				transformToSingleton.addChild(parameter);
				transformToSingleton.addChild(space);
				transformToSingleton.addChild(scoreChange);
				spawnBehind.addChild(spawnBehindString);
				spawnBehind.addChild(stype);
				spawnBehind.addChild(parameter);
				spawnBehind.addChild(space);
				spawnBehind.addChild(scoreChange);
				spawnIfHasMore.addChild(spawnIfHasMoreString);
				spawnIfHasMore.addChild(stype);
				spawnIfHasMore.addChild(parameter);
				spawnIfHasMore.addChild(space);
				spawnIfHasMore.addChild(resourceName);
				spawnIfHasMore.addChild(parameter);
				spawnIfHasMore.addChild(space);
				spawnIfHasMore.addChild(limit);
				spawnIfHasMore.addChild(evaluableInt);
				spawnIfHasMore.addChild(space);
				spawnIfHasMore.addChild(scoreChange);
				spawnIfHasLess.addChild(spawnIfHasLessString);
				spawnIfHasLess.addChild(stype);
				spawnIfHasLess.addChild(parameter);
				spawnIfHasLess.addChild(space);
				spawnIfHasLess.addChild(resourceName);
				spawnIfHasLess.addChild(parameter);
				spawnIfHasLess.addChild(space);
				spawnIfHasLess.addChild(limit);
				spawnIfHasLess.addChild(evaluableInt);
				spawnIfHasLess.addChild(space);
				spawnIfHasLess.addChild(scoreChange);
				cloneSprite.addChild(cloneSpriteString);
				cloneSprite.addChild(scoreChange);
				transformTo.addChild(transformToString);
				transformTo.addChild(stype);
				transformTo.addChild(parameter);
				transformTo.addChild(space);
				transformTo.addChild(forceOrientation);
				transformTo.addChild(evaluableBoolean);
				transformTo.addChild(space);
				transformTo.addChild(scoreChange);
				transformIfCounts.addChild(transformIfCountsString);
				transformIfCounts.addChild(stype);
				transformIfCounts.addChild(parameter);
				transformIfCounts.addChild(space);
				transformIfCounts.addChild(stypeCount);
				transformIfCounts.addChild(evaluableInt);
				transformIfCounts.addChild(space);
				transformIfCounts.addChild(geq);
				transformIfCounts.addChild(evaluableInt);
				transformIfCounts.addChild(space);
				transformIfCounts.addChild(leq);
				transformIfCounts.addChild(evaluableInt);
				transformIfCounts.addChild(space);
				transformIfCounts.addChild(scoreChange);
				transformToRandomChild.addChild(transformToRandomChildString);
				transformToRandomChild.addChild(stype);
				transformToRandomChild.addChild(parameter);
				transformToRandomChild.addChild(space);
				transformToRandomChild.addChild(scoreChange);
				updateSpawnType.addChild(updateSpawnTypeString);
				updateSpawnType.addChild(stype);
				updateSpawnType.addChild(parameter);
				updateSpawnType.addChild(space);
				updateSpawnType.addChild(spawnPointParam);
				updateSpawnType.addChild(parameter);
				updateSpawnType.addChild(space);
				updateSpawnType.addChild(scoreChange);
				removeScore.addChild(removeScoreString);
				removeScore.addChild(stype);
				removeScore.addChild(parameter);
				addHealthPoints.addChild(addHealthPointsString);
				addHealthPoints.addChild(value);
				addHealthPoints.addChild(evaluableInt);
				addHealthPoints.addChild(space);
				addHealthPoints.addChild(scoreChange);
				addHealthPointsToMax.addChild(addHealthPointsToMaxString);
				addHealthPointsToMax.addChild(value);
				addHealthPointsToMax.addChild(evaluableInt);
				addHealthPointsToMax.addChild(space);
				addHealthPointsToMax.addChild(scoreChange);
				subtractHealthPoints.addChild(subtractHealthPointsString);
				subtractHealthPoints.addChild(value);
				subtractHealthPoints.addChild(evaluableInt);
				subtractHealthPoints.addChild(space);
				subtractHealthPoints.addChild(stype);
				subtractHealthPoints.addChild(parameter);
				subtractHealthPoints.addChild(space);
				subtractHealthPoints.addChild(scoreChange);
				increaseSpeedToAll.addChild(increaseSpeedToAllString);
				increaseSpeedToAll.addChild(value);
				increaseSpeedToAll.addChild(evaluableInt);
				increaseSpeedToAll.addChild(space);
				increaseSpeedToAll.addChild(stype);
				increaseSpeedToAll.addChild(parameter);
				increaseSpeedToAll.addChild(space);
				increaseSpeedToAll.addChild(scoreChange);
				decreaseSpeedToAll.addChild(decreaseSpeedToAllString);
				decreaseSpeedToAll.addChild(value);
				decreaseSpeedToAll.addChild(evaluableInt);
				decreaseSpeedToAll.addChild(space);
				decreaseSpeedToAll.addChild(stype);
				decreaseSpeedToAll.addChild(parameter);
				decreaseSpeedToAll.addChild(space);
				decreaseSpeedToAll.addChild(scoreChange);
				setSpeedForAll.addChild(setSpeedForAllString);
				setSpeedForAll.addChild(value);
				setSpeedForAll.addChild(evaluableInt);
				setSpeedForAll.addChild(space);
				setSpeedForAll.addChild(stype);
				setSpeedForAll.addChild(parameter);
				setSpeedForAll.addChild(space);
				setSpeedForAll.addChild(scoreChange);
				stepBack.addChild(stepBackString);
				stepBack.addChild(scoreChange);
				undoAll.addChild(undoAllString);
				undoAll.addChild(scoreChange);
				flipDirection.addChild(flipDirectionString);
				flipDirection.addChild(scoreChange);
				reverseDirection.addChild(reverseDirectionString);
				reverseDirection.addChild(scoreChange);
				attractGaze.addChild(attractGazeString);
				attractGaze.addChild(scoreChange);
				align.addChild(alignString);
				align.addChild(scoreChange);
				turnAround.addChild(turnAroundString);
				turnAround.addChild(scoreChange);
				wrapAround.addChild(wrapAroundString);
				wrapAround.addChild(scoreChange);
				teleportToExit.addChild(teleportToExitString);
				teleportToExit.addChild(scoreChange);
				pullWithIt.addChild(pullWithItString);
				pullWithIt.addChild(scoreChange);
				bounceForward.addChild(bounceForwardString);
				bounceForward.addChild(scoreChange);
				collectResource.addChild(collectResourceString);
				collectResource.addChild(scoreChange);
				changeResource.addChild(changeResourceString);
				changeResource.addChild(resourceName);
				changeResource.addChild(parameter);
				changeResource.addChild(space);
				changeResource.addChild(value);
				changeResource.addChild(evaluableInt);
				changeResource.addChild(space);
				changeResource.addChild(scoreChange);
				//termination classes
				spriteCounter.addChild(spriteCounterString);
				spriteCounter.addChild(stype);
				spriteCounter.addChild(identifier);
				spriteCounter.addChild(space);
				spriteCounter.addChild(limit);
				spriteCounter.addChild(evaluableInt);
				spriteCounter.addChild(space);
				spriteCounter.addChild(terminationOption);
				spriteCounterMore.addChild(spriteCounterMoreString);
				spriteCounterMore.addChild(stype);
				spriteCounterMore.addChild(identifier);
				spriteCounterMore.addChild(space);
				spriteCounterMore.addChild(limit);
				spriteCounterMore.addChild(evaluableInt);
				spriteCounterMore.addChild(space);
				spriteCounterMore.addChild(terminationOption);	
				multiSpriteCounterSubTypes.addChild(multiSpriteCounterSubTypesString);
				multiSpriteCounterSubTypes.addChild(stype1);
				multiSpriteCounterSubTypes.addChild(parameter);
				multiSpriteCounterSubTypes.addChild(space);
				multiSpriteCounterSubTypes.addChild(stype2);
				multiSpriteCounterSubTypes.addChild(parameter);
				multiSpriteCounterSubTypes.addChild(space);
				multiSpriteCounterSubTypes.addChild(stype3);
				multiSpriteCounterSubTypes.addChild(parameter);
				multiSpriteCounterSubTypes.addChild(space);
				multiSpriteCounterSubTypes.addChild(limit);
				multiSpriteCounterSubTypes.addChild(evaluableInt);
				multiSpriteCounterSubTypes.addChild(space);
				multiSpriteCounterSubTypes.addChild(terminationOption);
				stopCounter.addChild(stopCounterString);
				stopCounter.addChild(stype1);
				stopCounter.addChild(parameter);
				stopCounter.addChild(space);
				stopCounter.addChild(stype2);
				stopCounter.addChild(parameter);
				stopCounter.addChild(space);
				stopCounter.addChild(stype3);
				stopCounter.addChild(parameter);
				stopCounter.addChild(space);
				stopCounter.addChild(limit);
				stopCounter.addChild(evaluableInt);
				stopCounter.addChild(space);
				stopCounter.addChild(terminationOption);
				timeout.addChild(timeOutString);
				timeout.addChild(limit);
				timeout.addChild(evaluableLargeInt);
				timeout.addChild(space);
				timeout.addChild(terminationOption);
				//Old Interchangeables now NonTerminal choices
				//Identifier Lists already existed, left over from previous version so are being reused here to speed things up, could be done without lists if desired
				for (int i=0; i<identifiers.length; i++) {
					TerminalSymbol currentSymbol = new TerminalSymbol(identifiers[i], identifiers[i], identifier);
					identifier.addChild(currentSymbol);
				}
				for (int i=0; i<identifiers.length; i++) {
					TerminalSymbol currentSymbol = new TerminalSymbol(identifiers[i], identifiers[i], levelIdentifier);
					levelIdentifier.addChild(currentSymbol);
				}
				for (int i=0; i<identifiers.length; i++) {
					TerminalSymbol currentSymbol = new TerminalSymbol(identifiers[i], identifiers[i], spriteIdentifier);
					spriteIdentifier.addChild(currentSymbol);
				}
				//Parameter
				for (int i=0; i<parameters.length; i++) {
					parameter.addChild(avatar);
					TerminalSymbol currentSymbol = new TerminalSymbol(parameters[i], parameters[i], parameter);
					parameter.addChild(currentSymbol);
				}
				//CharVar
				for (int i=0; i<chars.length; i++) {
					TerminalSymbol currentSymbol = new TerminalSymbol(chars[i], chars[i]); //For now changing chars seems uncessary as it has little affect on how the game plays
					charVar.addChild(currentSymbol);
				}
				//Boolean
				for (int i=0; i<evaluableBooleans.length; i++) {
					TerminalSymbol currentSymbol = new TerminalSymbol(evaluableBooleans[i], evaluableBooleans[i], evaluableBoolean);
					evaluableBoolean.addChild(currentSymbol);
				}
				//Float
				for (int i=0; i<evaluableFloats.length; i++) {
					TerminalSymbol currentSymbol = new TerminalSymbol(evaluableFloats[i], evaluableFloats[i], evaluableFloat);
					evaluableFloat.addChild(currentSymbol);
				}
				//Int
				for (int i=0; i<evaluableInts.length; i++) {
					TerminalSymbol currentSymbol = new TerminalSymbol(evaluableInts[i], evaluableInts[i], evaluableInt);
					evaluableInt.addChild(currentSymbol);
				}
				//TerminationOption
				for (int i=0; i<terminationOptions.length; i++) {
					TerminalSymbol currentSymbol = new TerminalSymbol(terminationOptions[i], terminationOptions[i]); //does not need to be changed as it is only used twice with a fixed value each time (one win one loss condition) 
					terminationOption.addChild(currentSymbol);
				}
				//Direction
				for (int i=0; i<evaluableDirections.length; i++) {
					TerminalSymbol currentSymbol = new TerminalSymbol(evaluableDirections[i], evaluableDirections[i], evaluableDirection);
					evaluableDirection.addChild(currentSymbol);
				}
				//ScoreInts
				for (int i=0; i<evaluableScoreInts.length; i++) {
					TerminalSymbol currentSymbol = new TerminalSymbol(evaluableScoreInts[i], evaluableScoreInts[i], evaluableScoreInt);
					evaluableScoreInt.addChild(currentSymbol);
				}
				//LargeInts
				for (int i=0; i<evaluableLargeInts.length; i++) {
					TerminalSymbol currentSymbol = new TerminalSymbol(evaluableLargeInts[i], evaluableLargeInts[i], evaluableLargeInt);
					evaluableLargeInt.addChild(currentSymbol);
				}
	}
	
	
	public void createGame() {
		
		int i = 0;
		gameSymbols.add(game);

		//While there are still symbols that need to be expanded
		while (containsNonTerminals(gameSymbols)) {
			if (i > gameSymbols.size()-1) {
				i = 0;
			}
			Symbol currentSymbol = gameSymbols.get(i);
			
			if (currentSymbol instanceof NonTerminalSymbol) {
				//If symbol represents a repeatable section of the grammar
				if (((NonTerminalSymbol) currentSymbol).repeatable == true) {
					//EA makes decision somehow as to if the thing is repeated
					//for now its just 50/50 random
					if (rnd.nextInt(2) == 1) {
						gameSymbols.remove(i);
					}
				}
				else
				{
					//If symbol is not repeatable remove it 
					gameSymbols.remove(i);
				}
				//If symbol is an not optional add children otherwise decide if the the option will be used then continue as before
				if(((NonTerminalSymbol)currentSymbol).optional == false){
					if (((NonTerminalSymbol)currentSymbol).choice == true) {
						gameSymbols.add(i, ((NonTerminalSymbol)currentSymbol).children.get(rnd.nextInt(((NonTerminalSymbol)currentSymbol).children.size())));
						i++;
					}
					else {
						for (int j=0; j<((NonTerminalSymbol)currentSymbol).children.size(); j++) {
							gameSymbols.add(i, ((NonTerminalSymbol)currentSymbol).children.get(j));
							i++;
						}
					}
				}
				else {
					if (rnd.nextInt(2) == 1){ 
						for (int j=0; j<((NonTerminalSymbol)currentSymbol).children.size(); j++) {
							gameSymbols.add(i, ((NonTerminalSymbol)currentSymbol).children.get(j));
							i++;
						}
					}
				}
			}
			else {
				i++;
			}
		}
		//This will later be made to print to a file, but for now just prints it to console
		//It can still be tested by copying to a file
		for (int j=0; j<gameSymbols.size(); j++) {	
			if (gameSymbols.get(j) instanceof InterchangableSymbol) { 
				System.out.print(((InterchangableSymbol)gameSymbols.get(j)).classStrings[rnd.nextInt(((InterchangableSymbol)gameSymbols.get(j)).classStrings.length)]);
			}
			else {
				System.out.print(((TerminalSymbol)gameSymbols.get(j)).content);
			}
		}
	}
	

	public void loadGame()
	{		
		try {
			fis = new FileInputStream(saveSymbols);
			in = new ObjectInputStream(fis);
			gameSymbols = (List<Symbol>) in.readObject();
			in.close();
		}
		catch (Exception ex) {
			ex.printStackTrace();;
		}
		
		s.getParentFile().mkdirs();
		try {
		s.createNewFile();
		saveWriter = new PrintWriter(s);
		} catch (IOException e) { 
			e.printStackTrace();
		}
		for (int j=0; j<gameSymbols.size(); j++) {	
			if (gameSymbols.get(j) instanceof InterchangableSymbol) { 	//	--------INTERCHANGABLE SYMBOLS-----------	
					System.out.print(((InterchangableSymbol)gameSymbols.get(j)).classStrings[rnd.nextInt(255) % ((InterchangableSymbol)gameSymbols.get(j)).classStrings.length]);
					saveWriter.print(((InterchangableSymbol)gameSymbols.get(j)).classStrings[rnd.nextInt(255) % ((InterchangableSymbol)gameSymbols.get(j)).classStrings.length]);
			}
			else {
				
				System.out.print(((TerminalSymbol)gameSymbols.get(j)).content);
				
				saveWriter.print(((TerminalSymbol)gameSymbols.get(j)).content);
			}
		}
		saveWriter.close();
	}
	
public void saveGameFromGenome(int[] genome) {
		
		System.out.println("Saving individual in " + savePath);
	
		s.getParentFile().mkdirs();
		try {
		s.createNewFile();
		saveWriter = new PrintWriter(s);
		} catch (IOException e) { 
			e.printStackTrace();
		}
		
		int i = 0;
		gameSymbols.clear();
		gameSymbols.add(game);
		int genomeTracker = 0;
		int variablesUsed = 0;
		boolean levelBlockSizeDetermined = false;
		boolean spriteBlockSizeDetermined = false;
		boolean interactionBlockSizeDetermined = false;
		int levelVariableTracker = 0;
		int spriteVariableTracker = 0;
		int terminationTracker = 0;

		//While there are still symbols that need to be expanded
		while (containsNonTerminals(gameSymbols)) {
			//System.out.println(i);
			if (i > gameSymbols.size()-1) {
				i = 0;
			}
			Symbol currentSymbol = gameSymbols.get(i);
			
			if (currentSymbol instanceof NonTerminalSymbol) {
				//If symbol represents a repeatable section of the grammar 	------REPEATABLE SYMBOLS-----
				if (((NonTerminalSymbol) currentSymbol).repeatable == true) {
					if (genome[genomeTracker] % 2 == 0) {
						gameSymbols.remove(i);
					}
					genomeTracker++;
					if (genomeTracker > genome.length-1) {
						genomeTracker = 0;
					}
				}
				else
				{
					//If symbol is not repeatable remove it 
					gameSymbols.remove(i);
					//System.out.println(currentSymbol.name);
				}
				
				//This section is used to fix variables, without this there were many issues with variables being used without being defined 
				//or being defined and not used, this should ensure that variables declared in level block and used exactly once in sprite block
				//as a result this should produce many more working games
				//LevelBlock size
				if (currentSymbol.name == "charMapNewline" && !levelBlockSizeDetermined) {
					for (int j=0; j < (genome[genomeTracker] % 4) + 1; j++) {
						levelBlockSizeDetermined = true;
						gameSymbols.add(i,charMapNewline);
						variablesUsed++;
					}
					genomeTracker++;
					if (genomeTracker > genome.length-1) {
						genomeTracker = 0;
					}
				}
				//SpriteBlock size
				if (currentSymbol.name == "spriteDefNewline" && !spriteBlockSizeDetermined) {
					for (int j=0; j < variablesUsed; j++) {
						spriteBlockSizeDetermined = true;
						gameSymbols.add(i,spriteDefNewline);
					}
				}
				//InteractionBlock size
				if (currentSymbol.name == "interactionDefEol" && !interactionBlockSizeDetermined) {
					for (int j=0; j < (genome[genomeTracker] % 5) + 3; j++) {
						interactionBlockSizeDetermined = true;
						gameSymbols.add(i,interactionDefEol);
					}
					genomeTracker++;
					if (genomeTracker > genome.length-1) {
						genomeTracker = 0;
					}
				}
				
				//If symbol is an not optional add children otherwise decide if the the option will be used then continue as before
				if(((NonTerminalSymbol)currentSymbol).optional){  
					//OPTIONAL SECTION										---------OPTIONAL SYMBOLS-------
					if (genome[genomeTracker] % 2 == 0) { 
						for (int j=0; j<((NonTerminalSymbol)currentSymbol).children.size(); j++) {
							gameSymbols.add(i, ((NonTerminalSymbol)currentSymbol).children.get(j));
							i++;
						}
					}
					genomeTracker++;
					if (genomeTracker > genome.length-1) {
						genomeTracker = 0;
					}
				}
				else if (((NonTerminalSymbol)currentSymbol).choice == true) {
					//CHOICE SECTION										----------CHOICE SYMBOLS-----------
					if (currentSymbol.name == "levelIdentifier") {
						gameSymbols.add(i, ((NonTerminalSymbol)currentSymbol).children.get(levelVariableTracker));
						levelVariableTracker++;
					}
					else if (currentSymbol.name == "spriteIdentifier")
					{
						gameSymbols.add(i, ((NonTerminalSymbol)currentSymbol).children.get(spriteVariableTracker));
						spriteVariableTracker++;
					}
					else if (currentSymbol.name == "identifier") {
						gameSymbols.add(i, ((NonTerminalSymbol)currentSymbol).children.get(genome[genomeTracker] % variablesUsed));
						genomeTracker++;
						if (genomeTracker > genome.length-1) {
							genomeTracker = 0;
						}
					}
					else if (currentSymbol.name == "parameter") {
						gameSymbols.add(i, ((NonTerminalSymbol)currentSymbol).children.get(genome[genomeTracker] % (variablesUsed + 1)));
						genomeTracker++;
						if (genomeTracker > genome.length-1) {
							genomeTracker = 0;
						}
					}
					else if (currentSymbol.name == "terminationOption") {
						gameSymbols.add(i, ((NonTerminalSymbol)currentSymbol).children.get(terminationTracker));
						terminationTracker++;
					}
					else {
						gameSymbols.add(i, ((NonTerminalSymbol)currentSymbol).children.get(genome[genomeTracker] % (((NonTerminalSymbol)currentSymbol).children.size())));
						genomeTracker++;
						if (genomeTracker > genome.length-1) {
							genomeTracker = 0;
						}
						i++;
					}
				}
				else {
					for (int j=0; j<((NonTerminalSymbol)currentSymbol).children.size(); j++) {
						System.out.println(((NonTerminalSymbol)currentSymbol).children.get(j).name);
						gameSymbols.add(i, ((NonTerminalSymbol)currentSymbol).children.get(j));
						i++;
					}
				}
			}
			else {
				i++;
			}
		}
		
		for (int j=0; j<gameSymbols.size(); j++) {
				System.out.print(((TerminalSymbol)gameSymbols.get(j)).content);
				saveWriter.print(((TerminalSymbol)gameSymbols.get(j)).content);
		}
		saveWriter.close();
		try {
			fos = new FileOutputStream(saveSymbols);
			out = new ObjectOutputStream(fos);
			out.writeObject(gameSymbols);
			out.close();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
}

public List<Symbol> createGameFromGenome(int[] genome) {
	
	System.out.println("creating game from genome");

	f.getParentFile().mkdirs();
	try {
	f.createNewFile();
	writer = new PrintWriter(f);
	} catch (IOException e) { 
		e.printStackTrace();
	}
	
	int i = 0;
	gameSymbols.clear();
	gameSymbols.add(game);
	int genomeTracker = 0;
	int variablesUsed = 0;
	boolean levelBlockSizeDetermined = false;
	boolean spriteBlockSizeDetermined = false;
	boolean interactionBlockSizeDetermined = false;
	int levelVariableTracker = 0;
	int spriteVariableTracker = 0;
	int terminationTracker = 0;
	
	//While there are still symbols that need to be expanded
	while (containsNonTerminals(gameSymbols)) {
		//System.out.println(i);
		if (i > gameSymbols.size()-1) {
			i = 0;
		}
		Symbol currentSymbol = gameSymbols.get(i);
		
		if (currentSymbol instanceof NonTerminalSymbol) {
			//If symbol represents a repeatable section of the grammar 	------REPEATABLE SYMBOLS-----
			if (((NonTerminalSymbol) currentSymbol).repeatable == true) {
				if (genome[genomeTracker] % 2 == 0) {
					gameSymbols.remove(i);
				}
				genomeTracker++;
				if (genomeTracker > genome.length-1) {
					genomeTracker = 0;
				}
			}
			else
			{
				//If symbol is not repeatable remove it 
				gameSymbols.remove(i);
				//System.out.println(currentSymbol.name);
			}
			
			//This section is used to fix variables, without this there were many issues with variables being used without being defined 
			//or being defined and not used, this should ensure that variables declared in level block and used exactly once in sprite block
			//as a result this should produce many more working games
			//LevelBlock size
			if (currentSymbol.name == "charMapNewline" && !levelBlockSizeDetermined) {
				for (int j=0; j < (genome[genomeTracker] % 4) + 1; j++) {
					levelBlockSizeDetermined = true;
					gameSymbols.add(i,charMapNewline);
					variablesUsed++;
				}
				genomeTracker++;
				if (genomeTracker > genome.length-1) {
					genomeTracker = 0;
				}
			}
			//SpriteBlock size
			if (currentSymbol.name == "spriteDefNewline" && !spriteBlockSizeDetermined) {
				for (int j=0; j < variablesUsed; j++) {
					spriteBlockSizeDetermined = true;
					gameSymbols.add(i,spriteDefNewline);
				}
			}
			//InteractionBlock size
			if (currentSymbol.name == "interactionDefEol" && !interactionBlockSizeDetermined) {
				for (int j=0; j < (genome[genomeTracker] % 5) + 3; j++) {
					interactionBlockSizeDetermined = true;
					gameSymbols.add(i,interactionDefEol);
				}
				genomeTracker++;
				if (genomeTracker > genome.length-1) {
					genomeTracker = 0;
				}
			}
			
			//If symbol is an not optional add children otherwise decide if the the option will be used then continue as before
			if(((NonTerminalSymbol)currentSymbol).optional){  
				//OPTIONAL SECTION										---------OPTIONAL SYMBOLS-------
				if (genome[genomeTracker] % 2 == 0) { 
					for (int j=0; j<((NonTerminalSymbol)currentSymbol).children.size(); j++) {
						gameSymbols.add(i, ((NonTerminalSymbol)currentSymbol).children.get(j));
						i++;
					}
				}
				genomeTracker++;
				if (genomeTracker > genome.length-1) {
					genomeTracker = 0;
				}
			}
			else if (((NonTerminalSymbol)currentSymbol).choice == true) {
				//CHOICE SECTION										----------CHOICE SYMBOLS-----------
				if (currentSymbol.name == "levelIdentifier") {
					gameSymbols.add(i, ((NonTerminalSymbol)currentSymbol).children.get(levelVariableTracker));
					levelVariableTracker++;
				}
				else if (currentSymbol.name == "spriteIdentifier")
				{
					gameSymbols.add(i, ((NonTerminalSymbol)currentSymbol).children.get(spriteVariableTracker));
					spriteVariableTracker++;
				}
				else if (currentSymbol.name == "identifier") {
					gameSymbols.add(i, ((NonTerminalSymbol)currentSymbol).children.get(genome[genomeTracker] % variablesUsed));
					genomeTracker++;
					if (genomeTracker > genome.length-1) {
						genomeTracker = 0;
					}
				}
				else if (currentSymbol.name == "parameter") {
					gameSymbols.add(i, ((NonTerminalSymbol)currentSymbol).children.get(genome[genomeTracker] % (variablesUsed + 1)));
					genomeTracker++;
					if (genomeTracker > genome.length-1) {
						genomeTracker = 0;
					}
				}
				else if (currentSymbol.name == "terminationOption") {
					gameSymbols.add(i, ((NonTerminalSymbol)currentSymbol).children.get(terminationTracker));
					terminationTracker++;
				}
				else {
					gameSymbols.add(i, ((NonTerminalSymbol)currentSymbol).children.get(genome[genomeTracker] % (((NonTerminalSymbol)currentSymbol).children.size())));
					genomeTracker++;
					if (genomeTracker > genome.length-1) {
						genomeTracker = 0;
					}
					i++;
				}
			}
			else {
				for (int j=0; j<((NonTerminalSymbol)currentSymbol).children.size(); j++) {
					//System.out.println(((NonTerminalSymbol)currentSymbol).children.get(j).name);
					gameSymbols.add(i, ((NonTerminalSymbol)currentSymbol).children.get(j));
					i++;
				}
			}
		}
		else {
			i++;
		}
	}
	
	for (int j=0; j<gameSymbols.size(); j++) {
			System.out.print(((TerminalSymbol)gameSymbols.get(j)).content);
			writer.print(((TerminalSymbol)gameSymbols.get(j)).content);
	}
	writer.close();
	return gameSymbols;
}

public void writeSymbolsToFile(List<Symbol> gameSymbols)
{
	f.getParentFile().mkdirs();
	try {
	f.createNewFile();
	writer = new PrintWriter(f);
	} catch (IOException e) { 
		e.printStackTrace();
	}
	
	for (int j=0; j<gameSymbols.size(); j++) {
		System.out.print(((TerminalSymbol)gameSymbols.get(j)).content);
		writer.print(((TerminalSymbol)gameSymbols.get(j)).content);
}
	
	writer.close();
}

public void saveSymbolsToFile(List<Symbol> gameSymbols)
{
	s.getParentFile().mkdirs();
	try {
	s.createNewFile();
	saveWriter = new PrintWriter(s);
	} catch (IOException e) { 
		e.printStackTrace();
	}
	
	for (int j=0; j<gameSymbols.size(); j++) {
		System.out.print(((TerminalSymbol)gameSymbols.get(j)).content);
		saveWriter.print(((TerminalSymbol)gameSymbols.get(j)).content);
	}
	saveWriter.close();
	try {
		fos = new FileOutputStream(saveSymbols);
		out = new ObjectOutputStream(fos);
		out.writeObject(gameSymbols);
		out.close();
	}
	catch (Exception ex) {
		ex.printStackTrace();
	}
}
	
public List<Symbol> mutate(List<Symbol> game, double indpb)
{
	int i = 0;
	section currentSection = section.LEVEL;
	while (i < game.size())
	{
		//System.out.println("Begin loop");
		if (i > game.size())
		{
			//System.out.println("loop counter reset");
			i = 0;
		}
		
		Symbol currentSymbol = game.get(i);
		//if currentSymbol.parent != null
		//Should all be terminal symbols but just in case checking that this is the case first
		if (currentSymbol == spriteSet)
		{
			currentSection = section.SPRITE;
		}
		if (currentSymbol instanceof TerminalSymbol && currentSection != section.LEVEL)
		{
			if (((TerminalSymbol) currentSymbol).parent != null)
			{
				//If (mutation probability check) then mutate
				if (rnd.nextDouble() < indpb)
				{
					//if just a parameter/identifier
					if (parameterOrEvaluable(((TerminalSymbol) currentSymbol).parent))
					{
						//choose new sibling 
						game.remove(i);
						game.add(i, (((TerminalSymbol) currentSymbol).parent).children.get(rnd.nextInt(255) % ((TerminalSymbol) currentSymbol).parent.children.size()));
						//System.out.println("Begin Param Expansion");
						game = expandSymbol(i, game);
						//System.out.println("End Param Expansion");
					}
					else
					{
						//remove appropriate symbols (use newline as end point)
						while (game.get(i) != newline)
						{
							game.remove(i);
						}
						//add new sibling symbol and expand
						game.add(i, (((TerminalSymbol) currentSymbol).parent).children.get(rnd.nextInt(255) % ((TerminalSymbol) currentSymbol).parent.children.size()));
						//System.out.println("Begin Line Expansion");
						game = expandSymbol(i, game);
						//System.out.println("End Line Expansion");
					}
				}
			}
		}
		
		i++;
	}
	return game;
}

private List<Symbol> expandSymbol(int i, List<Symbol> game)
{
	//get VariablesUsed
	int variablesUsed = 0;
	int j = 0;
	Symbol currentSymbol = game.get(j);
	//Check the level set to see how many non-avatar sprites are used (so that unused sprites cannot be assigned later)
	while (currentSymbol != spriteSet && containsNonTerminals(game))
	{
		//System.out.println("Counting Variables");
		currentSymbol = game.get(j);
		if (isIdentifier(currentSymbol))
		{
			variablesUsed++;
		}
		j++;
	}
	//System.out.println(variablesUsed);
	while (containsNonTerminals(game))
	{
		if (i > game.size()-1)
		{
			i = 0;
		}
		currentSymbol = game.get(i);
		if (currentSymbol instanceof NonTerminalSymbol) {
			//If symbol represents a repeatable section of the grammar 	------REPEATABLE SYMBOLS-----
			if (((NonTerminalSymbol) currentSymbol).repeatable == true) {
				//System.out.println("Repeater");
				if (rnd.nextInt(255) % 2 == 0) {
					game.remove(i);
				}
			}
			else
			{
				//If symbol is not repeatable remove it 
				game.remove(i);
				//System.out.println("Removing " + currentSymbol.name);
			}
		
			//If symbol is an not optional add children otherwise decide if the the option will be used then continue as before
			if(((NonTerminalSymbol)currentSymbol).optional){ 
				//System.out.println("Optional");
				//OPTIONAL SECTION										---------OPTIONAL SYMBOLS-------
				if (rnd.nextInt(10) < 7) { 
					for (int k=0; k<((NonTerminalSymbol)currentSymbol).children.size(); k++) {
						game.add(i, ((NonTerminalSymbol)currentSymbol).children.get(k));
						i++;
					}
				}

			}
			else if (((NonTerminalSymbol)currentSymbol).choice == true) {
				//System.out.println("Choice");
				//CHOICE SECTION										----------CHOICE SYMBOLS-----------
				if (currentSymbol.name == "identifier") {
					//System.out.println("Variables Used " + variablesUsed);
					game.add(i, ((NonTerminalSymbol)currentSymbol).children.get(rnd.nextInt(variablesUsed)));
				}
				else if (currentSymbol.name == "parameter") {
					game.add(i, ((NonTerminalSymbol)currentSymbol).children.get(rnd.nextInt(variablesUsed + 1)));
				}		
				else {
					game.add(i, ((NonTerminalSymbol)currentSymbol).children.get(rnd.nextInt((((NonTerminalSymbol)currentSymbol).children.size()))));
					i++;
				}
			}
			else {
				//System.out.println("Standard");
				for (int k=0; k<((NonTerminalSymbol)currentSymbol).children.size(); k++) {
					//System.out.println(((NonTerminalSymbol)currentSymbol).children.get(k).name);
					game.add(i, ((NonTerminalSymbol)currentSymbol).children.get(k));
					i++;
				}
			}
		}
		i++;
		//printNames(game);
	}
	return game;
}

private void printNames(List<Symbol> game)
{
	for (int i = 0; i < game.size(); i++) {
		System.out.println("Print " + game.get(i).name);
	}
}

public List<List<Symbol>> onePointCrossover(List<Symbol> a, List<Symbol> b)
{
	List<List<Symbol>> results = new LinkedList<List<Symbol>>();
	boolean split = false;
	int i = 0;
	int lineCounter = 0;
	//Determine rough size of game not including Level block so that crossover points can have a better chance of being anywhere in the game (not including level block)
	int[] aLineCount = gameLineCount(a);
	int[] bLineCount = gameLineCount(b);
	
	int bCrossoverPoint = 0;
	section aCrossoverSection = section.LEVEL;
	section bCrossoverSection = section.LEVEL;
	boolean bCrossoverChosen = false;
	
	List<Symbol> a1 = new LinkedList<Symbol>();
	List<Symbol> a2 = new LinkedList<Symbol>();
	List<Symbol> b1 = new LinkedList<Symbol>();
	List<Symbol> b2 = new LinkedList<Symbol>();
	
	//Pick crossover section and line number
	int sectionInt = rnd.nextInt(3);
	int aCrossoverPoint = 1;
	switch(sectionInt)
	{
		case 0: aCrossoverSection = section.SPRITE;
			aCrossoverPoint = rnd.nextInt(aLineCount[1]);
			break;
		case 1: aCrossoverSection = section.INTERACTION;
			aCrossoverPoint = rnd.nextInt(aLineCount[2]);
			break;
		case 2: aCrossoverSection = section.TERMINATION;
			aCrossoverPoint = rnd.nextInt(2); //Termination section must always be only 2 lines
			break;
		default: System.out.println("Error selecting section");		
			break;
	}
	
	section currentSection = section.LEVEL;
	
	//split and determine section of crossover
	while(!split)
	{
		Symbol currentSymbol = a.get(i);
		
		
		
		if (currentSection == aCrossoverSection)
		{ //Split List
			if (lineCounter == aCrossoverPoint)
			{
				for (int j=0; j<i; j++)
				{
					a1.add(a.get(j));
				}
				for (int j=i; j<a.size(); j++)
				{
					a2.add(a.get(j));
				}
				split = true;
			}
			
			
			if (currentSymbol == newline)
			{
				lineCounter++;
			}
		}
		else
		{
			if (currentSymbol == spriteSet)
			{
				currentSection = section.SPRITE;
			}
			if (currentSymbol == interactionSet)
			{
				currentSection = section.INTERACTION;
			}
			if (currentSymbol  == terminationSet)
			{
				currentSection = section.TERMINATION;
			}
		}
		
		
		
		i++;
	}
	//keep track of which section initial crossover point is in
	//choose crossover point in other game (make sure it is in same block as first)
	
	//Assign bCrossoverPoint
	bCrossoverPoint = aCrossoverPoint;
	switch(aCrossoverSection) {
		case SPRITE: 
			if (bCrossoverPoint + 1 > bLineCount[1])
			{
				bCrossoverPoint = bLineCount[1] - 1;
			}
			break;
		case INTERACTION: 
			if (bCrossoverPoint + 1 > bLineCount[2])
			{
				bCrossoverPoint = bLineCount[2] - 1;
			}
			break;
		case TERMINATION: 
			if (bCrossoverPoint + 1 > bLineCount[3])
			{
				bCrossoverPoint = bLineCount[3] - 1;
			}
			break;
		default:
			break;
	}
	
	split = false;
	i = 0;
	lineCounter = 0;
	while(!split)
	{
		Symbol currentSymbol = b.get(i);
		
		if (bCrossoverSection != aCrossoverSection)
		{
			if (currentSymbol == spriteSet)
			{
				bCrossoverSection = section.SPRITE;
			}
			if (currentSymbol == interactionSet)
			{
				bCrossoverSection = section.INTERACTION;
			}
			if (currentSymbol  == terminationSet)
			{
				bCrossoverSection = section.TERMINATION;
			}
		}
		else
		{			
			if (lineCounter == bCrossoverPoint)
			{
				for (int j=0; j<i; j++)
				{
					b1.add(b.get(j));
				}
				for (int j=i; j<b.size(); j++)
				{
					b2.add(b.get(j));
				}
				split = true;
			}
			
			if (currentSymbol == newline)
			{
				lineCounter++;
			}
			
		}

		i++;
	}
	//cross lists over
	List<Symbol> newA = new LinkedList<Symbol>();
	newA.addAll(a1);
	newA.addAll(b2);
	List<Symbol> newB = new LinkedList<Symbol>();
	newB.addAll(b1);
	newB.addAll(a2);
	
	//return both lists
	results.add(newA);
	results.add(newB);
	return results;
}

public List<Symbol> fixVars(List<Symbol> game)
{
	Symbol currentSymbol = game.get(0);
	int j=0;
	int variablesUsed = 0;
	
	while (currentSymbol != spriteSet)
	{
		//System.out.println("Counting Variables");
		currentSymbol = game.get(j);
		if (isIdentifier(currentSymbol))
		{
			variablesUsed++;
		}
		j++;
	}
	
	while (j < game.size())
	{
		if (isIdentifier(currentSymbol))
		{
			if (Character.getNumericValue(currentSymbol.name.charAt(3)) > variablesUsed) //Not the most foolproof thing to use the 4th character evertime but name is always varX where x is 1-5 (9 vars would be too many so no need to worry about 2 digits)
			{
				game.set(j, identifier.children.get(rnd.nextInt(variablesUsed)));
			}
		}
	}
	
	return game;
}

private int[] gameLineCount(List<Symbol> game)
{
	section currentSection = section.LEVEL;
	int[] result = new int[4];
	for (int i=0; i < 4; i++){
		result[i] = 0;
	}
	for (int i=0; i < game.size(); i++)
	{
		Symbol currentSymbol = game.get(i);	
		
		if (currentSymbol == spriteSet)
		{
			currentSection = section.SPRITE;
		}
		if (currentSymbol == interactionSet)
		{
			currentSection = section.INTERACTION;
		}
		if (currentSymbol == terminationSet)
		{
			currentSection = section.TERMINATION;
		}
		
		switch(currentSection) {
		case LEVEL: if (currentSymbol == newline)
		{
			result[0]++;
		}
			break;
		case SPRITE: if (currentSymbol == newline)
		{
			result[1]++;
		}
			break;
		case INTERACTION: if (currentSymbol == newline)
		{
			result[2]++;
		}
			break;
		case TERMINATION: if (currentSymbol == newline)
		{
			result[3]++;
		} 
			break;		
		}
		
	}
	return result;
}

private boolean parameterOrEvaluable(Symbol symbol)
{
	if (symbol == levelIdentifier || symbol == spriteIdentifier || symbol == parameter || symbol == identifier || symbol == evaluableBoolean || symbol == evaluableFloat || symbol == evaluableInt || symbol == evaluableDirection || symbol == evaluableScoreInt || symbol == evaluableLargeInt)
	{
		return true;
	}
	else
	{
		return false;
	}
}

private boolean isIdentifier(Symbol symbol)
{
	if (symbol.name == "var1" || symbol.name == "var2" || symbol.name == "var3" || symbol.name == "var4" || symbol.name == "var5")
	{
		return true;
	}
	else 
	{
		return false;
	}
}

private boolean isParameter(Symbol symbol)
{
	if (symbol.name == "avatar" || isIdentifier(symbol))
	{
		return true;
	}
	else 
	{
		return false;
	}
}

	private boolean containsNonTerminals(List<Symbol> list) {
		for (int i=0; i<list.size(); i++) {
			if (list.get(i) instanceof NonTerminalSymbol) {
				return true;
			}
		}
		return false;
	}

}
