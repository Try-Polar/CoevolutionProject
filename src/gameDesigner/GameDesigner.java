package gameDesigner;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class GameDesigner {
	
	String path ="C:" + File.separator + "Users" + File.separator + "Elliot" + File.separator + "Documents" + File.separator + "GitHub" + File.separator + "CoevolutionProject" + File.separator + "examples" + File.separator + "gridphysics" + File.separator + "earlyAttempts.txt";
	//C:\Users\Elliot\Documents\GitHub\CoevolutionProject\examples\gridphysics
	File f = new File(path);
	PrintWriter writer;
	
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
	NonTerminalSymbol interactionDefEol = new NonTerminalSymbol("interactionDefEol", true, false, false);
	NonTerminalSymbol terminationDefEol = new NonTerminalSymbol("terminationDefEol", true, false, false);
	NonTerminalSymbol spaceSpriteType = new NonTerminalSymbol("spaceSpriteType", true, false, false);
	NonTerminalSymbol spriteDefEol = new NonTerminalSymbol("spriteDefEol", true, false, false);
	NonTerminalSymbol spaceOption = new NonTerminalSymbol("spaceOption", true, false, false);
	NonTerminalSymbol spaceRepeat = new NonTerminalSymbol("spaceRepeat", true, false, false);
	NonTerminalSymbol charOrSpaceRepeat = new NonTerminalSymbol("charOrSpaceRepeat", true, false, false);
	//Optional symbols	
	NonTerminalSymbol spriteDefOptionalBlock = new NonTerminalSymbol("spriteDefOptional", false, true, false);
	NonTerminalSymbol spriteSimpleOptionalBlock = new NonTerminalSymbol("spriteSimpleOptional", false, true, false);
	NonTerminalSymbol eolOptionalBlock = new NonTerminalSymbol("eolOptional", false, true, false);
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
	
	
	//Declare Terminals	
	TerminalSymbol newline = new TerminalSymbol("newline", "\n");
	TerminalSymbol indent = new TerminalSymbol("indent","	");
	TerminalSymbol lambda = new TerminalSymbol("lambda","LAMBDA");
	TerminalSymbol greaterThan = new TerminalSymbol("greater than"," > ");
	TerminalSymbol hash = new TerminalSymbol("hash", "#");
	TerminalSymbol space = new TerminalSymbol("space", " ");
	TerminalSymbol equals = new TerminalSymbol("equals", "=");
	//strings
	TerminalSymbol levelMapping = new TerminalSymbol("levelMapping","LevelMapping");
	TerminalSymbol avatar = new TerminalSymbol("avatar","avatar");
	TerminalSymbol wall = new TerminalSymbol("wall","wall");
	TerminalSymbol eos = new TerminalSymbol("eos", "EOS");
	TerminalSymbol spriteSet = new TerminalSymbol("spriteSet","SpriteSet");
	TerminalSymbol interactionSet = new TerminalSymbol("interactionSet","InteractionSet");
	TerminalSymbol terminationSet = new TerminalSymbol("terminationSet","TerminationSet");
	
	
	//Avatar Sprite Classes
	//Terminals
	TerminalSymbol movingAvatar = new TerminalSymbol("MovingAvatar", "MovingAvatar");
	TerminalSymbol horizontalAvatar = new TerminalSymbol("HorizontalAvatar", "HorizontalAvatar");
	TerminalSymbol verticalAvatar = new TerminalSymbol("VerticalAvatar", "VerticalAvatar");
	TerminalSymbol ongoingAvatar = new TerminalSymbol("OngoingAvatar", "OngoingAvatar");
	TerminalSymbol ongoingTurningAvatar = new TerminalSymbol("OngoingTurningAvatar", "OngoingTurningAvatar");
	TerminalSymbol ongoingShootAvatar = new TerminalSymbol("OngoingShootAvatar", "OngoingShootAvatar");
	TerminalSymbol missileAvatar = new TerminalSymbol("MissileAvatar", "MissileAvatar");
	TerminalSymbol orientedAvatar = new TerminalSymbol("OrientedAvatar", "OrientedAvatar");
	//Non Terminals
	NonTerminalSymbol shootAvatar = new NonTerminalSymbol("ShootAvatar", false, false, false);
	NonTerminalSymbol flakAvatar = new NonTerminalSymbol("FlakAvatar", false, false, false);
	//avatar strings
	TerminalSymbol shootAvatarString = new TerminalSymbol("ShootAvatar", "ShootAvatar ");
	TerminalSymbol flakAvatarString = new TerminalSymbol("FlakAvatar", "FlakAvatar ");
	//avatar parameters
	//TerminalSymbol ammo = new TerminalSymbol("Ammo", "ammo="); //For now not going to give this to the GA to try and figure out
	//TerminalSymbol minAmmo = new TerminalSymbol("minAmmo", "minAmmo="); //commented same as ammo
	TerminalSymbol stype = new TerminalSymbol("stype", "stype="); 
	//Other Sprite Classes
	//Terminals
	TerminalSymbol immovable = new TerminalSymbol("Immovable", "Immovable");
	TerminalSymbol passive = new TerminalSymbol("Passive", "Passive");
	TerminalSymbol missile = new TerminalSymbol("Missile", "Missile");
	TerminalSymbol randomMissile = new TerminalSymbol("RandomMissile", "RandomMissile");
	TerminalSymbol randomNPC = new TerminalSymbol("RandomNPC", "RandomNPC");
	TerminalSymbol door = new TerminalSymbol("Door", "Door");
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
	TerminalSymbol flickerString = new TerminalSymbol("flicker", "Flicker ");
	TerminalSymbol orientedFlickerString = new TerminalSymbol("orientedFlicker","OrientedFlicker ");
	TerminalSymbol chaserString = new TerminalSymbol("chaser", "Chaser ");
	TerminalSymbol fleeingString = new TerminalSymbol("fleeing", "Fleeing");
	TerminalSymbol alternateChaserString = new TerminalSymbol("alternateChaser", "AlternateChaser ");
	TerminalSymbol randomAltChaserString = new TerminalSymbol("randomAltChaser", "RandomAltChaser ");
	TerminalSymbol spawnPointString = new TerminalSymbol("spawnPoint", "SpawnPoint ");
	TerminalSymbol bomberString	= new TerminalSymbol("bomber", "Bomber ");
	TerminalSymbol randomBomberString = new TerminalSymbol("randomBomberString", "RandomBomberString ");
	TerminalSymbol bomberRandomMissileString = new TerminalSymbol("bomberRandomMissile", "BomberRandomMissile ");
	TerminalSymbol spreaderString = new TerminalSymbol("spreader", "Spreader ");
	TerminalSymbol portalString = new TerminalSymbol("portal", "Portal ");
	TerminalSymbol resourceString = new TerminalSymbol("resource", "Resource ");
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
	//Terminals
	TerminalSymbol killSprite = new TerminalSymbol("killSprite", "killSprite");
	TerminalSymbol killIfFromAbove = new TerminalSymbol("killIfFromAbove", "killIfFromAbove");
	TerminalSymbol cloneSprite = new TerminalSymbol("cloneSprite", "cloneSprite");
	TerminalSymbol stepBack = new TerminalSymbol("stepBack", "stepBack");
	TerminalSymbol undoAll = new TerminalSymbol("undoAll", "undoAll");
	TerminalSymbol flipDirection = new TerminalSymbol("flipDirection", "flipDirection");
	TerminalSymbol reverseDirection = new TerminalSymbol("reverseDirection", "reverseDirection");
	TerminalSymbol attractGaze = new TerminalSymbol("attractGaze", "attractGaze");
	TerminalSymbol align = new TerminalSymbol("align","algin");
	TerminalSymbol turnAround = new TerminalSymbol("turnAround", "turnAround");
	TerminalSymbol wrapAround = new TerminalSymbol("wrapAround", "wrapAround");
	TerminalSymbol teleportToExit = new TerminalSymbol("teleportToExit", "teleportToExit");
	TerminalSymbol pullWithIt = new TerminalSymbol("pullWithIt", "pullWithIt");
	TerminalSymbol bounceForward = new TerminalSymbol("bounceForward", "bounceForward");
	TerminalSymbol collectResource = new TerminalSymbol("collectResource", "collectResource");
	//NonTerminals
	NonTerminalSymbol killAll = new NonTerminalSymbol("killAll", false, false, false);
	NonTerminalSymbol killIfHasMore = new NonTerminalSymbol("killIfHasMore", false, false, false);
	NonTerminalSymbol killIfHasLess = new NonTerminalSymbol("killIfHasLess", false, false, false);
	NonTerminalSymbol killIfOtherHasMore = new NonTerminalSymbol("killIfOtherHasMore", false, false, false);
	NonTerminalSymbol transformToSingleton = new NonTerminalSymbol("transformToSingleton", false, false, false);
	NonTerminalSymbol spawnBehind = new NonTerminalSymbol("spawnBehind", false, false, false);
	NonTerminalSymbol spawnIfHasMore = new NonTerminalSymbol("spawnIfHasMore", false, false, false);
	NonTerminalSymbol spawnIfHasLess = new NonTerminalSymbol("spawnIfHasLess", false, false, false);
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
	NonTerminalSymbol changeResource = new NonTerminalSymbol("changeResource",false,false,false);
	//Interaction method strings
	TerminalSymbol killAllString = new TerminalSymbol("killAll","killAll ");
	TerminalSymbol killIfHasMoreString = new TerminalSymbol("killIfHasMore", "killIfHasMore ");
	TerminalSymbol killIfHasLessString = new TerminalSymbol("killIfHasLess", "killIfHasLess ");
	TerminalSymbol killIfOtherHasMoreString = new TerminalSymbol("killIfOtherHasMore", "killIfOtherHasMore ");
	TerminalSymbol transformToSingletonString = new TerminalSymbol("transformToSingleton", "transformToSingleton ");
	TerminalSymbol spawnBehindString = new TerminalSymbol("spawnBehind", "spawnBehind ");
	TerminalSymbol spawnIfHasMoreString = new TerminalSymbol("spawIfHasMore", "spawnIfHasMore ");
	TerminalSymbol spawnIfHasLessString = new TerminalSymbol("spawnIfHasLess", "spawnIfHasLess ");
	TerminalSymbol transformToString = new TerminalSymbol("transformTo", "transformTo ");
	TerminalSymbol transformIfCountsString = new TerminalSymbol("transformIfCounts", "transformIfCounts ");
	TerminalSymbol transformToRandomChildString = new TerminalSymbol("transformToRandomChild", "transformToRandomChild ");
	TerminalSymbol updateSpawnTypeString = new TerminalSymbol("updateSpawnType", "updateSpawnType ");
	TerminalSymbol removeScoreString = new TerminalSymbol("removeScore", "removeScore ");
	TerminalSymbol addHealthPointsString = new TerminalSymbol("addHealthPoints","addHealthPoints ");
	TerminalSymbol addHealthPointsToMaxString = new TerminalSymbol("addHealthPointsToMax", "addHealthPointsToMax ");
	TerminalSymbol subtractHealthPointsString = new TerminalSymbol("subtractHealthPoints", "subtractHealthPoints ");
	TerminalSymbol increaseSpeedToAllString = new TerminalSymbol("increaseSpeedToAll", "increaseSpeedToAll ");
	TerminalSymbol decreaseSpeedToAllString = new TerminalSymbol("decreaseSpeedToAll", "decreaseSpeedToAll ");
	TerminalSymbol setSpeedForAllString = new TerminalSymbol("setSpeedForAll", "setSpeedForAll ");
	TerminalSymbol changeResourceString = new TerminalSymbol("changeResource", "changeResource ");
	//Interaction method paramters
	TerminalSymbol stype_other = new TerminalSymbol("stype_other","stype_other=");
	TerminalSymbol forceOrientation = new TerminalSymbol("forceOrientation", "forceOrientation=");
	TerminalSymbol stypeCount = new TerminalSymbol("stypeCount", "stypeCount=");
	TerminalSymbol geq = new TerminalSymbol("geq","geq=");
	TerminalSymbol leq = new TerminalSymbol("leq","leq=");
	TerminalSymbol spawnPointParam = new TerminalSymbol("spawnPoint", "spawnPoint=");
	//Termination Classes
	//NonTerminals
	NonTerminalSymbol spriteCounter = new NonTerminalSymbol("SpriteCounter",false,false,false);
	NonTerminalSymbol spriteCounterMore = new NonTerminalSymbol("SpriteCounterMore",false,false,false);
	NonTerminalSymbol multiSpriteCounter = new NonTerminalSymbol("MultiSpriteCounter",false,false,false);
	NonTerminalSymbol multiSpriteCounterSubTypes = new NonTerminalSymbol("MultiSpriteCounterSubTypes",false,false,false);
	NonTerminalSymbol stopCounter = new NonTerminalSymbol("StopCounter",false,false,false);
	NonTerminalSymbol timeout = new NonTerminalSymbol("Timeout",false,false,false);
	//Termination class strings
	TerminalSymbol spriteCounterString = new TerminalSymbol("spriteCounter", "SpriteCounter ");
	TerminalSymbol spriteCounterMoreString = new TerminalSymbol("spriteCounterMore", "SpriteCounterMore ");
	TerminalSymbol multiSpriteCounterString = new TerminalSymbol("multiSpriteCounter", "MultiSpriteCounter ");
	TerminalSymbol multiSpriteCounterSubTypesString = new TerminalSymbol("multiSpriteCounterSubTypes", "MultiSpriteCounterSubTypes ");
	TerminalSymbol stopCounterString = new TerminalSymbol("stopCounter", "StopCounter ");
	TerminalSymbol timeOutString = new TerminalSymbol("timeout", "Timeout ");
	//Termination class params
	TerminalSymbol stype3 = new TerminalSymbol("stype3", "stype3=");
	
	
	String[] gameClasses = {"BasicGame"};
	String[] identifiers = {"var1", "var2", "var3", "var4", "var5"};
	String[] parameters = {"avatar", "var1", "var2", "var3", "var4", "var5"};
	String[] chars = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
	String[] evaluableBooleans = {"True", "False"};
	String[] evaluableFloats = {"0.1","0.2","0.3","0.4","0.5","0.6","0.7","0.8","0.9"}; //make for loop to expand
	String[] evaluableInts = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20"}; //make for loop to expand
	String[] terminationOptions = {"win=True", "win=False"};
	String[] evaluableDirections = {"UP", "DOWN", "LEFT", "RIGHT"};
	
	//ClassSymbol //could use NonTerminal choice symbols here but this is slightly neater/uses less lines of code (not sure if its worth it yet)
		InterchangableSymbol game_class = new InterchangableSymbol("game_class", gameClasses);

		InterchangableSymbol identifier = new InterchangableSymbol("identifier", identifiers);
		InterchangableSymbol parameter = new InterchangableSymbol("parameter", identifiers); //basically the same thing named differently so that parameters are treated differently to identifiers
		InterchangableSymbol charVar = new InterchangableSymbol("char", chars);
		InterchangableSymbol evaluableBoolean = new InterchangableSymbol("evaluable", evaluableBooleans);
		InterchangableSymbol evaluableFloat = new InterchangableSymbol("evaluable", evaluableFloats);
		InterchangableSymbol evaluableInt = new InterchangableSymbol("evaluable", evaluableInts);
		InterchangableSymbol terminationOption = new InterchangableSymbol("terminationOption", terminationOptions);
		InterchangableSymbol evaluableDirection = new InterchangableSymbol("evaluableDirection", evaluableDirections);
	
	List<Symbol> gameSymbols = new LinkedList<Symbol>();
	
	Random rnd = new Random();
	
	public GameDesigner() {
		initiliseVGDLSymbols();
		
		System.out.println("delete");
		
		f.getParentFile().mkdirs();
		try {
		f.createNewFile();
		writer = new PrintWriter(f);
		} catch (IOException e) { 
			e.printStackTrace();
		}
	}
	
	private void initiliseVGDLSymbols() { //May remove this entirely later, it was useful earlier until I changed things
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
				//char-map
				charMap.addChild(charVar);
				charMap.addChild(greaterThan);
				charMap.addChild(spriteTypeMinusAvatar);
				//charMap.addChild(spaceSpriteType);
				//avatarMap
				avatarMap.addChild(charVar);
				avatarMap.addChild(greaterThan);
				avatarMap.addChild(avatar);
				//sprite-def
				spriteDef.addChild(spriteSimple);
				//spriteDef.addChild(spriteDefOptionalBlock);
				//sprite-simple
				spriteSimple.addChild(spriteTypeMinusAvatar);
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
				spriteDefOptionalBlock.addChild(eol);
				spriteDefOptionalBlock.addChild(indent);
				spriteDefOptionalBlock.addChild(spriteDefEol);
				//spriteSimpleOptionalBlock
				spriteSimpleOptionalBlock.addChild(sprite_class);
				//eolOptionalBlock
				eolOptionalBlock.addChild(hash);
				eolOptionalBlock.addChild(charOrSpaceRepeat);
				
				//Choices
				//spriteType
				spriteType.addChild(identifier);
				spriteType.addChild(avatar);
				spriteType.addChild(wall);
				spriteType.addChild(eos);
				//spriteTypeMinusAvatar
				spriteTypeMinusAvatar.addChild(identifier);
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
				//sprite_class
				avatar_class.addChild(movingAvatar);
				avatar_class.addChild(horizontalAvatar);
				avatar_class.addChild(verticalAvatar);
				avatar_class.addChild(ongoingAvatar);
				avatar_class.addChild(ongoingTurningAvatar);
				avatar_class.addChild(ongoingShootAvatar);
				avatar_class.addChild(missileAvatar);
				avatar_class.addChild(orientedAvatar);
				avatar_class.addChild(shootAvatar);
				avatar_class.addChild(flakAvatar);
				//Potentially split avatar sprite classes from other sprite classes
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
				sprite_class.addChild(resource);
				//interaction_method
				interaction_method.addChild(killSprite);
				interaction_method.addChild(killAll);
				interaction_method.addChild(killIfHasMore);
				interaction_method.addChild(killIfHasLess);
				interaction_method.addChild(killIfFromAbove);
				interaction_method.addChild(killIfOtherHasMore);
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
				interaction_method.addChild(collectResource);
				interaction_method.addChild(changeResource);
				//termination__class
				termination_class.addChild(spriteCounter);
				termination_class.addChild(spriteCounterMore);
				termination_class.addChild(multiSpriteCounter);
				termination_class.addChild(multiSpriteCounterSubTypes);
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
				killAll.addChild(killAllString);
				killAll.addChild(stype);
				killAll.addChild(parameter);
				killIfHasMore.addChild(killIfHasMoreString);
				killIfHasMore.addChild(resourceName);
				killIfHasMore.addChild(parameter);
				killIfHasMore.addChild(space);
				killIfHasMore.addChild(limit);
				killIfHasMore.addChild(evaluableInt);
				killIfHasLess.addChild(killIfHasLessString);
				killIfHasLess.addChild(resourceName);
				killIfHasLess.addChild(parameter);
				killIfHasLess.addChild(space);
				killIfHasLess.addChild(limit);
				killIfHasLess.addChild(evaluableInt);
				killIfOtherHasMore.addChild(killIfOtherHasMoreString);
				killIfOtherHasMore.addChild(resourceName);
				killIfOtherHasMore.addChild(parameter);
				killIfOtherHasMore.addChild(space);
				killIfOtherHasMore.addChild(limit);
				killIfOtherHasMore.addChild(evaluableInt);
				transformToSingleton.addChild(transformToRandomChildString);
				transformToSingleton.addChild(stype);
				transformToSingleton.addChild(parameter);
				transformToSingleton.addChild(space);
				transformToSingleton.addChild(stype_other);
				transformToSingleton.addChild(parameter);
				spawnBehind.addChild(spawnBehindString);
				spawnBehind.addChild(stype);
				spawnBehind.addChild(parameter);
				spawnIfHasMore.addChild(spawnIfHasMoreString);
				spawnIfHasMore.addChild(stype);
				spawnIfHasMore.addChild(parameter);
				spawnIfHasMore.addChild(space);
				spawnIfHasMore.addChild(resourceName);
				spawnIfHasMore.addChild(parameter);
				spawnIfHasMore.addChild(space);
				spawnIfHasMore.addChild(limit);
				spawnIfHasMore.addChild(evaluableInt);
				spawnIfHasLess.addChild(spawnIfHasLessString);
				spawnIfHasLess.addChild(stype);
				spawnIfHasLess.addChild(parameter);
				spawnIfHasLess.addChild(space);
				spawnIfHasLess.addChild(resourceName);
				spawnIfHasLess.addChild(parameter);
				spawnIfHasLess.addChild(space);
				spawnIfHasLess.addChild(limit);
				spawnIfHasLess.addChild(evaluableInt);
				transformTo.addChild(transformToString);
				transformTo.addChild(stype);
				transformTo.addChild(parameter);
				transformTo.addChild(space);
				transformTo.addChild(forceOrientation);
				transformTo.addChild(evaluableDirection);
				transformIfCounts.addChild(transformIfCountsString);
				transformIfCounts.addChild(stype);
				transformIfCounts.addChild(parameter);
				transformIfCounts.addChild(space);
				transformIfCounts.addChild(stypeCount);
				transformIfCounts.addChild(parameter);
				transformIfCounts.addChild(space);
				transformIfCounts.addChild(geq);
				transformIfCounts.addChild(evaluableInt);
				transformIfCounts.addChild(space);
				transformIfCounts.addChild(leq);
				transformIfCounts.addChild(evaluableInt);
				transformToRandomChild.addChild(transformToRandomChildString);
				transformToRandomChild.addChild(stype);
				transformToRandomChild.addChild(parameter);
				updateSpawnType.addChild(updateSpawnTypeString);
				updateSpawnType.addChild(stype);
				updateSpawnType.addChild(parameter);
				updateSpawnType.addChild(space);
				updateSpawnType.addChild(spawnPointParam);
				updateSpawnType.addChild(parameter);
				removeScore.addChild(removeScoreString);
				removeScore.addChild(stype);
				removeScore.addChild(parameter);
				addHealthPoints.addChild(addHealthPointsString);
				addHealthPoints.addChild(value);
				addHealthPoints.addChild(evaluableInt);
				addHealthPointsToMax.addChild(addHealthPointsToMaxString);
				addHealthPointsToMax.addChild(value);
				addHealthPointsToMax.addChild(evaluableInt);
				subtractHealthPoints.addChild(subtractHealthPointsString);
				subtractHealthPoints.addChild(value);
				subtractHealthPoints.addChild(evaluableInt);
				subtractHealthPoints.addChild(space);
				subtractHealthPoints.addChild(stype);
				subtractHealthPoints.addChild(parameter);
				increaseSpeedToAll.addChild(increaseSpeedToAllString);
				increaseSpeedToAll.addChild(value);
				increaseSpeedToAll.addChild(evaluableInt);
				increaseSpeedToAll.addChild(space);
				increaseSpeedToAll.addChild(stype);
				increaseSpeedToAll.addChild(parameter);
				decreaseSpeedToAll.addChild(decreaseSpeedToAllString);
				decreaseSpeedToAll.addChild(value);
				decreaseSpeedToAll.addChild(evaluableInt);
				decreaseSpeedToAll.addChild(stype);
				decreaseSpeedToAll.addChild(parameter);
				setSpeedForAll.addChild(setSpeedForAllString);
				setSpeedForAll.addChild(value);
				setSpeedForAll.addChild(evaluableInt);
				setSpeedForAll.addChild(space);
				setSpeedForAll.addChild(stype);
				setSpeedForAll.addChild(parameter);
				changeResource.addChild(changeResourceString);
				changeResource.addChild(resourceName);
				changeResource.addChild(parameter);
				changeResource.addChild(space);
				changeResource.addChild(value);
				changeResource.addChild(evaluableInt);
				//termination classes
				spriteCounter.addChild(spriteCounterString);
				spriteCounter.addChild(stype);
				spriteCounter.addChild(parameter);
				spriteCounter.addChild(space);
				spriteCounter.addChild(limit);
				spriteCounter.addChild(evaluableInt);
				spriteCounterMore.addChild(spriteCounterMoreString);
				spriteCounterMore.addChild(stype);
				spriteCounterMore.addChild(parameter);
				spriteCounterMore.addChild(space);
				spriteCounterMore.addChild(limit);
				spriteCounterMore.addChild(evaluableInt);
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
				timeout.addChild(timeOutString);
				timeout.addChild(limit);
				timeout.addChild(evaluableInt);
	}
	
	public void createGame() {
		
		int i = 0;
		gameSymbols.add(game);

		//While there are still symbols that need to be expanded
		while (containsNonTerminals()) {
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
	
public void createGameFromGenome(int[] genome) {
		
		System.out.println("creating game from genome");
	

		
		int i = 0;
		gameSymbols.add(game);
		int genomeTracker = 0;
		int variablesUsed = 0;
		boolean levelBlockSizeDetermined = false;
		boolean spriteBlockSizeDetermined = false;

		//While there are still symbols that need to be expanded
		while (containsNonTerminals()) {
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
				
				//If symbol is an not optional add children otherwise decide if the the option will be used then continue as before
				if(((NonTerminalSymbol)currentSymbol).optional == false){
					//CHOICE SECTION										----------CHOICE SYMBOLS-----------
					if (((NonTerminalSymbol)currentSymbol).choice == true) {
						gameSymbols.add(i, ((NonTerminalSymbol)currentSymbol).children.get(genome[genomeTracker] % (((NonTerminalSymbol)currentSymbol).children.size())));
						genomeTracker++;
						if (genomeTracker > genome.length-1) {
							genomeTracker = 0;
						}
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
			}
			else {
				i++;
			}
		}
		int variableTracker = 0;
		boolean inLevelBlock = false;
		boolean inSpriteBlock = false;
		
		for (int j=0; j<gameSymbols.size(); j++) {	
			if (gameSymbols.get(j) instanceof InterchangableSymbol) { 	//	--------INTERCHANGABLE SYMBOLS-----------
				if ((inLevelBlock) && (((InterchangableSymbol)gameSymbols.get(j)).name == "identifier")) {
					
					System.out.print(((InterchangableSymbol)gameSymbols.get(j)).classStrings[variableTracker]);
					writer.print(((InterchangableSymbol)gameSymbols.get(j)).classStrings[variableTracker]);
					variableTracker++;
				} 
				else if ((inSpriteBlock) && (((InterchangableSymbol)gameSymbols.get(j)).name == "identifier")) {
					
					System.out.print(((InterchangableSymbol)gameSymbols.get(j)).classStrings[variableTracker]);
					writer.print(((InterchangableSymbol)gameSymbols.get(j)).classStrings[variableTracker]);
					variableTracker++;
				}
				else if ((((InterchangableSymbol)gameSymbols.get(j)).name == "identifier") || (((InterchangableSymbol)gameSymbols.get(j)).name == "parameter") ) {
					writer.print(((InterchangableSymbol)gameSymbols.get(j)).classStrings[genome[genomeTracker] % variablesUsed]);
				}
				else {	
					
					System.out.print(((InterchangableSymbol)gameSymbols.get(j)).classStrings[genome[genomeTracker] % ((InterchangableSymbol)gameSymbols.get(j)).classStrings.length]);
					writer.print(((InterchangableSymbol)gameSymbols.get(j)).classStrings[genome[genomeTracker] % ((InterchangableSymbol)gameSymbols.get(j)).classStrings.length]);
					genomeTracker++;
					if (genomeTracker > genome.length-1) {
						genomeTracker = 0;
					}
				}
			}
			else {
				
				System.out.print(((TerminalSymbol)gameSymbols.get(j)).content);
				
				writer.print(((TerminalSymbol)gameSymbols.get(j)).content);
				if (((TerminalSymbol)gameSymbols.get(j)).name == "levelMapping") {
					inLevelBlock = true;
				}
				if (((TerminalSymbol)gameSymbols.get(j)).name == "spriteSet") {
					variableTracker = 0;
					inLevelBlock = false;
					inSpriteBlock = true;
				}
				if (((TerminalSymbol)gameSymbols.get(j)).name == "interactionSet") {
					inLevelBlock = false;
					inSpriteBlock = false;
				}
			}
		}
		System.out.println("creating game from genome");
		writer.close();
}
	
	private boolean containsNonTerminals() {
		boolean result = false; 
		for (int i=0; i<gameSymbols.size(); i++) {
			if (gameSymbols.get(i) instanceof NonTerminalSymbol) {
				result = true;
				break;
			}
		}
		return result;
	}

}
