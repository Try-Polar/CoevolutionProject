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
	NonTerminalSymbol spriteDef = new NonTerminalSymbol("sprieDef", false, false, false);
	NonTerminalSymbol interactionDef = new NonTerminalSymbol("interactionDef", false, false, false);
	NonTerminalSymbol terminationDef = new NonTerminalSymbol("terminationdef", false, false, false);
	NonTerminalSymbol spriteSimple = new NonTerminalSymbol("spriteSimple", false, false, false);
	NonTerminalSymbol option = new NonTerminalSymbol("option", false, false, false);
	NonTerminalSymbol eol = new NonTerminalSymbol("eol", false, false, false);
	//Repitition symbols
	NonTerminalSymbol charMapNewline = new NonTerminalSymbol("charMapNewline", true, false, false);
	NonTerminalSymbol spriteDefNewline = new NonTerminalSymbol("spriteDefNewline", true, false, false);
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
	NonTerminalSymbol evaluable = new NonTerminalSymbol("evaluable", false, false, true);
	//NonTerminalSymbol game_class; //probably not needed for the type of games we are making, also can't find a clear list of what this would contain
	NonTerminalSymbol sprite_class = new NonTerminalSymbol("sprite_class", false, false, true);
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
	TerminalSymbol missileAvatar = new TerminalSymbol("MissileAvatar", "MissileAvatar");
	TerminalSymbol orientatedAvatar = new TerminalSymbol("OrientatedAvatar", "OrientatedAvatar");
	//Non Terminals
	NonTerminalSymbol shootAvatar = new NonTerminalSymbol("ShootAvatar", false, false, false);
	NonTerminalSymbol flakAvatar = new NonTerminalSymbol("FlakAvatar", false, false, false);
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
	NonTerminalSymbol orientatedFlicker = new NonTerminalSymbol("OrientatedFlick", false, false, false);
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
	//Interaction methods
	//Terminals
	TerminalSymbol killSprite = new TerminalSymbol("killSprite", "killSprite");
	TerminalSymbol killIfFromAbove = new TerminalSymbol("killIfFromAbove", "killIfFromAbove");
	TerminalSymbol cloneSprite = new TerminalSymbol("cloneSprite", "cloneSprite");
	TerminalSymbol stepBack = new TerminalSymbol("stepBack", "stepBack");
	TerminalSymbol undoAll = new TerminalSymbol("undoAll", "undoAll");
	TerminalSymbol flipDirection = new TerminalSymbol("flipDirection", "flipDirection");
	TerminalSymbol reverseDirection = new TerminalSymbol("reverseDirection", "reverseDirection");
	TerminalSymbol attactGaze = new TerminalSymbol("attractGaze", "attractGaze");
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
	NonTerminalSymbol upateSpawnType = new NonTerminalSymbol("updateSpawnType", false, false, false);
	NonTerminalSymbol removeScore = new NonTerminalSymbol("removeScore", false, false,false);
	NonTerminalSymbol addHealthPoints = new NonTerminalSymbol("addHealthPoints", false, false, false);
	NonTerminalSymbol addHealthPointsToMax = new NonTerminalSymbol("addHealthPointToMax",false,false,false);
	NonTerminalSymbol subtractHealthPoints = new NonTerminalSymbol("subtracHealthPoints",false,false,false);
	NonTerminalSymbol increaseSpeedToAll = new NonTerminalSymbol("increaseSpeedToAll",false,false,false);
	NonTerminalSymbol decreaseSpeedToAll = new NonTerminalSymbol("decreaseSpeedToAll",false,false,false);
	NonTerminalSymbol setSpeedForAll = new NonTerminalSymbol("setSpeedForAll",false,false,false);
	NonTerminalSymbol changeResource = new NonTerminalSymbol("changeResource",false,false,false);
	//Termination Classes
	//NonTerminals
	NonTerminalSymbol spriteCounter = new NonTerminalSymbol("SpriteCounter",false,false,false);
	NonTerminalSymbol spriteCounterMore = new NonTerminalSymbol("SpriteCounterMore",false,false,false);
	NonTerminalSymbol multiSpriteCounter = new NonTerminalSymbol("MultiSpriteCounter",false,false,false);
	NonTerminalSymbol multiSpriteCounterSubTypes = new NonTerminalSymbol("MultiSpriteCounterSubTypes",false,false,false);
	NonTerminalSymbol stopCounter = new NonTerminalSymbol("StopCounter",false,false,false);
	NonTerminalSymbol timeOut = new NonTerminalSymbol("TimeOut",false,false,false);
	
	
	
	String[] gameClasses = {"BasicGame"};
	String[] identifiers = {"var1", "var2", "var3", "var4", "var5", "avatar"};
	String[] chars = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
	String[] evaluableBooleans = {"True", "False"};
	String[] evaluableFloats = {"0.1","0.2","0.3","0.4","0.5","0.6","0.7","0.8","0.9"};
	String[] evaluableInts = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20"};
	String[] terminationOptions = {"win=True", "win=False"};
	
	//ClassSymbol //could use NonTerminal choice symbols here but this is slightly neater/uses less lines of code (not sure if its worth it yet)
		InterchangableSymbol game_class = new InterchangableSymbol("game_class", gameClasses);

		InterchangableSymbol identifier = new InterchangableSymbol("identifier", identifiers);
		InterchangableSymbol charVar = new InterchangableSymbol("char", chars);
		InterchangableSymbol evaluableBoolean = new InterchangableSymbol("evaluable", evaluableBooleans);
		InterchangableSymbol evaluableFloat = new InterchangableSymbol("evaluable", evaluableFloats);
		InterchangableSymbol evaluableInt = new InterchangableSymbol("evaluable", evaluableInts);
		InterchangableSymbol terminationOption = new InterchangableSymbol("terminationOption", terminationOptions);
	
	List<Symbol> gameSymbols = new LinkedList<Symbol>();
	
	Random rnd = new Random();
	
	public GameDesigner() {
		initiliseVGDLSymbols();
		
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
				levelBlock.addChild(charMapNewline);
				//sprite-block
				spriteBlock.addChild(indent);
				spriteBlock.addChild(spriteSet);
				spriteBlock.addChild(eol);
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
				charMap.addChild(spriteType);
				charMap.addChild(spaceSpriteType);
				//sprite-def
				spriteDef.addChild(spriteSimple);
				spriteDef.addChild(spriteDefOptionalBlock);
				//sprite-simple
				spriteSimple.addChild(spriteType);
				spriteSimple.addChild(greaterThan);
				spriteSimple.addChild(sprite_class);
				spriteSimple.addChild(space);
				spriteSimple.addChild(option);
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
				terminationDef.addChild(space);
				terminationDef.addChild(terminationOption);
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
				//spriteDefNewline
				spriteDefNewline.addChild(indent);
				spriteDefNewline.addChild(indent);
				spriteDefNewline.addChild(spriteDef);
				spriteDefNewline.addChild(newline);
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
				//If symbol represents a repeatable section of the grammer
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
		int i = 0;
		gameSymbols.add(game);
		int genomeTracker = 0;

		//While there are still symbols that need to be expanded
		while (containsNonTerminals()) {
			if (i > gameSymbols.size()-1) {
				i = 0;
			}
			Symbol currentSymbol = gameSymbols.get(i);
			
			if (currentSymbol instanceof NonTerminalSymbol) {
				//If symbol represents a repeatable section of the grammer
				if (((NonTerminalSymbol) currentSymbol).repeatable == true) {
					if (genome[genomeTracker] % 2 == 0) {
						gameSymbols.remove(i);
					}
					genomeTracker++;
				}
				else
				{
					//If symbol is not repeatable remove it 
					gameSymbols.remove(i);
					//System.out.println(currentSymbol.name);
				}
				//If symbol is an not optional add children otherwise decide if the the option will be used then continue as before
				if(((NonTerminalSymbol)currentSymbol).optional == false){
					//CHOICE SECTION
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
					//OPTIONAL SECTION
					if (genome[genomeTracker] % 2 == 0) { 
						for (int j=0; j<((NonTerminalSymbol)currentSymbol).children.size(); j++) {
							gameSymbols.add(i, ((NonTerminalSymbol)currentSymbol).children.get(j));
							i++;
						}
					}
					genomeTracker++;
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
				//System.out.print(((InterchangableSymbol)gameSymbols.get(j)).classStrings[genome[genomeTracker] % ((InterchangableSymbol)gameSymbols.get(j)).classStrings.length]);
				writer.print(((InterchangableSymbol)gameSymbols.get(j)).classStrings[genome[genomeTracker] % ((InterchangableSymbol)gameSymbols.get(j)).classStrings.length]);
				genomeTracker++;
			}
			else {
				//System.out.print(((TerminalSymbol)gameSymbols.get(j)).content);
				writer.print(((TerminalSymbol)gameSymbols.get(j)).content);
			}
		}
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
