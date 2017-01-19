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
	NonTerminalSymbol spriteType;
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
	NonTerminalSymbol charOrSpace; 
	//Optional symbols	
	NonTerminalSymbol spriteDefOptionalBlock;
	NonTerminalSymbol spriteSimpleOptionalBlock;
	NonTerminalSymbol eolOptionalBlock;
			
	//Declare Terminals	
	TerminalSymbol newline;
	TerminalSymbol indent;
	TerminalSymbol identifier;
	TerminalSymbol lambda;
	TerminalSymbol greaterThan;
	TerminalSymbol hash;
	TerminalSymbol space;
	TerminalSymbol equals;
	//strings
	TerminalSymbol game_class;
	TerminalSymbol sprite_class;
	TerminalSymbol interaction_method;
	TerminalSymbol termination_class;
	TerminalSymbol levelMapping;
	TerminalSymbol variableChar;
	TerminalSymbol avatar;
	TerminalSymbol wall;
	TerminalSymbol spriteSet;
	TerminalSymbol interactionSet;
	TerminalSymbol terminationSet;
	
	
	List<Symbol> gameSymbols = new LinkedList<Symbol>();
	
	Random rnd = new Random();
	
	public GameDesigner() {
		establishVGDLSymbols();
		basicGame();
	}
	
	private void establishVGDLSymbols() {
		//Declare Non-Terminals
		game = new NonTerminalSymbol("game", false, false);
		levelBlock = new NonTerminalSymbol("levelBlock", false, false);
		spriteBlock = new NonTerminalSymbol("spriteBlock", false, false);
		interactionBlock = new NonTerminalSymbol("interactionBlock", false, false);
		terminationBlock = new NonTerminalSymbol("terminationBlock", false, false);
		charMap = new NonTerminalSymbol("charMap", false, false);
		spriteType = new NonTerminalSymbol("spriteType", false, false);
		spriteDef = new NonTerminalSymbol("spriteDef", false, false);
		interactionDef = new NonTerminalSymbol("interactionDef", false, false);
		terminationDef = new NonTerminalSymbol("terminationdef", false, false);
		spriteSimple = new NonTerminalSymbol("spriteSimple", false, false);
		option = new NonTerminalSymbol("option", false, false);
		eol = new NonTerminalSymbol("eol", false, false);
		//Repitition Symbols
		charMapNewline = new NonTerminalSymbol("charMapNewline", true, false);	
		spriteDefNewline = new NonTerminalSymbol("spriteDefNewline", true, false);
		interactionDefEol = new NonTerminalSymbol("interactionDefEol", true, false);
		terminationDefEol = new NonTerminalSymbol("terminationDefEol", true, false);
		spaceSpriteType = new NonTerminalSymbol("spaceSpriteType", true, false);
		spriteDefEol = new NonTerminalSymbol("spriteDefEol", true, false);
		spaceOption = new NonTerminalSymbol("spaceOption", true, false);
		charOrSpace = new NonTerminalSymbol("charOrSpace", true, false);
		//Optional Symbols
		spriteDefOptionalBlock = new NonTerminalSymbol("spriteDefOptional", false, true);
		spriteSimpleOptionalBlock = new NonTerminalSymbol("spriteSimpleOptional", false, true);
		eolOptionalBlock = new NonTerminalSymbol("eolOptional", false, true);
		//Declare Terminals		
		newline = new TerminalSymbol("newline", "\n");
		indent = new TerminalSymbol("indent","	");
		identifier = new TerminalSymbol("identifier","IDENTIFIER");
		lambda = new TerminalSymbol("lambda","LAMBDA");
		greaterThan = new TerminalSymbol("greater than"," > ");
		hash = new TerminalSymbol("hash", "#");
		space = new TerminalSymbol("space", " ");
		equals = new TerminalSymbol("equals", "=");
		//strings
		game_class = new TerminalSymbol("game_class","BasicGame"); //will not actually be terminal as it can expand to a number of things but this is simpler for now
		sprite_class = new TerminalSymbol("sprite_class", "sprite_class"); //will need to be passed as some sort of parameter or made into nonTerminal
		interaction_method = new TerminalSymbol("interaction_method", "interaction_method"); //will need to be passed as some sort of parameter or made into nonTerminal
		termination_class = new TerminalSymbol("termination_class", "termination_class");
		levelMapping = new TerminalSymbol("levelMapping","LevelMapping");
		variableChar = new TerminalSymbol("char","CHAR"); //Just using "CHAR" as place holder for now
		avatar = new TerminalSymbol("avatar","avatar");
		wall = new TerminalSymbol("wall","wall");
		spriteSet = new TerminalSymbol("spriteSet","SpriteSet");
		interactionSet = new TerminalSymbol("interactionSet","InteractionSet");
		terminationSet = new TerminalSymbol("terminationSet","TerminationSet");
		
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
		charMap.addChild(variableChar);
		charMap.addChild(greaterThan);
		charMap.addChild(spriteType);
		charMap.addChild(spaceSpriteType);
		//sprite-def
		spriteDef.addChild(spriteSimple);
		spriteDef.addChild(spriteDefOptionalBlock);
		//spriteDef.addChild(eol); //on hold for now until repeatable and optional sections are setup
		//spriteDef.addChild(indent);
		//spriteDef.addChild(spriteDef);
		//spriteDef.addChild(eol);
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
		//eol.addChild(space);
		//eol.addChild(hash);
		//eol.addChild(variableChar);
		//eol.addChild(space);
		eol.addChild(newline);
		//option
		option.addChild(identifier);
		option.addChild(equals);
		option.addChild(spriteType);
		//option.addChild(evaluable); //requires OR functionality
		spriteType.addChild(identifier);
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
		spriteDefEol.addChild(spriteDef);
		spriteDefEol.addChild(eol);
		//spaceOption
		spaceOption.addChild(space);
		spaceOption.addChild(option);
		//charOrSpace
		//Orfunctionality not yet ready
		
		//Optionals
		//spriteDefOptionalBlock
		spriteDefOptionalBlock.addChild(eol);
		spriteDefOptionalBlock.addChild(indent);
		spriteDefOptionalBlock.addChild(indent);
		spriteDefOptionalBlock.addChild(spriteDefEol);
		//spriteSimpleOptionalBlock
		spriteSimpleOptionalBlock.addChild(sprite_class);
		//eolOptionalBlock
		eolOptionalBlock.addChild(hash);
		eolOptionalBlock.addChild(charOrSpace);
		
	}
	
	public void basicGame() {
		//game_class
			//LevelMapping
				//CHAR > IDENTIFIER
		int i = 0;
		gameSymbols.add(game);
		System.out.println(gameSymbols.get(0).name);
		while (containsNonTerminals()) {
			if (i > gameSymbols.size()-1) {
				i = 0;
			}
			Symbol currentSymbol = gameSymbols.get(i);
			
			if (currentSymbol instanceof NonTerminalSymbol) {
				//System.out.println(((NonTerminalSymbol)currentSymbol).repeatable);
				if (((NonTerminalSymbol) currentSymbol).repeatable == true) {
					//EA makes decision somehow as to if the thing is repeated
					//for now its just 50/50 random
					if (rnd.nextInt(2) == 1) {
					//if (k > 2) {
						gameSymbols.remove(i);
						System.out.println("repeater removed");
						//k = 0;	
					}
				}
				else
				{
					gameSymbols.remove(i);
				}
				//System.out.println(((NonTerminalSymbol)currentSymbol).children.size());
				if(((NonTerminalSymbol)currentSymbol).optional == false){
					for (int j=0; j<((NonTerminalSymbol)currentSymbol).children.size(); j++) {
						gameSymbols.add(i, ((NonTerminalSymbol)currentSymbol).children.get(j));
						i++;
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
		System.out.println(gameSymbols.size());
		for (int j=0; j<gameSymbols.size(); j++) {	
			System.out.print(((TerminalSymbol)gameSymbols.get(j)).content);
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
