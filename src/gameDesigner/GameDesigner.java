package gameDesigner;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class GameDesigner {
	//Symbols
	//Declare Non-Terminals
	NonTerminalSymbol game;
	NonTerminalSymbol levelBlock;
	NonTerminalSymbol spriteBlock;
	NonTerminalSymbol interactionBlock;
	NonTerminalSymbol terminationBlock;
	NonTerminalSymbol charMap;
	NonTerminalSymbol spriteDef;
	NonTerminalSymbol interactionDef;
	NonTerminalSymbol terminationDef;
	NonTerminalSymbol spriteSimple;
	NonTerminalSymbol option;
	NonTerminalSymbol eol;
	//Repitition symbols
	NonTerminalSymbol charMapNewline;
	NonTerminalSymbol spriteDefNewline;
	NonTerminalSymbol interactionDefEol;
	NonTerminalSymbol terminationDefEol;
	NonTerminalSymbol spaceSpriteType;
	NonTerminalSymbol spriteDefEol;
	NonTerminalSymbol spaceOption;
	NonTerminalSymbol spaceRepeat;
	NonTerminalSymbol charOrSpaceRepeat;
	//Optional symbols	
	NonTerminalSymbol spriteDefOptionalBlock;
	NonTerminalSymbol spriteSimpleOptionalBlock;
	NonTerminalSymbol eolOptionalBlock;
	//choice symbols (symbol where the string will be chosen by the algorithm
	NonTerminalSymbol charOrSpace;
	//NonTerminalSymbol spriteTyeOrEvaluable; does not currently function as evaluable is not implemented
	NonTerminalSymbol spriteType;
			
	//Declare Terminals	
	TerminalSymbol newline;
	TerminalSymbol indent;
	TerminalSymbol lambda;
	TerminalSymbol greaterThan;
	TerminalSymbol hash;
	TerminalSymbol space;
	TerminalSymbol equals;
	//strings
	TerminalSymbol levelMapping;
	TerminalSymbol avatar;
	TerminalSymbol wall;
	TerminalSymbol eos;
	TerminalSymbol spriteSet;
	TerminalSymbol interactionSet;
	TerminalSymbol terminationSet;
	
	//ClassSymbol
	InterchangableSymbol game_class;
	InterchangableSymbol sprite_class;
	InterchangableSymbol interaction_method;
	InterchangableSymbol termination_class;
	InterchangableSymbol identifier;
	InterchangableSymbol charVar;
	
	String[] gameClasses = {"BasicGame"};
	String[] spriteClasses = {"Immovable", "Passive", "Flicker", "OrientatedFlick", "Missile", "RandomMissile", "RandomNPC",
			"Chaser", "Fleeing", "AlternateChaser", "RandomAltChaser", "SpawnPoint", "Bomber", "RandomBomber", "BomberRandomMissile", "Spreader",
			"Door", "Portal", "Resource", "MovingAvatar", "HorizontalAvatar", "VerticalAvatar", "OngoingAvatar", "OngoingTurningAvatar", "OngoingShootAvatar",
			"MissileAvatar", "OrientatedAvatar", "ShootAvatar", "FlakAvatar"};
	String[] interactionMethods = {"killAll", "killIfHasMore", "killIfHasLess", "killIfFromAbove", "killIfOtherHasMore", "transformToSingleton",
			"spawnBehind", "spawnIfHasMore", "spawnIfHasLess", "cloneSprite", "transformTo", "transformIfCounts", "transformToRandomChild", "updateSpawnType",
			"removeScore", "addHealthPoints", "addHealthPointsToMax", "subtractHealthPoint", "increaseSpeedToAll", "decreaseSpeedToAll", "setSpeedForAll", "stepBack", 
			"undoAll", "flipDirection", "reverseDirection", "attractGaze", "align", "turnAround", "wrapAround", "teleportToExit", "pullWithIt", 
			"bounceForward", "collectResource", "changeResource"};
	String[] terminationClasses = {"SpriteCounter", "SpriteCounterMore", "MultiSpriteCounter", "MultiSpriteCounterSubTypes", "StopCounter", 
			"TimeOut"};
	String[] identifiers = {"var1", "var2", "var3", "var4", "var5", "var6", "var7", "var8", "var9", "var10", };
	String[] chars = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
	
	
	List<Symbol> gameSymbols = new LinkedList<Symbol>();
	
	Random rnd = new Random();
	
	public GameDesigner() {
		initiliseVGDLSymbols();
		int[] genomeTest = new int[500];
		for (int i=0; i < 500; i++) {
			genomeTest[i] = rnd.nextInt(255);
		}
		createGameFromGenome(genomeTest);
	}
	
	private void initiliseVGDLSymbols() {
		//Declare Non-Terminals
		game = new NonTerminalSymbol("game", false, false, false);
		levelBlock = new NonTerminalSymbol("levelBlock", false, false, false);
		spriteBlock = new NonTerminalSymbol("spriteBlock", false, false, false);
		interactionBlock = new NonTerminalSymbol("interactionBlock", false, false, false);
		terminationBlock = new NonTerminalSymbol("terminationBlock", false, false, false);
		charMap = new NonTerminalSymbol("charMap", false, false, false);
		spriteDef = new NonTerminalSymbol("sprieDef", false, false, false);
		interactionDef = new NonTerminalSymbol("interactionDef", false, false, false);
		terminationDef = new NonTerminalSymbol("terminationdef", false, false, false);
		spriteSimple = new NonTerminalSymbol("spriteSimple", false, false, false);
		option = new NonTerminalSymbol("option", false, false, false);
		eol = new NonTerminalSymbol("eol", false, false, false);
		//Repitition Symbols
		charMapNewline = new NonTerminalSymbol("charMapNewline", true, false, false);	
		spriteDefNewline = new NonTerminalSymbol("spriteDefNewline", true, false, false);
		interactionDefEol = new NonTerminalSymbol("interactionDefEol", true, false, false);
		terminationDefEol = new NonTerminalSymbol("terminationDefEol", true, false, false);
		spaceSpriteType = new NonTerminalSymbol("spaceSpriteType", true, false, false);
		spriteDefEol = new NonTerminalSymbol("spriteDefEol", true, false, false);
		spaceOption = new NonTerminalSymbol("spaceOption", true, false, false);
		spaceRepeat = new NonTerminalSymbol("spaceRepeat", true, false, false);
		charOrSpaceRepeat = new NonTerminalSymbol("charOrSpaceRepeat", true, false, false);
		//Optional Symbols
		spriteDefOptionalBlock = new NonTerminalSymbol("spriteDefOptional", false, true, false);
		spriteSimpleOptionalBlock = new NonTerminalSymbol("spriteSimpleOptional", false, true, false);
		eolOptionalBlock = new NonTerminalSymbol("eolOptional", false, true, false);
		//choice Symbols
		spriteType = new NonTerminalSymbol("spriteType", false, false, true);
		charOrSpace = new NonTerminalSymbol("charOrSpace", false, false, true);
		//Declare Terminals		
		newline = new TerminalSymbol("newline", "\n");
		indent = new TerminalSymbol("indent","	");
		lambda = new TerminalSymbol("lambda","LAMBDA");
		greaterThan = new TerminalSymbol("greater than"," > ");
		hash = new TerminalSymbol("hash", "#");
		space = new TerminalSymbol("space", " ");
		equals = new TerminalSymbol("equals", "=");
		//strings
		levelMapping = new TerminalSymbol("levelMapping","LevelMapping");
		avatar = new TerminalSymbol("avatar","avatar");
		wall = new TerminalSymbol("wall","wall");
		eos = new TerminalSymbol("eos", "EOS");
		spriteSet = new TerminalSymbol("spriteSet","SpriteSet");
		interactionSet = new TerminalSymbol("interactionSet","InteractionSet");
		terminationSet = new TerminalSymbol("terminationSet","TerminationSet");
		
		//Declare Interchangeable Symbols
		game_class = new InterchangableSymbol("game_class", gameClasses); //will not actually be terminal as it can expand to a number of things but this is simpler for now
		sprite_class = new InterchangableSymbol("sprite_class", spriteClasses); //will need to be passed as some sort of parameter or made into nonTerminal
		interaction_method = new InterchangableSymbol("interaction_method", interactionMethods); //will need to be passed as some sort of parameter or made into nonTerminal
		termination_class = new InterchangableSymbol("termination_class", terminationClasses);
		identifier = new InterchangableSymbol("identifier", identifiers);
		charVar = new InterchangableSymbol("char", chars);

		
		//Has been split so that it can minimised and this makes things neater to work with
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
				interactionDef.addChild(option);
				//termination-def
				terminationDef.addChild(termination_class);
				terminationDef.addChild(space);
				terminationDef.addChild(option);
				//eol
				eol.addChild(newline);
				//eol.addChild(eolOptionalBlock);
				//option
				option.addChild(identifier);
				option.addChild(equals);
				option.addChild(spriteType);
				//option.addChild(evaluable); //requires OR functionality
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
				System.out.print(((InterchangableSymbol)gameSymbols.get(j)).classStrings[genome[genomeTracker] % ((InterchangableSymbol)gameSymbols.get(j)).classStrings.length]);
				genomeTracker++;
			}
			else {
				System.out.print(((TerminalSymbol)gameSymbols.get(j)).content);
			}
		}
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
